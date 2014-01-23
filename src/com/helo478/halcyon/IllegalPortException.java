package com.helo478.halcyon;

/**
 * The Class IllegalPortException.
 * 
 * @author Donald Subert
 */
public class IllegalPortException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5725799013077764504L;

	/**
	 * Instantiates a new illegal port exception.
	 */
	public IllegalPortException() {
		super();
	}

	/**
	 * Instantiates a new illegal port exception.
	 *
	 * @param message the message
	 */
	public IllegalPortException(final String message) {
		super(message);
	}

	/**
	 * Instantiates a new illegal port exception.
	 *
	 * @param cause the cause
	 */
	public IllegalPortException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new illegal port exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public IllegalPortException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new illegal port exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public IllegalPortException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
