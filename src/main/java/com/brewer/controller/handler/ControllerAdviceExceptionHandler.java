package com.brewer.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.brewer.service.exception.NomeEstiloJaCadastradoExcetion;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	@ExceptionHandler(NomeEstiloJaCadastradoExcetion.class)
	public ResponseEntity<String> handleNomeEstiloJaCadastradoException(NomeEstiloJaCadastradoExcetion e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
