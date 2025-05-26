package br.com.jlucasguedes.apisimpalmas.exception;

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
public class DetalheErro {
  private String titulo;
  private Long status;
  private Long timestamp;
  private String mensagemDesenvolvedor;

}
