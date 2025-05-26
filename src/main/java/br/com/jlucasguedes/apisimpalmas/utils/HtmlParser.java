package br.com.jlucasguedes.apisimpalmas.utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import br.com.jlucasguedes.apisimpalmas.exception.InputOutputException;

@Component
public class HtmlParser {
  public Document parsePage(String url) {
    try {
      return Jsoup.connect(url)
          .userAgent("Mozilla/5.0")
          .timeout(120 * 1000)
          .get();
    } catch (IOException e) {
      throw new InputOutputException("Erro ao acessar a url informada.");
    }
  }

}
