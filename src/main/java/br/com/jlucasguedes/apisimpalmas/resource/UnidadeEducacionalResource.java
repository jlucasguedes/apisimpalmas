package br.com.jlucasguedes.apisimpalmas.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jlucasguedes.apisimpalmas.domain.UnidadeEducacional;
import br.com.jlucasguedes.apisimpalmas.service.UnidadeEducacionalService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/unidades")
public class UnidadeEducacionalResource {

  @Autowired
  private UnidadeEducacionalService service;

  @GetMapping
  public ResponseEntity<List<UnidadeEducacional>> listarUnidades() {
    return ResponseEntity.ok().body(service.findAll());
  }

  @PostMapping("/sincronizar")
  public ResponseEntity<Void> sincronizar() {
    service.sincronizarUnidades();
    return ResponseEntity.noContent().build();
  }

}
