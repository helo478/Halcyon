package com.helo478.halcyon.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.helo478.halcyon.ui.InvalidInputException;
import com.helo478.halcyon.ui.UserInterface;

public class CmdInputParser {

	private static final Logger logger = Logger.getLogger(CmdInputParser.class
			.getName());

	public static void parseInput(final UserInterface userInterface,
			final String[] input) throws InvalidInputException {

		if (input == null) {
			return;
		}

		List<String> inputList = new ArrayList<String>();

		for (String s : input) {
			inputList.add(s);
		}

		Iterator<String> iterator = inputList.iterator();

		while (iterator.hasNext()) {

			String commandName = iterator.next();

			Command command = Command.get(commandName);

			if (command != null) {
				command.processCommand(userInterface, iterator);
			} else {
				throw new InvalidInputException();
			}
		}
	}

	private enum Command {
		SERVER("-s") {
			@Override
			protected void processCommand(final UserInterface ui,
					final Iterator<String> commands)
					throws InvalidInputException {

				if (logger.isLoggable(Level.INFO)) {
					StringBuffer sb = new StringBuffer();
					sb.append(CmdInputParser.class.getName());
					sb.append(".");
					sb.append(Command.class.getName());
					sb.append(".processCommand SERVER");

					logger.info(sb.toString());
				}

				ui.createServer();
			}
		},

		HOST("-h") {
			@Override
			protected void processCommand(final UserInterface ui,
					final Iterator<String> commands)
					throws InvalidInputException {

				if (logger.isLoggable(Level.INFO)) {
					StringBuffer sb = new StringBuffer();
					sb.append(CmdInputParser.class.getName());
					sb.append(".");
					sb.append(Command.class.getName());
					sb.append(".processCommand HOST");

					logger.info(sb.toString());
				}
				
				ui.setHost(commands.next());
			}
		},

		PORT("-p") {
			@Override
			protected void processCommand(final UserInterface ui,
					final Iterator<String> commands)
					throws InvalidInputException {

				if (logger.isLoggable(Level.INFO)) {
					StringBuffer sb = new StringBuffer();
					sb.append(CmdInputParser.class.getName());
					sb.append(".");
					sb.append(Command.class.getName());
					sb.append(".processCommand PORT");

					logger.info(sb.toString());
				}
				
				try {
					final int port = Integer.parseInt(commands.next());
					ui.setPort(port);
				} catch (final NumberFormatException nfe) {
					throw new InvalidInputException(nfe);
				}
			}
		};

		private final String token;

		private Command(final String token) {
			this.token = token;
		}

		private String getName() {
			return token;
		}

		private static Command get(final String token) {
			for (Command c : values()) {
				if (c.getName().equals(token)) {
					return c;
				}
			}

			return null;
		}

		protected abstract void processCommand(final UserInterface ui,
				final Iterator<String> commands) throws InvalidInputException;
	}
}
