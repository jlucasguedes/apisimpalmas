package br.com.jlucasguedes.apisimpalmas.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Crianca {
  private int id;
  private int idResponsavel;
  private String ordem;
  private String pontuacao;
  private String nome;
  private String nomeMae;
  private String dataNascimento;
  private String sexo;
  private String endereco;
  private String dataCadastro;
  private String dataAtualizacao;
  private String horaCadastro;
  private int ano;
  private boolean matriculado;
  private String situacao;
  private String opcao;
  private String atualizouCadastro;
  private Serie serie;
  private List<Solicitacao> solicitacoes = new ArrayList<>();
  private List<Criterio> criterios = new ArrayList<>();
}
