package br.com.jlucasguedes.apisimpalmas.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ResponsavelCadastro {
  private boolean temcadastro;
  private Pessoa pessoa;
  private List<Crianca> crianças;
}
