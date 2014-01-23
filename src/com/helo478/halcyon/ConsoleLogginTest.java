package com.helo478.halcyon;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ConsoleLogginTest {
	
	public static void main (String[] args) {
		new ConsoleLogginTest().go();
	}

	private void go() {
	Logger logger = Logger.getLogger("my.logger");
	logger.setLevel(Level.ALL);
	ConsoleHandler handler = new ConsoleHandler();
	handler.setFormatter(new SimpleFormatter());
	handler.setLevel(Level.ALL);
	logger.addHandler(handler);
	logger.fine("hello world");
	}
}
