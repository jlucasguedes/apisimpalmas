package br.com.jlucasguedes.apisimpalmas.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jlucasguedes.apisimpalmas.domain.ResponsavelCadastro;
import br.com.jlucasguedes.apisimpalmas.service.SolicitacaoService;

@RestController
@RequestMapping("/solicitacoes")
public class ResponsavelCadastroResource {

  @Autowired
  private SolicitacaoService service;

  @GetMapping("/responsavel/{cpf}")
  public ResponseEntity<ResponsavelCadastro> cadastro(@PathVariable String cpf) {
    return ResponseEntity.ok().body(service.verifiCadastro(cpf));
  }
}
