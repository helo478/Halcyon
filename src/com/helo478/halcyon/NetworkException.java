package com.helo478.halcyon;

/**
 * The Class HalcyonException.
 * 
 * @author Donald Subert
 */
public class NetworkException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9078293352816077108L;

	/**
	 * Instantiates a new halcyon exception.
	 */
	public NetworkException() {
		super();
	}

	/**
	 * Instantiates a new halcyon exception.
	 *
	 * @param message the message
	 */
	public NetworkException(final String message) {
		super(message);
	}

	/**
	 * Instantiates a new halcyon exception.
	 *
	 * @param cause the cause
	 */
	public NetworkException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new halcyon exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public NetworkException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new halcyon exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public NetworkException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
