package com.helo478.halcyon.ui;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.helo478.halcyon.NetworkException;
import com.helo478.halcyon.NetworkListener;
import com.helo478.halcyon.util.CmdInputParser;

public class SimpleUserInterface implements UserInterface {

	private static final String CLASS_NAME = SimpleUserInterface.class
			.getName();
	private static final Logger logger = Logger.getLogger(CLASS_NAME);

	private boolean isServer = false;

	private final List<InputListener> inputListeners;

	private final NetworkListener networkListener = new NetworkListener() {

		@Override
		public void outUI(final String line) {

			if (logger.isLoggable(Level.INFO)) {
				StringBuffer sb = new StringBuffer();
				sb.append(this.getClass().getName());
				sb.append("actionPerformed(String) with argument: ");
				sb.append(line);

				logger.info(sb.toString());
			}

			System.out.println(line);

			logger.exiting(this.getClass().getName(), "actionPerformed(String)");
		}
	};

	public SimpleUserInterface() {

		logger.entering(CLASS_NAME, "NO_ARG_CONSTRUCTOR");

		inputListeners = new ArrayList<InputListener>();

		logger.exiting(CLASS_NAME, "NO_ARG_CONSTRUCTOR");
	}

	@Override
	public String toString() {

		logger.entering(CLASS_NAME, "toString()");

		return "A Simple User Interface";
	}

	@Override
	public void go(final String[] input) {

		if (logger.isLoggable(Level.INFO)) {
			StringBuffer sb = new StringBuffer();
			sb.append(CLASS_NAME);
			sb.append(".go(String[]) with parameter: {");
			for (String s : input) {
				sb.append("\"");
				sb.append(s);
				sb.append("\" ");
			}
			sb.append("}");

			logger.info(sb.toString());
		}

		try {
			CmdInputParser.parseInput(this, input);
			defaultToClient();
			begin();
		} catch (final NetworkException he) {
			final String message = "Unable to create a connection; "
					+ he.getMessage();
			logger.log(Level.INFO, message, he);
			System.out.println(message);
		} catch (final InvalidInputException iie) {
			final String message = "Invalid Input; " + iie.getMessage();
			logger.log(Level.INFO, message, iie);
			System.out.println(message);
		} catch (final UnknownHostException uhe) {
			final String message = "Unknown Host; " + uhe.getMessage();
			logger.log(Level.INFO, message, uhe);
			System.out.println(message);
		}

		logger.exiting(CLASS_NAME, "go(String[])");
	}

	private void defaultToClient() throws InvalidInputException {

		logger.entering(CLASS_NAME, "defaultToClient()");

		if (getIsServer() == false) {
			createClient();
		}
		
		logger.exiting(CLASS_NAME, "defaultToClient()");
	}

	private boolean getIsServer() {
		return isServer;
	}

	private void setIsServer(final boolean b) {
		isServer = b;
	}

	@Override
	public Collection<InputListener> getInputListeners() {

		logger.entering(CLASS_NAME, "getInputListeners()");

		return inputListeners;
	}

	@Override
	public void addInputListener(final InputListener listener) {

		if (logger.isLoggable(Level.INFO)) {
			StringBuffer sb = new StringBuffer();
			sb.append(CLASS_NAME);
			sb.append(".addInputListener(InputListener) with parameter: ");
			sb.append(listener);

			logger.info(sb.toString());
		}

		inputListeners.add(listener);

		logger.exiting(CLASS_NAME, "addInputListener(InputListener)");
	}

	@Override
	public boolean removeInputListener(final InputListener listener) {

		if (logger.isLoggable(Level.INFO)) {
			StringBuffer sb = new StringBuffer();
			sb.append(CLASS_NAME);
			sb.append(".removeInputListener(InputListener) with parameter: ");
			sb.append(listener);

			logger.info(sb.toString());
		}

		return inputListeners.remove(listener);
	}

	@Override
	public NetworkListener getNetworkListener() {

		logger.entering(CLASS_NAME, "getNetworkListener()");

		return networkListener;
	}

	@Override
	public void setHost(final String host) throws InvalidInputException {

		if (logger.isLoggable(Level.INFO)) {
			StringBuffer sb = new StringBuffer();
			sb.append(CLASS_NAME);
			sb.append(".setHost(String) with parameter: ");
			sb.append(host);

			logger.info(sb.toString());
		}

		for (InputListener listener : inputListeners) {
			listener.setHost(host);
		}

		logger.exiting(CLASS_NAME, "setHost()");
	}

	@Override
	public void setPort(final int port) throws InvalidInputException {

		if (logger.isLoggable(Level.INFO)) {
			StringBuffer sb = new StringBuffer();
			sb.append(CLASS_NAME);
			sb.append(".setPort(int) with parameter: ");
			sb.append(String.valueOf(port));

			logger.info(sb.toString());
		}

		for (InputListener listener : inputListeners) {
			listener.setPort(port);
		}

		logger.exiting(CLASS_NAME, "setPort(int)");
	}

	@Override
	public void createClient() throws InvalidInputException {

		logger.entering(CLASS_NAME, "createClient()");

		for (InputListener listener : inputListeners) {
			listener.createClient();
		}

		logger.exiting(CLASS_NAME, "createClient()");
	}

	@Override
	public void createServer() throws InvalidInputException {

		logger.entering(CLASS_NAME, "createServer()");

		setIsServer(true);

		for (InputListener listener : inputListeners) {
			listener.createServer();
		}

		logger.exiting(CLASS_NAME, "createServer()");
	}

	@Override
	public void begin() throws NetworkException, UnknownHostException,
			InvalidInputException {

		logger.entering(CLASS_NAME, "begin()");

		for (InputListener listener : inputListeners) {
			listener.begin();
		}

		logger.exiting(CLASS_NAME, "begin()");
	}

	@Override
	public void stop() {

		logger.entering(CLASS_NAME, "stop()");

		for (InputListener listener : inputListeners) {
			listener.stop();
		}

		logger.exiting(CLASS_NAME, "stop()");
	}

	@Override
	public void sendMessage(final String message) {

		if (logger.isLoggable(Level.INFO)) {
			StringBuffer sb = new StringBuffer();
			sb.append(CLASS_NAME);
			sb.append(".sendMessage(String) with parameter: ");
			sb.append(message);

			logger.info(sb.toString());
		}

		for (InputListener listener : inputListeners) {
			listener.outNetwork(message);
		}

		logger.exiting(CLASS_NAME, "sendMessage(String)");
	}

	@Override
	public void println(final String line) {

		if (logger.isLoggable(Level.INFO)) {
			StringBuffer sb = new StringBuffer();
			sb.append(CLASS_NAME);
			sb.append(".println(String) with parameter: ");
			sb.append(line);

			logger.info(sb.toString());
		}

		System.out.println(line);

		logger.exiting(CLASS_NAME, "println()");
	}
}
