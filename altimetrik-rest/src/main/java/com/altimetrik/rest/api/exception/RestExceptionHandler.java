package com.altimetrik.rest.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.altimetrik.rest.api.model.Response;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class) 
	public ResponseEntity <Object> notFound(ResourceNotFoundException notFound){
		Response res = new Response(HttpStatus.NOT_FOUND.value(),notFound.getMessage());
		return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	  public ResponseEntity<Response> handleMaxSizeException(MaxUploadSizeExceededException exc) {
	    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Response(HttpStatus.EXPECTATION_FAILED.value(),"File too large!"));
	  }

}
