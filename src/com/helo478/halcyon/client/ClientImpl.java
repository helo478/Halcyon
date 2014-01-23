package com.helo478.halcyon.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.helo478.halcyon.HalcyonComponent;
import com.helo478.halcyon.controller.Controller;

public class ClientImpl implements HalcyonComponent {

	private static final String CLASS_NAME = ClientImpl.class.getName();
	private static final Logger logger = Logger.getLogger(CLASS_NAME);

	private Socket socket;

	private final Controller controller;

	public ClientImpl(final Controller controller) {
		this.controller = controller;
	}

	@Override
	public void begin(final String host, final int port)
			throws UnknownHostException, IOException {

		if (logger.isLoggable(Level.INFO)) {
			StringBuffer sb = new StringBuffer();
			sb.append("Entering: ");
			sb.append(CLASS_NAME);
			sb.append(".begin(String, int) with arguments: ");
			sb.append(host);
			sb.append(", ");
			sb.append(String.valueOf(port));

			logger.info(sb.toString());
		}

		socket = new Socket(host, port);

		// TODO: handle connection issues gracefully

		PrintWriter outNetwork = new PrintWriter(socket.getOutputStream());
		outNetwork.println("test");
		outNetwork.flush();
		outNetwork.close();

		logger.exiting(CLASS_NAME, "begin");
	}

	@Override
	public void stop() {

		logger.entering(CLASS_NAME, "stop()");

		// no implementation needed
	}

	@Override
	public boolean getIsRunning() {

		logger.entering(CLASS_NAME, "getIsRunning()");

		return false;
	}
	
	@Override
	public void outNetwork(final String message) {
		// TODO implement
	}
}
