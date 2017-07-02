package com.customexceptions;

public class InsufficientSharesException extends Exception {
	public String getMessage() {
		return "Insufficient Shares";
	}
}
