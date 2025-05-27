package br.com.jlucasguedes.apisimpalmas.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Crianca {
  private int idpessoa;
  private int idpessoacadastro;
  private int idpessoaresponsavel;
  private String nome;
  private String datanascimento;
  private String nomemae;
  private String cpfmae;
  private String sexo;
  private String cep;
  private String endereco;
  private String bairro;
  private String numero;
  private String complemento;
  private String telefone;
  private String celular;
  private String telefonecomercial;
  private String email;
  private int ano;
  private String datacadastro;
  private String horacadastro;
  private int idnecessidadeespecial;
  private int idresponsavelguarda;
  private boolean temirmaogemeo;
  private boolean matriculado;
  private boolean sorteado;
  private boolean flativo;
  private String dataalteracao;
  private boolean cadastroencerrado;
  private String atualizoucadastro;
  private boolean participasorteio;
  private String datamatricula;
  private boolean solicitacaotransferencia;
  private int identurmacao;
  private int idserie;
  private String descricaonecessidadeespecial;
  private String descresponsavelguarda;
  private String descricaoserie;
  private int idmodalidade;
  private String descricaomodalidade;
  private int idademinima;
  private Solicitacao solicitacao;
  @Builder.Default
  private List<Criterio> criterios = new ArrayList<>();
}
