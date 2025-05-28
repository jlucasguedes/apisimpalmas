package br.com.jlucasguedes.apisimpalmas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jlucasguedes.apisimpalmas.domain.UnidadeEducacional;

@Repository
public interface UnidadeEducacionalRepository extends JpaRepository<UnidadeEducacional, Integer> {
  Optional<UnidadeEducacional> findByNomeIgnoreCase(String nome);
}
