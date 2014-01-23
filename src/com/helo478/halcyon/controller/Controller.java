package com.helo478.halcyon.controller;

import java.util.Collection;

import com.helo478.halcyon.NetworkListener;

/**
 * The Interface Controller.
 * 
 * @author Donald Subert
 */
public interface Controller {

	/**
	 * Gets the collection of network listeners.
	 * 
	 * @return the network listeners
	 */
	Collection<NetworkListener> getNetworkListeners();

	/**
	 * Adds a network listener to the collection of network listeners.
	 * 
	 * @param listener
	 *            the network listener
	 */
	void addNetworkListener(NetworkListener listener);

	/**
	 * Removes a network listener from the collection of network listeners.
	 * 
	 * @param listener
	 *            the network listener
	 * @return true, if successfully removed
	 */
	boolean removeNetworkListener(NetworkListener listener);

	/**
	 * Out to user interface.
	 * 
	 * @param line
	 *            the line to output
	 */
	void outUI(String line);
}
