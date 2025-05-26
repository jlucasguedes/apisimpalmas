package br.com.jlucasguedes.apisimpalmas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jlucasguedes.apisimpalmas.domain.Serie;
import br.com.jlucasguedes.apisimpalmas.utils.HtmlParser;

@Service
public class SerieService {

  @Autowired
  private HtmlParser htmlParser;

  public List<Serie> listarByUnidadeEducacionalId(String ano, String idUnidade) {
    List<Serie> series = new ArrayList<>();

    Document doc = htmlParser.fetchPage(
        "http://semed.palmas.to.gov.br/sige/app/action/serie/createelementserieunidade.php",
        Method.POST,
        Map.of("ano", ano, "idunidade", idUnidade));
    Element selectSerie = doc.getElementById("idserie");
    Elements options = selectSerie.select("option:gt(0)");
    for (Element element : options) {
      series.add(
          Serie
              .builder()
              .id(Integer.parseInt(element.val()))
              .descricao(element.text())
              .build());
    }
    return series;
  }
}
