package com.factorypro.grammer.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.factorypro.grammer.domain.exception.BusinessException;
import com.factorypro.grammer.domain.exception.NotFoundEntitiyException;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ProblemsMessage.Field> fields = new ArrayList<>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			fields.add(new ProblemsMessage.Field(name, message));
		}
		
		ProblemsMessage problem = new ProblemsMessage();
		problem.setStatus(status.value());
		problem.setDateHour(OffsetDateTime.now());
		problem.setTitle("Um ou mais campos estão preechidos de forma incorreta.");
		problem.setField(fields);
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request){
	
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ProblemsMessage problem = new ProblemsMessage();
		problem.setStatus(status.value());
		problem.setDateHour(OffsetDateTime.now());
		problem.setTitle(ex.getMessage());
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
	
	@ExceptionHandler(NotFoundEntitiyException.class)
	public ResponseEntity<Object> handleNotFoudEntity(NotFoundEntitiyException ex, WebRequest request){
	
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ProblemsMessage problem = new ProblemsMessage();
		problem.setStatus(status.value());
		problem.setDateHour(OffsetDateTime.now());
		problem.setTitle(ex.getMessage());
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
	
	

}
