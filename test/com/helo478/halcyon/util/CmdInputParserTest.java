package com.helo478.halcyon.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.helo478.halcyon.ui.InvalidInputException;
import com.helo478.halcyon.ui.UserInterface;

public class CmdInputParserTest {

	private static final int USABLE_PORT = 35664;
	private static final String TEST_HOST = "google.com";
	private static final String NON_NUMERIC_STRING = "non-numeric string";

	@Mock
	UserInterface mockUserInterface;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void parseInputTest_null_shouldNotError()
			throws InvalidInputException {
		CmdInputParser.parseInput(mockUserInterface, null);
	}

	@Test
	public void parseInputTest_setPort_usablePort()
			throws InvalidInputException {

		final String[] parameter = { "-p", String.valueOf(USABLE_PORT) };
		final int expected = USABLE_PORT;

		CmdInputParser.parseInput(mockUserInterface, parameter);

		Mockito.verify(mockUserInterface, Mockito.times(1)).setPort(expected);
	}

	@Test
	public void parseInputTest_setPort_badPortNumber_shouldNotError()
			throws InvalidInputException {

		final int port = -1;
		final String[] parameter = { "-p", String.valueOf(port) };

		CmdInputParser.parseInput(mockUserInterface, parameter);

		Mockito.verify(mockUserInterface, Mockito.times(1)).setPort(port);
	}

	@Test(expected = InvalidInputException.class)
	public void parseInputTest_setPort_nonNumberFormat_shouldError()
			throws InvalidInputException {

		final String[] parameter = { "-p", NON_NUMERIC_STRING };

		CmdInputParser.parseInput(mockUserInterface, parameter);

		Mockito.verifyZeroInteractions(mockUserInterface);
	}

	@Test
	public void parseInputTest_setHost_testHost() throws InvalidInputException {

		final String[] parameter = { "-h", String.valueOf(TEST_HOST) };

		CmdInputParser.parseInput(mockUserInterface, parameter);

		Mockito.verify(mockUserInterface, Mockito.times(1)).setHost(TEST_HOST);

		Mockito.verify(mockUserInterface, Mockito.never()).createServer();
	}

	@Test
	public void parseInputTest_createServer() throws InvalidInputException {

		final String[] parameter = { "-s" };

		CmdInputParser.parseInput(mockUserInterface, parameter);

		Mockito.verify(mockUserInterface, Mockito.times(1)).createServer();
	}

	@Test
	public void parseInputTest_multipleCommands_server_port()
			throws InvalidInputException {

		final String[] parameter = { "-p", String.valueOf(USABLE_PORT), "-s" };

		CmdInputParser.parseInput(mockUserInterface, parameter);

		Mockito.verify(mockUserInterface, Mockito.times(1))
				.setPort(USABLE_PORT);
		Mockito.verify(mockUserInterface, Mockito.times(1)).createServer();
	}

	@Test
	public void parseInputTest_multipleCommands_port_server()
			throws InvalidInputException {

		final String[] parameter = { "-s", "-p", String.valueOf(USABLE_PORT) };

		CmdInputParser.parseInput(mockUserInterface, parameter);

		Mockito.verify(mockUserInterface, Mockito.times(1))
				.setPort(USABLE_PORT);
		Mockito.verify(mockUserInterface, Mockito.times(1)).createServer();
	}

	@Test
	public void parseInputTest_multipleCommands_host_port()
			throws InvalidInputException {

		final String[] parameter = { "-h", TEST_HOST, "-p",
				String.valueOf(USABLE_PORT) };

		CmdInputParser.parseInput(mockUserInterface, parameter);

		Mockito.verify(mockUserInterface, Mockito.times(1)).setHost(TEST_HOST);
		Mockito.verify(mockUserInterface, Mockito.times(1))
				.setPort(USABLE_PORT);
	}

	@Test
	public void parseInputTest_multipleCommands_port_host()
			throws InvalidInputException {

		final String[] parameter = { "-p", String.valueOf(USABLE_PORT), "-h",
				TEST_HOST };

		CmdInputParser.parseInput(mockUserInterface, parameter);

		Mockito.verify(mockUserInterface, Mockito.times(1))
				.setPort(USABLE_PORT);
		Mockito.verify(mockUserInterface, Mockito.times(1)).setHost(TEST_HOST);
	}

	@Test
	public void parseInputTest_multipleCommands_host_port_server()
			throws InvalidInputException {

		final String[] parameter = { "-h", TEST_HOST, "-p",
				String.valueOf(USABLE_PORT), "-s" };

		CmdInputParser.parseInput(mockUserInterface, parameter);

		Mockito.verify(mockUserInterface, Mockito.times(1)).setHost(TEST_HOST);
		Mockito.verify(mockUserInterface, Mockito.times(1))
				.setPort(USABLE_PORT);
		Mockito.verify(mockUserInterface, Mockito.times(1)).createServer();
	}
}
