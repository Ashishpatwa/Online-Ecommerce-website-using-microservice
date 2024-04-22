package com.example.InventoryService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.InventoryService.payload.ApiResponse;

@RestControllerAdvice
public class GlobalException{
	
	@ExceptionHandler(InsufficentProductQuantity.class)
	public ResponseEntity<ApiResponse> handleInsufficentProductQuantity(InsufficentProductQuantity ex){
		
		ApiResponse response = new ApiResponse();
		response.setMessage(ex.getMessage());
		response.setHttpStatus(HttpStatus.NOT_FOUND);
		response.setSuccess(true);
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		
	}
	
	

}
