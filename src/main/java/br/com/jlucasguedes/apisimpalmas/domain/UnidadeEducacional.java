package br.com.jlucasguedes.apisimpalmas.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class UnidadeEducacional {
  private Integer id;
  @JsonInclude(Include.NON_NULL)
  private String idsige;
  @JsonInclude(Include.NON_NULL)
  private String codigoInep;
  @JsonInclude(Include.NON_NULL)
  private String nome;
  @JsonInclude(Include.NON_NULL)
  private String nomeFormatado;
  @JsonInclude(Include.NON_NULL)
  private String telefoneComercial;
  @JsonInclude(Include.NON_NULL)
  private String atendimento;
  @JsonInclude(Include.NON_NULL)
  private String whatsapp;
  @JsonInclude(Include.NON_NULL)
  private String email;
  @JsonInclude(Include.NON_NULL)
  private Double latitude;
  @JsonInclude(Include.NON_NULL)
  private Double logintude;
  @JsonInclude(Include.NON_NULL)
  private String endereco;
  @JsonInclude(Include.NON_NULL)
  private String bairro;
  @JsonInclude(Include.NON_NULL)
  private String imagem;
  @JsonInclude(Include.NON_NULL)
  private List<Serie> series;
}
