package br.com.jlucasguedes.apisimpalmas.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jlucasguedes.apisimpalmas.domain.UnidadeEducacional;
import br.com.jlucasguedes.apisimpalmas.utils.HtmlParser;

@Service
public class UnidadeEducacionalService {

  @Autowired
  private HtmlParser htmlParser;

  public String getUnidadesFromSimPalmas() {
    Document document = htmlParser
        .parsePage("http://semed.palmas.to.gov.br/sige/indexsm.php?url=92FDCD01EC7E38AA358DF35A6C14D19F");
    Element selectUnidades = document.getElementById("idunidade");
    Elements optionsUnidades = selectUnidades.getElementsByTag("option");
    for (Element element : optionsUnidades) {
      String[] partes = element.text().split(" - ", 3);
      System.out.println(partes[1] + "\n");
    }
    return selectUnidades.text();
  }

  private UnidadeEducacional convert(String linha) {
    String[] partes = linha.split(" - ", 3);
    System.out.println(partes[0].trim());
    String codigoInep = partes[1].trim();
    String nome = partes[2].trim();

    return UnidadeEducacional.builder()
        .codigoInep(codigoInep)
        .nome(nome)
        .build();
  }
}
