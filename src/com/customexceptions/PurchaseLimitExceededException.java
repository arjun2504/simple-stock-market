package com.customexceptions;

public class PurchaseLimitExceededException extends Exception {
	public String getMessage() {
		return "A transaction cannot exceed Rs. 1,00,000";
	}
}
