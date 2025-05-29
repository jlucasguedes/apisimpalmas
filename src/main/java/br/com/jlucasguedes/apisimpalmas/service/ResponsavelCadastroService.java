package br.com.jlucasguedes.apisimpalmas.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jlucasguedes.apisimpalmas.domain.Crianca;
import br.com.jlucasguedes.apisimpalmas.domain.CriancaWrapper;
import br.com.jlucasguedes.apisimpalmas.domain.Criterio;
import br.com.jlucasguedes.apisimpalmas.domain.ResponsavelCadastro;
import br.com.jlucasguedes.apisimpalmas.domain.ResponsavelCadastroWrapper;
import br.com.jlucasguedes.apisimpalmas.domain.Serie;
import br.com.jlucasguedes.apisimpalmas.domain.Solicitacao;
import br.com.jlucasguedes.apisimpalmas.exception.CadastroNaoEncontradoException;
import br.com.jlucasguedes.apisimpalmas.utils.HtmlParser;

@Service
public class ResponsavelCadastroService {

  @Autowired
  private HtmlParser htmlParser;

  @Autowired
  private UnidadeEducacionalService unidadeEducacionalService;

  public ResponsavelCadastro verifiResponsavelCadastro(String cpf) {
    try {
      Document document = htmlParser.parsePage(
          "http://semed.palmas.to.gov.br/sige/app/action/mo/pessoaresponsavel/pessoaresponsaveltemcadastro.php?cpf="
              + cpf,
          Method.POST);
      Element body = document.body();

      ObjectMapper mapper = new ObjectMapper();
      ResponsavelCadastroWrapper wrapper = mapper.readValue(body.text(), ResponsavelCadastroWrapper.class);

      if (!wrapper.getRecords().isTemcadastro()) {
        throw new CadastroNaoEncontradoException("Responsável não possui cadastro.");
      }
      ResponsavelCadastro responsavelEncontrado = wrapper.getRecords();
      responsavelEncontrado.getPessoa().setProtocolo(getProtocolo(cpf));
      responsavelEncontrado.setCrianças(getCriancasComprovante(responsavelEncontrado.getPessoa().getProtocolo()));
      return wrapper.getRecords();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new CadastroNaoEncontradoException("Não foi possivel buscar o cadastro. tente novamente mais tarde");
    }
  }

  private String getProtocolo(String cpf) {
    Document document = htmlParser.fetchPage(
        "http://semed.palmas.to.gov.br/sige/public/sm/protocolo/recuperarprotocololist.php",
        Method.POST,
        Map.of("cpf", cpf));

    Element table = document.getElementById("tblistprotocolo");
    Element bodyTable = table.getElementsByTag("tbody").first();
    Element row = bodyTable.getElementsByTag("tr").first();

    return row.getElementsByTag("td").get(2).text();
  }

  private List<Crianca> getCriancas(String cpf, Integer idPessoaReponsavel) {
    try {
      Document doc = htmlParser.parsePage(
          "http://semed.palmas.to.gov.br/sige/app/action/mo/pessoacadastro/jsonallpessoacadastroresponsavel.php?cpf="
              + cpf
              + "&idpessoaresponsavel="
              + idPessoaReponsavel,
          Method.GET);
      ObjectMapper mapper = new ObjectMapper();
      CriancaWrapper wrapper = mapper.readValue(doc.body().text(), CriancaWrapper.class);

      return wrapper.getRecords();

    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new CadastroNaoEncontradoException("Não foi possivel buscar as crianças cadastradas");
    }
  }

  private List<Crianca> getCriancasComprovante(String protocolo) {
    String md5 = DigestUtils.md5DigestAsHex(protocolo.getBytes(StandardCharsets.UTF_8));
    String url = "http://semed.palmas.to.gov.br/sige/indexrelatoriomatriculaonline.php?url=50EC1894EF4018C8ACE6623CBAAE39B4&p="
        + md5;
    Document doc = htmlParser.parsePage(url, Method.POST);
    Element bodyPage = doc.body();
    Elements tables = bodyPage.select("table:gt(0)");
    int quantidadeCadastros = tables.size();

    List<Crianca> criancas = new ArrayList<>();

    for (int i = 0; i < quantidadeCadastros; i = i + 2) {
      Element tableDadosCrianca = tables.get(i);
      Element bodyTable = tableDadosCrianca.getElementsByTag("tbody").first();
      Element linhaDadosCrianca = bodyTable.getElementsByTag("tr").get(0);
      Element linhaEnderecoCrianca = bodyTable.getElementsByTag("tr").get(1);
      Element linhaContatoCrianca = bodyTable.getElementsByTag("tr").get(2);

      Crianca crianca = Crianca
          .builder()
          .nome(linhaDadosCrianca.getElementsByTag("td").get(0).text())
          .datanascimento(linhaDadosCrianca.getElementsByTag("td").get(1).text())
          .nomemae(linhaDadosCrianca.getElementsByTag("td").get(2).text())
          .datacadastro(linhaDadosCrianca.getElementsByTag("td").get(3).text())
          .dataalteracao(verificaExistenciaDataAlteracao(linhaDadosCrianca.getElementsByTag("td").get(4).text()))
          .endereco(linhaEnderecoCrianca.getElementsByTag("th").get(1).text())
          .telefone(linhaContatoCrianca.getElementsByTag("th").get(1).text().replace(",", "").trim())
          .build();

      Element tableSolicitacao = tables.get(i + 1);
      Element bodyTableSolicitacao = tableSolicitacao.getElementsByTag("tbody").first();
      Element linhaSolicitacao = bodyTableSolicitacao.getElementsByTag("tr").first();

      Solicitacao solicitacao = Solicitacao
          .builder()
          .opcao(Integer.parseInt(linhaSolicitacao.getElementsByTag("td").first().text().substring(0, 1)))
          .serie(new Serie(null, linhaSolicitacao.getElementsByTag("td").get(1).text()))
          .unidade(
              unidadeEducacionalService.findByNomeIgnoreCase(linhaSolicitacao.getElementsByTag("td").get(2).text()))
          .encerrada(!linhaSolicitacao.getElementsByTag("td").get(4).text().equals("NÃO"))
          .build();

      crianca.setSolicitacao(solicitacao);

      int indexCriterio = tableSolicitacao.getElementsByTag("tr").size() - 1;
      Element trCriterios = tableSolicitacao.getElementsByTag("tr").get(indexCriterio);
      Element tdCriterios = trCriterios.getElementsByTag("td").get(1);

      if (!tdCriterios.text().isBlank()) {
        String[] criteriosString = tdCriterios.text().trim().split(";");
        if (criteriosString.length > 0) {
          for (String string : criteriosString) {
            crianca.getCriterios().add(getCriterio(string));
          }
        }
      }
      criancas.add(crianca);
    }
    return criancas;
  }

  private static Criterio getCriterio(String criteriosString) {
    final String[] criterioVetor = criteriosString.split("-");
    Criterio criterio = new Criterio();
    criterio.setCodigo(Integer.parseInt(criterioVetor[0].trim()));
    criterio.setDescricao(criterioVetor[1].trim());
    return criterio;
  }

  private String verificaExistenciaDataAlteracao(String dataAlteracao) {
    if (!"//".equalsIgnoreCase(dataAlteracao)) {
      return dataAlteracao;
    } else {
      return "";
    }
  }

}