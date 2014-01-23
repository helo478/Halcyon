package com.helo478.halcyon.ui;

import com.helo478.halcyon.NetworkException;

public interface InputListener {
	
	void createClient() throws InvalidInputException;
	
	void createServer() throws InvalidInputException;

	void setHost(String host) throws InvalidInputException;

	void setPort(int parameter) throws InvalidInputException;

	void begin() throws NetworkException, InvalidInputException;

	void stop();
	
	boolean getIsRunning();
	
	void outNetwork(String message);
}
