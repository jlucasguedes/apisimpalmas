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

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(schema = "app", name = "serie")
@SequenceGenerator(schema = "app", name = "serie_seq_gen", sequenceName = "serie_id_seq", allocationSize = 1)
public class Serie {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serie_seq_gen")
  private Integer id;
  private String descricao;
}
