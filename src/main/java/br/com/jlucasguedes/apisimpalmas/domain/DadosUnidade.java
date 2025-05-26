package br.com.jlucasguedes.apisimpalmas.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(schema = "app", name = "dados_unidade")
@SequenceGenerator(schema = "app", name = "dados_unidade_seq_gen", sequenceName = "dados_unidades_id_seq", allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DadosUnidade {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dados_unidade_seq_gen")
  private long id;
  private Double latitude;
  private Double longitude;
  private String imagem;
}
