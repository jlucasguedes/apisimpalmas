package br.com.jlucasguedes.apisimpalmas.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jlucasguedes.apisimpalmas.domain.Serie;
import br.com.jlucasguedes.apisimpalmas.service.SerieService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/series")
public class SerieResource {
  @Autowired
  private SerieService service;

  @GetMapping("/ano/{ano}/unidade/{idUnidade}")
  public ResponseEntity<List<Serie>> listar(@PathVariable String ano, @PathVariable String idUnidade) {
    return ResponseEntity.ok().body(service.listarByUnidadeEducacionalId(ano, idUnidade));
  }

}
