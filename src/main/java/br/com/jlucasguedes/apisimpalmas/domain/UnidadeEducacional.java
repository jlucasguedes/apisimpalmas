package br.com.jlucasguedes.apisimpalmas.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Entity
@Table(schema = "app", name = "unidade_educacional")
@SequenceGenerator(schema = "app", name = "unidade_educacional_seq_gen", sequenceName = "unidade_educacional_id_seq", allocationSize = 1)
public class UnidadeEducacional {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidade_educacional_seq_gen")
  private Integer id;
  @Column(unique = true, nullable = false)
  private Integer idSimPalmas;
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
}
