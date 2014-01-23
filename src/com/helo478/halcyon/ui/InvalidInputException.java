package com.helo478.halcyon.ui;

public class InvalidInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -722037657481583381L;

	public InvalidInputException() {
		super();
	}

	public InvalidInputException(final String message) {
		super(message);
	}

	public InvalidInputException(final Throwable cause) {
		super(cause);
	}

	public InvalidInputException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidInputException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
