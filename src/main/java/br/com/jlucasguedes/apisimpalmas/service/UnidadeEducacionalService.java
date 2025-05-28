package br.com.jlucasguedes.apisimpalmas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.jlucasguedes.apisimpalmas.domain.UnidadeEducacional;
import br.com.jlucasguedes.apisimpalmas.exception.CadastroNaoEncontradoException;
import br.com.jlucasguedes.apisimpalmas.repository.UnidadeEducacionalRepository;
import br.com.jlucasguedes.apisimpalmas.utils.HtmlParser;
import jakarta.transaction.Transactional;

@Service
public class UnidadeEducacionalService {

  @Autowired
  private HtmlParser htmlParser;

  @Autowired
  private UnidadeEducacionalRepository repository;

  public List<UnidadeEducacional> findAll() {
    return repository.findAll(Sort.by(Direction.ASC, "idSimPalmas"));
  }

  public UnidadeEducacional findByNomeIgnoreCase(String nome) {
    return repository.findByNomeIgnoreCase(nome)
        .orElseThrow(() -> new CadastroNaoEncontradoException("Unidade educacional não encontrada"));
  }

  @Transactional
  public void sincronizarUnidades() {
    List<UnidadeEducacional> novasUnidades = getUnidadesCompletasFromSimPalmas();

    // Mapa de unidades persistidas por ID
    Map<Integer, UnidadeEducacional> unidadesExistentes = repository.findAll().stream()
        .collect(Collectors.toMap(UnidadeEducacional::getIdSimPalmas, u -> u));

    List<UnidadeEducacional> unidadesParaSalvar = new ArrayList<>();

    for (UnidadeEducacional nova : novasUnidades) {
      UnidadeEducacional existente = unidadesExistentes.get(nova.getIdSimPalmas());

      if (existente == null) {
        // Unidade nova - inserir
        unidadesParaSalvar.add(nova);
      } else if (!dadosIguais(existente, nova)) {
        // Unidade já existe, mas com dados diferentes - atualizar
        existente.setNome(nova.getNome());
        existente.setCodigoInep(nova.getCodigoInep());
        existente.setAtendimento(nova.getAtendimento());
        existente.setTelefoneComercial(nova.getTelefoneComercial());
        existente.setEndereco(nova.getEndereco());
        existente.setBairro(nova.getBairro());
        unidadesParaSalvar.add(existente);
      }
    }

    repository.saveAll(unidadesParaSalvar);

  }

  public List<UnidadeEducacional> getUnidadesCompletasFromSimPalmas() {
    // 1. Obter as listas separadas
    List<UnidadeEducacional> basicas = getUnidadesFromSimPalmas();
    List<UnidadeEducacional> complementares = getDadosUnidadesFromSimPalmas();

    // 2. Criar um mapa com os dados básicos, chaveando pelo nome
    Map<String, UnidadeEducacional> mapaBasicos = basicas.stream()
        .collect(Collectors.toMap(UnidadeEducacional::getNome, u -> u));

    // 3. Fazer o merge dos dados complementares
    for (UnidadeEducacional comp : complementares) {
      UnidadeEducacional base = mapaBasicos.get(comp.getNome());
      if (base != null) {
        base.setAtendimento(comp.getAtendimento());
        base.setTelefoneComercial(comp.getTelefoneComercial());
        base.setEndereco(comp.getEndereco());
        base.setBairro(comp.getBairro());
      } else {
        // Se não encontrou, adiciona mesmo assim
        mapaBasicos.put(comp.getNome(), comp);
      }
    }

    // 4. Retornar a lista unificada
    return new ArrayList<>(mapaBasicos.values());
  }

  public List<UnidadeEducacional> getUnidadesFromSimPalmas() {
    List<UnidadeEducacional> unidades = new ArrayList<>();
    Document document = htmlParser
        .parsePage("http://semed.palmas.to.gov.br/sige/indexsm.php?url=92FDCD01EC7E38AA358DF35A6C14D19F");
    Element selectUnidades = document.getElementById("idunidade");
    Elements options = selectUnidades.select("option:gt(0)");
    for (Element element : options) {
      unidades.add(convert(element.text()));
    }
    return unidades;
  }

  public List<UnidadeEducacional> getDadosUnidadesFromSimPalmas() {
    List<UnidadeEducacional> unidades = new ArrayList<>();
    Document document = htmlParser
        .parsePage("http://semed.palmas.to.gov.br/sige/public/sm/unidade/listunidade.php");
    Element tableUnidades = document.getElementsByTag("table").first();
    Element bodyTableUnidades = tableUnidades.getElementsByTag("tbody").first();
    Elements linhasBodyTableUnidades = bodyTableUnidades.getElementsByTag("tr");
    for (Element linha : linhasBodyTableUnidades) {
      unidades.add(UnidadeEducacional
          .builder()
          .nome(linha.getElementsByTag("td").get(0).text())
          .atendimento(linha.getElementsByTag("td").get(1).text())
          .telefoneComercial(linha.getElementsByTag("td").get(2).text())
          .endereco(linha.getElementsByTag("td").get(3).text())
          .bairro(linha.getElementsByTag("td").get(4).text())
          .build());
    }
    return unidades;
  }

  private UnidadeEducacional convert(String linha) {
    String[] partes = linha.split(" - ", 3);
    int id = Integer.parseInt(partes[0].trim());
    String codigoInep = partes[1].trim();
    String nome = partes[2].trim();
    return UnidadeEducacional.builder()
        .idSimPalmas(id)
        .codigoInep(codigoInep)
        .nome(nome)
        .build();
  }

  private boolean dadosIguais(UnidadeEducacional u1, UnidadeEducacional u2) {
    return Objects.equals(u1.getNome(), u2.getNome()) &&
        Objects.equals(u1.getCodigoInep(), u2.getCodigoInep()) &&
        Objects.equals(u1.getAtendimento(), u2.getAtendimento()) &&
        Objects.equals(u1.getTelefoneComercial(), u2.getTelefoneComercial()) &&
        Objects.equals(u1.getEndereco(), u2.getEndereco()) &&
        Objects.equals(u1.getBairro(), u2.getBairro());
  }

}
