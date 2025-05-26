package br.com.jlucasguedes.apisimpalmas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptioHandler {
  @ExceptionHandler(UnidadeNaoEncontradaException.class)
  public ResponseEntity<DetalheErro> handlerUnidadeNaoEncontradaException(UnidadeNaoEncontradaException e,
      HttpServletRequest request) {

    DetalheErro erro = new DetalheErro();
    erro.setStatus(404l);
    erro.setTitulo(e.getMessage());
    erro.setMensagemDesenvolvedor("https://localhost:8080/erros/404");
    erro.setTimestamp(System.currentTimeMillis());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
  }

  @ExceptionHandler(SerieNaoEncontradaException.class)
  public ResponseEntity<DetalheErro> handlerSerieNaoEncontradaException(SerieNaoEncontradaException e,
      HttpServletRequest request) {

    DetalheErro erro = new DetalheErro();
    erro.setStatus(404l);
    erro.setTitulo(e.getMessage());
    erro.setMensagemDesenvolvedor("https://localhost:8080/erros/404");
    erro.setTimestamp(System.currentTimeMillis());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
  }

  @ExceptionHandler(ClassificacaoNaoLiberadaException.class)
  public ResponseEntity<DetalheErro> handlerClassificacaoEncontradaException(ClassificacaoNaoLiberadaException e,
      HttpServletRequest request) {

    DetalheErro erro = new DetalheErro();
    erro.setStatus(404l);
    erro.setTitulo(e.getMessage());
    erro.setMensagemDesenvolvedor("https://localhost:8080/erros/404");
    erro.setTimestamp(System.currentTimeMillis());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
  }

  @ExceptionHandler(CadastroNaoEncontradoException.class)
  public ResponseEntity<DetalheErro> handlerCadastroNaoEncontradoException(CadastroNaoEncontradoException e,
      HttpServletRequest request) {

    DetalheErro erro = new DetalheErro();
    erro.setStatus(404l);
    erro.setTitulo(e.getMessage());
    erro.setMensagemDesenvolvedor("https://localhost:8080/erros/404");
    erro.setTimestamp(System.currentTimeMillis());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
  }
}
