package br.com.jlucasguedes.apisimpalmas.utils;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
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

  public Document fetchPage(String url, Connection.Method method, Map<String, String> params) {
    try {
      Connection connection = Jsoup.connect(url)
          .method(method)
          .timeout(10000) // tempo limite opcional
          .userAgent("Mozilla/5.0") // simula um navegador
          .header("Content-Type", "application/x-www-form-urlencoded");

      if (params != null && !params.isEmpty()) {
        connection.data(params);
      }

      return connection.execute().parse();
    } catch (IOException e) {
      throw new InputOutputException("Erro ao acessar a url informada.");
    }
  }

}
