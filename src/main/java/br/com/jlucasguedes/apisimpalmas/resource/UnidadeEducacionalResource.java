package br.com.jlucasguedes.apisimpalmas.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jlucasguedes.apisimpalmas.service.UnidadeEducacionalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/unidades")
public class UnidadeEducacionalResource {

  @Autowired
  private UnidadeEducacionalService service;

  @GetMapping
  public ResponseEntity<String> getMethodName() {
    return ResponseEntity.ok().body(service.getUnidadesFromSimPalmas());
  }

}
