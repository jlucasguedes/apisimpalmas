package br.com.jlucasguedes.apisimpalmas.domain;

import java.util.ArrayList;
import java.util.List;

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
public class ResponsavelCadastro {
  private int idPessoaResponsavel;
  private boolean temCadastro;
  private String nome;
  private String cpf;
  private String rg;
  private String celular;
  private String telefoneComercial;
  private String telefoneResidencial;
  private String email;
  private String dataCadastro;
  private String horaCadastro;
  private String protocolo;
  private List<Crianca> criancas = new ArrayList<>();
}
