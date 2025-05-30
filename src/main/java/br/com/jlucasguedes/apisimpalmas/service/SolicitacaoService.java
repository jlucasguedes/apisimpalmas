package br.com.jlucasguedes.apisimpalmas.service;

import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jlucasguedes.apisimpalmas.domain.ResponsavelCadastro;
import br.com.jlucasguedes.apisimpalmas.domain.WrapperResponse;
import br.com.jlucasguedes.apisimpalmas.exception.CadastroNaoEncontradoException;
import br.com.jlucasguedes.apisimpalmas.utils.HtmlParser;

@Service
public class SolicitacaoService {

  @Autowired
  private HtmlParser htmlParser;

  public ResponsavelCadastro verificaCadastro(String cpf) {
    try {
      Document doc = htmlParser.fetchPage(
          "http://semed.palmas.to.gov.br/sige/app/action/mo/pessoaresponsavel/pessoaresponsaveltemcadastro.php?cpf="
              + cpf,
          Method.POST, null);
      Element body = doc.getElementsByTag("body").first();
      System.out.println(body.text());
      ObjectMapper mapper = new ObjectMapper();
      WrapperResponse response;
      response = mapper.readValue(body.text(), WrapperResponse.class);
      ResponsavelCadastro cadastro = response.getRecords();
      if (!cadastro.isTemcadastro()) {
        throw new CadastroNaoEncontradoException("O CPF informado não possui cadastro");
      }
      return cadastro;
    } catch (JsonProcessingException e) {
      throw new CadastroNaoEncontradoException("erro ao procurar cadastro");
    }
  }

}
