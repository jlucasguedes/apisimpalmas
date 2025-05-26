package br.com.jlucasguedes.apisimpalmas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UnidadeEducaionalSerie {
  private String ano;
  private String idSerie;
  private String idUnidade;
  private String idTipoFiltro;
}
