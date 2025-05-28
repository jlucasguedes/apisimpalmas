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
@EqualsAndHashCode
@Builder
public class Pessoa {
  private int idpessoaresponsavel;
  private String nome;
  private String cpf;
  private String numerorg;
  private String cep;
  private String endereco;
  private String bairro;
  private String numero;
  private String complemento;
  private String celular;
  private String telefonecomercial;
  private String telefoneresidencial;
  private String email;
  private String datacadastro;
  private String horacadastro;
  private boolean flativo;
  private String protocolo;
}
