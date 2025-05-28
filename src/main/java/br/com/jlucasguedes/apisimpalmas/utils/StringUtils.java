package br.com.jlucasguedes.apisimpalmas.utils;

import java.util.Objects;

public class StringUtils {

  public static String formatAtualizouCadastro(String text) {
    if (Objects.nonNull(text)) {
      return text.equals("1") ? "Sim" : "Não";
    }
    return "Não";
  }

  public static String formatNomeUnidade(String nome) {
    return nome.replace("CENTRO MUNICIPAL DE EDUCAÇÃO INFANTIL", "CMEI")
        .replace("ESCOLA MUNICIPAL DE TEMPO INTEGRAL", "ETI")
        .replace("ESCOLA MUNICIPAL", "ESC. MUN.")
        .replace("ESCOLA", "ESC.")
        .replace("ASSOCIAÇÃO", "ASSOC.");
  }

  public static String formatHorario(String horario) {
    if (horario.substring(3).equals("00")) {
      return horario.replace(":", "h");
    }
    return horario.replace(":", "h").concat("min");
  }
}