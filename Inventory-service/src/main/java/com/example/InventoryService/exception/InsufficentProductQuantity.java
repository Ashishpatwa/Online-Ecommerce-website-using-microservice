package com.example.InventoryService.exception;

public class InsufficentProductQuantity extends RuntimeException{
	
	public InsufficentProductQuantity(String message) {
		super(message);
	}
	
}
