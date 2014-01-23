package com.helo478.halcyon.app;

import com.helo478.halcyon.controller.Controller;
import com.helo478.halcyon.controller.ControllerImpl;
import com.helo478.halcyon.ui.InputListener;
import com.helo478.halcyon.ui.SimpleUserInterface;
import com.helo478.halcyon.ui.UserInterface;

public class Main {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		
		Controller controller = new ControllerImpl();
		InputListener inputListener = (InputListener) controller;
		UserInterface ui = new SimpleUserInterface();
		
		ui.addInputListener(inputListener);
		
		controller.addNetworkListener(ui.getNetworkListener());

		ui.go(args);
	}
}
