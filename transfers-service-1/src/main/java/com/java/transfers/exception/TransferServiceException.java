package com.java.transfers.exception;

public class TransferServiceException extends RuntimeException{
	private static final long serialVersionUID = -9174255649313131185L;

	public TransferServiceException(String message) {
		super(message);
	}

	public TransferServiceException(Throwable cause) {
		super(cause);
	}
}