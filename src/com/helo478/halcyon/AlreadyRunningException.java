package com.helo478.halcyon;

// TODO: Auto-generated Javadoc
/**
 * The Class AlreadyListeningException.
 * 
 * @author Donald Subert
 */
public class AlreadyRunningException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4748527596678168015L;

	/**
	 * Instantiates a new already listening exception.
	 */
	public AlreadyRunningException() {
		super();
	}

	/**
	 * Instantiates a new already listening exception.
	 *
	 * @param message the message
	 */
	public AlreadyRunningException(final String message) {
		super(message);
	}

	/**
	 * Instantiates a new already listening exception.
	 *
	 * @param cause the cause
	 */
	public AlreadyRunningException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new already listening exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public AlreadyRunningException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new already listening exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public AlreadyRunningException(final String message,
			final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
