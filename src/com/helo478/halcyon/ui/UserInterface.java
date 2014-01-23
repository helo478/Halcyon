package com.helo478.halcyon.ui;

import java.net.UnknownHostException;
import java.util.Collection;

import com.helo478.halcyon.NetworkException;
import com.helo478.halcyon.NetworkListener;

/**
 * The Interface UserInterface.
 * 
 * @author Donald Subert
 */
public interface UserInterface {

	/**
	 * Begin processing the user input.
	 * 
	 * @param args
	 *            the command line arguments
	 */
	void go(String[] args);

	/**
	 * Gets the collection of input listeners.
	 * 
	 * @return the collection of input listeners
	 */
	Collection<InputListener> getInputListeners();

	/**
	 * Adds the input listener to the collection of input listeners.
	 * 
	 * @param listener
	 *            the listener
	 */
	void addInputListener(InputListener listener);

	/**
	 * Removes the input listener from the collection of input listeners if
	 * present.
	 * 
	 * @param mockInputListener
	 *            the mock input listener
	 * @return true, if successful
	 */
	boolean removeInputListener(InputListener mockInputListener);

	/**
	 * Gets the singleton network listener for this instance of a UserInterface.
	 * 
	 * @return the network listener
	 */
	NetworkListener getNetworkListener();

	/**
	 * Sets the host.
	 * 
	 * @param host
	 *            the new host
	 * @throws InvalidInputException
	 *             the invalid input exception
	 */
	void setHost(String host) throws InvalidInputException;

	/**
	 * Sets the port.
	 * 
	 * @param port
	 *            the new port
	 * @throws InvalidInputException
	 *             the invalid input exception
	 */
	void setPort(int port) throws InvalidInputException;

	/**
	 * Creates a Halcyon Client.
	 * 
	 * @throws InvalidInputException
	 *             the invalid input exception
	 */
	void createClient() throws InvalidInputException;

	/**
	 * Creates a Halcyon Server.
	 * 
	 * @throws InvalidInputException
	 *             the invalid input exception
	 */
	void createServer() throws InvalidInputException;

	/**
	 * Begin running the Halcyon Component.
	 * 
	 * @throws InvalidInputException
	 *             the invalid input exception
	 * @throws UnknownHostException
	 *             the unknown host exception
	 * @throws NetworkException
	 *             the halcyon exception
	 */
	void begin() throws InvalidInputException, UnknownHostException,
			NetworkException;

	/**
	 * Stop running the Halcyon Component.
	 */
	void stop();

	/**
	 * Send a text message to a network connected program.
	 * 
	 * @param message
	 *            the message
	 */
	void sendMessage(String message);

	/**
	 * Print a line of text to the output
	 * 
	 * @param line
	 *            the line
	 */
	void println(String line);
}
