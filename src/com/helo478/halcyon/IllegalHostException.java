package com.helo478.halcyon;

/**
 * The Class IllegalHostException.
 * 
 * @author Donald Subert
 */
public class IllegalHostException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2964724019880416596L;

	/**
	 * Instantiates a new illegal host exception.
	 */
	public IllegalHostException() {
		super();
	}

	/**
	 * Instantiates a new illegal host exception.
	 * 
	 * @param message
	 *            the message
	 */
	public IllegalHostException(final String message) {
		super(message);
	}

	/**
	 * Instantiates a new illegal host exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public IllegalHostException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new illegal host exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public IllegalHostException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new illegal host exception.
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
	public IllegalHostException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
