package com.helo478.halcyon;

import java.io.IOException;
import java.net.UnknownHostException;

import com.helo478.halcyon.controller.Controller;

/**
 * The Interface HalcyonComponent.
 * 
 * @author Donald Subert
 */
public interface HalcyonComponent {

	void begin(String host, int port) throws AlreadyRunningException,
			UnknownHostException, IOException;

	/**
	 * Stops running the Halcyon Component.
	 */
	void stop();

	/**
	 * Checks if the Halcyon Component is running.
	 * 
	 * @return true if the Halcoyon Component is running
	 */
	boolean getIsRunning();

	void outNetwork(String message);
}