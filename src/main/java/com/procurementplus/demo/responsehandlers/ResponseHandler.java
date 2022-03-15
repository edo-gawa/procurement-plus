package com.procurementplus.demo.responsehandlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ResponseHandler{
	public static ResponseEntity<Object> generateResponse(String message, int statusCode, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("status", message);
            map.put("code", statusCode);
            map.put("message", responseObj);

            return new ResponseEntity<Object>(map,HttpStatus.OK);
	} 
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            
            Object rejectedVal = ((FieldError) error).getRejectedValue();
            String errorMessage = error.getDefaultMessage();
            errors.put("Invalid Value For Field < ".concat(fieldName).concat(" >"),"Rejected Value < ".concat(Optional.ofNullable(rejectedVal).orElse("null").toString()).concat(" >"));
        });
        return ResponseHandler.generateResponse("BAD_REQUEST", 3001, errors);

    }
}
