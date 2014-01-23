package com.helo478.halcyon.controller;

/**
 * The Class ControllerException.
 * 
 * @author Donald Subert
 */
public class ControllerException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9132396117281570740L;

	/**
	 * Instantiates a new controller exception.
	 */
	public ControllerException() {
		super();
	}

	/**
	 * Instantiates a new controller exception.
	 * 
	 * @param message
	 *            the message
	 */
	public ControllerException(final String message) {
		super(message);
	}

	/**
	 * Instantiates a new controller exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public ControllerException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new controller exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public ControllerException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new controller exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 * @param enableSuppression
	 *            the enable suppression
	 * @param writableStackTrace
	 *            the writable stack trace
	 */
	public ControllerException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
