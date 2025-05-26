package br.com.jlucasguedes.apisimpalmas.domain;

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
@Builder
@EqualsAndHashCode
public class Pessoa {
  private Long idpessoaresponsavel;
  private String nome;
  private String cpf;
  private String numerorg;
  private String cep;
  private String endereco;
  private String bairro;
  private String numero;
  private String complemento;
  private String telefoneresidencial;
  private String celular;
  private String telefonecomercial;
  private String email;
  private String datacadastro;
  private String horacadastro;
  private boolean flativo;
}
