package com.helo478.halcyon.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.helo478.halcyon.AlreadyRunningException;
import com.helo478.halcyon.HalcyonComponent;
import com.helo478.halcyon.NetworkException;
import com.helo478.halcyon.NetworkListener;
import com.helo478.halcyon.client.ClientImpl;
import com.helo478.halcyon.server.ServerImpl;
import com.helo478.halcyon.ui.InputListener;
import com.helo478.halcyon.ui.InvalidInputException;

public class ControllerImpl implements Controller, InputListener {

	private static final String CLASS_NAME = ControllerImpl.class.getName();
	private static final Logger logger = Logger.getLogger(CLASS_NAME);

	private static final int MAX_PORT = 65535;
	private static final int MIN_PORT = 1;

	private static final String LOCAL_HOST = "127.0.0.1";

	private int port = 1;
	private String host = LOCAL_HOST;

	private HalcyonComponent halcyonComponent;

	private List<NetworkListener> networkListeners;

	public ControllerImpl() {
		networkListeners = new ArrayList<NetworkListener>();
	}

	/**
	 * Gets the current Halcyon component. This should be used only for testing
	 * purposes.
	 * 
	 * @return the current Halcyon component
	 */
	protected HalcyonComponent getHalcyonComponent() {
		return this.halcyonComponent;
	}

	/**
	 * Sets the current Halcyon component. This should be used only for testing
	 * purposes.
	 * 
	 * @param the
	 *            new Halcyon component
	 */
	protected void setHalcyonComponent(HalcyonComponent component) {
		this.halcyonComponent = component;
	}

	@Override
	public void createClient() throws InvalidInputException {

		if (getIsRunning()) {
			throw new InvalidInputException(
					"Unable to create a new client because Halcyon is currently running");
		}

		halcyonComponent = new ClientImpl(this);

		outUI("Client created");
	}

	@Override
	public void createServer() throws InvalidInputException {

		if (halcyonComponent != null && halcyonComponent.getIsRunning()) {
			throw new InvalidInputException(
					"Cannot create a server because there is a Halcyon Component currently running");
		}

		halcyonComponent = new ServerImpl(this);

		outUI("Server created");
	}

	protected String getHost() {
		return host;
	}

	@Override
	public void setHost(final String host) throws InvalidInputException {
		if (host == null) {
			throw new InvalidInputException("Illegal host: null");
		} else {
			this.host = host;

			outUI("Host set to: " + this.host);
		}
	}

	protected int getPort() {
		return port;
	}

	@Override
	public void setPort(final int port) throws InvalidInputException {

		if (port > MAX_PORT || port < MIN_PORT) {
			StringBuffer sb = new StringBuffer();
			sb.append("Illegal port: ");
			sb.append(String.valueOf(port));

			throw new InvalidInputException(sb.toString());
		} else {
			ControllerImpl.this.port = port;

			outUI("Port set to: " + String.valueOf(this.port));
		}
	}

	@Override
	public void begin() throws InvalidInputException, NetworkException {

		if (halcyonComponent == null) {
			final String message = "Unable to begin; No Halcyon component loaded";
			logger.info(message);
			outUI(message);
			return;
		}

		try {
			halcyonComponent.begin(host, port);
		} catch (final AlreadyRunningException are) {
			final String message = "Cannot begin because there is a Halcyon Component currently running; "
					+ are.getMessage();
			logger.log(Level.INFO, message, are);
			throw new InvalidInputException(message, are);
		} catch (final IOException ioe) {
			final String message = "There is a problem communicating across the network; "
					+ ioe.getMessage();
			logger.info(message);
			throw new NetworkException(message, ioe);
		}

		outUI("Halcyon is running");
	}

	@Override
	public void stop() {
		if (halcyonComponent == null) {
			return;
		}

		halcyonComponent.stop();

		outUI("Halcyon is stopped");
	}

	@Override
	public boolean getIsRunning() {
		if (halcyonComponent == null) {
			return false;
		} else {
			return halcyonComponent.getIsRunning();
		}
	}

	@Override
	public void outNetwork(final String message) {
		halcyonComponent.outNetwork(message);
	}

	@Override
	public Collection<NetworkListener> getNetworkListeners() {
		return networkListeners;
	}

	@Override
	public void addNetworkListener(final NetworkListener listener) {
		networkListeners.add(listener);
	}

	@Override
	public boolean removeNetworkListener(final NetworkListener listener) {
		return networkListeners.remove(listener);
	}

	@Override
	public void outUI(final String line) {
		for (NetworkListener listener : networkListeners) {
			listener.outUI(line);
		}
	}
}
