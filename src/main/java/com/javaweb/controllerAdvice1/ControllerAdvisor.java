package com.javaweb.controllerAdvice1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.Beans.ErrorResponseDTO;
import com.javaweb.customexception.FieldRequiredException;


@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
	@ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Object> handleArithmeticException(
    		ArithmeticException ex, WebRequest request) {
		ErrorResponseDTO errorRespomseDTO = new ErrorResponseDTO();
		errorRespomseDTO.setError(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("số nguyên chia 0 kiểu gì");
		errorRespomseDTO.setDetail(details);

        return new ResponseEntity<>(errorRespomseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	@ExceptionHandler(FieldRequiredException.class)
    public ResponseEntity<Object> handleFieldRequiredException(
    		FieldRequiredException ex, WebRequest request) {
		ErrorResponseDTO errorRespomseDTO = new ErrorResponseDTO();
		errorRespomseDTO.setError(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("check lại name hoặc number");
		errorRespomseDTO.setDetail(details);

        return new ResponseEntity<>(errorRespomseDTO, HttpStatus.BAD_GATEWAY);
    }
}
