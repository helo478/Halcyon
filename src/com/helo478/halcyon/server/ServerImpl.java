package com.helo478.halcyon.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.helo478.halcyon.AlreadyRunningException;
import com.helo478.halcyon.HalcyonComponent;
import com.helo478.halcyon.controller.Controller;

public class ServerImpl implements HalcyonComponent {

	private static final String CLASS_NAME = ServerImpl.class.getName();
	private static final Logger logger = Logger.getLogger(CLASS_NAME);

	private volatile boolean listening = false;

	private final Controller controller;

	public ServerImpl(final Controller controller) {
		this.controller = controller;
	}

	@Override
	public void begin(final String host, final int port)
			throws AlreadyRunningException, IOException {

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

		if (getIsRunning()) {
			throw new AlreadyRunningException();
		}

		new Thread(new Runnable() {

			private ServerSocket serverSocket;

			public void run() {

				if (logger.isLoggable(Level.INFO)) {
					StringBuffer sb = new StringBuffer();
					sb.append(CLASS_NAME);
					sb.append("$Runnable.run() on thread: ");
					sb.append(Thread.currentThread());

					logger.info(sb.toString());
				}

				begin();

				try {
					serverSocket = new ServerSocket(port);

					StringBuffer sb = new StringBuffer();
					sb.append("Server: listening on port: ");
					sb.append(serverSocket.getLocalPort());
					final String message = sb.toString();

					logger.info(message);

					controller.outUI(message);

					while (listening) {
						acceptNewConnections();
					}
				} catch (final IOException ioe) {
					final String message = ioe.getMessage();
					logger.log(Level.SEVERE, message != null ? message : "",
							ioe);
				} finally {
					closeServerSocket();
				}

				logger.exiting(this.getClass().getName(), "run()");
			}

			private void acceptNewConnections() throws IOException {

				logger.entering(this.getClass().getName(),
						"acceptNewConnections()");

				final Socket socket = serverSocket.accept();

				Thread socketThread = new Thread(new SocketHandler(socket));
				socketThread.setDaemon(true);
				socketThread.start();

				logger.exiting(this.getClass().getName(),
						"acceptNewConnections()");
			}

			private void closeServerSocket() {

				logger.entering(this.getClass().getName(),
						"closeServerSocket()");

				while (serverSocket != null) {
					try {
						serverSocket.close();
					} catch (final IOException ioe) {

						final String message = "Unable to close serverSocket; "
								+ ioe.getMessage();

						if (logger.isLoggable(Level.WARNING)) {
							logger.log(Level.WARNING, message, ioe);
						}
					}
				}
				
				stop();

				logger.exiting(this.getClass().getName(), "closeServerSocket()");
			}
		}).start();
	}

	@Override
	public void stop() {

		logger.entering(CLASS_NAME, "stop()");

		listening = false;

		logger.exiting(CLASS_NAME, "stop()");
	}

	@Override
	public synchronized boolean getIsRunning() {

		boolean output = listening;

		if (logger.isLoggable(Level.INFO)) {
			StringBuffer sb = new StringBuffer();
			sb.append(CLASS_NAME);
			sb.append(".getIsRunning() returning: ");
			sb.append(output);

			logger.info(sb.toString());
		}

		return output;
	}

	private synchronized void begin() {

		logger.entering(CLASS_NAME, "setRunning()");

		listening = true;

		logger.exiting(CLASS_NAME, "setRunning()");
	}

	// TODO revise this
	private void fireNetworkListeners(final String line) {

		if (logger.isLoggable(Level.INFO)) {
			StringBuffer sb = new StringBuffer();
			sb.append(CLASS_NAME);
			sb.append(".fireNetworkListeners(String) for parameter: ");
			sb.append(line);
			sb.append("; on Thread: ");
			sb.append(Thread.currentThread());

			logger.info(sb.toString());
		}

		controller.outUI(line);

		logger.exiting(CLASS_NAME, "fireNetworkListeners(String)");
	}
	
	@Override
	public void outNetwork(final String message) {
		// TODO implement
	}

	private class SocketHandler implements Runnable {

		private Socket socket;

		private BufferedReader networkIn;
		private PrintWriter networkOut;

		private SocketHandler(final Socket socket) {
			this.socket = socket;
		}

		public void run() {

			if (logger.isLoggable(Level.INFO)) {
				StringBuffer sb = new StringBuffer();
				sb.append(this.getClass().getName());
				sb.append(".run() for Thread: ");
				sb.append(Thread.currentThread());

				logger.info(sb.toString());
			}

			try {
				while (true) { // TODO maybe change this to while (running)
					networkIn = new BufferedReader(new InputStreamReader(
							socket.getInputStream()));
					networkOut = new PrintWriter(socket.getOutputStream());

					String received = null;

					while ((received = networkIn.readLine()) == null) {
					}

					handleReceived(received);
				}
			} catch (final IOException ioe) {
				final String message = "Unable to communicate using socket; "
						+ ioe.getMessage();
				logger.log(Level.SEVERE, message, ioe);
			} finally {

				networkOut.close();

				try {
					networkIn.close();
				} catch (final IOException ioe) {
					final String message = "Unable to close BufferedReader; "
							+ ioe.getMessage();
					logger.log(Level.WARNING, message, ioe);
				}
			}

			logger.exiting(this.getClass().getName(), "run()");
		}

		private void handleReceived(final String received) {

			if (logger.isLoggable(Level.INFO)) {
				StringBuffer sb = new StringBuffer();
				sb.append(this.getClass().getName());
				sb.append(".handleReceived(String) for parameter: ");
				sb.append(received);
				sb.append("; on Thread: ");
				sb.append(Thread.currentThread());
			}

			fireNetworkListeners(received);

			logger.exiting(this.getClass().getName(), "handleReceived(String)");
		}
	}
}
