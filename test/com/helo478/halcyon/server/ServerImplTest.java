package com.helo478.halcyon.server;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.helo478.halcyon.AlreadyRunningException;
import com.helo478.halcyon.HalcyonComponent;
import com.helo478.halcyon.controller.Controller;

public class ServerImplTest {

	private static final String LOCAL_HOST = "127.0.0.1";
	private static final int USABLE_PORT = 44444;

	private HalcyonComponent server;

	@Mock
	Controller mockController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		server = new ServerImpl(mockController);
	}

	@After
	public void tearDown() {
		server.stop();
		server = null;
	}

	@Test
	public void beginTest_usablePort_shouldNotError()
			throws UnknownHostException, AlreadyRunningException, IOException {
		server.begin(LOCAL_HOST, USABLE_PORT);
	}

	@Test(expected = AlreadyRunningException.class)
	public void beginTest_alreadyRunning_shouldError()
			throws UnknownHostException, AlreadyRunningException, IOException {
		server.begin(LOCAL_HOST, USABLE_PORT);
		server.begin(LOCAL_HOST, USABLE_PORT);
	}

	@Test
	public void stopTest_initial_shouldNotError() {
		server.stop();
	}

	@Test
	public void stopTest_beginThenStop_shouldNotError()
			throws UnknownHostException, AlreadyRunningException, IOException {
		server.begin(LOCAL_HOST, USABLE_PORT);
		server.stop();
	}

	@Test
	public void getIsRunningTest_initial_shouldReturnFalse() throws IOException {

		final boolean expected = false;
		final boolean received = server.getIsRunning();

		assertEquals("The server should not be running initially", expected,
				received);
	}

	@Test
	public void getIsRunningTest_begin_shouldReturnTrue()
			throws AlreadyRunningException, IOException {

		final boolean expected = true;

		server.begin(LOCAL_HOST, USABLE_PORT);

		final boolean received = server.getIsRunning();

		assertEquals("Once the server begins running, should return true",
				expected, received);
	}

	@Test
	public void getIsRunningTest_beginThenStop_shouldReturnFalse()
			throws UnknownHostException, AlreadyRunningException, IOException {

		final boolean expected = false;

		server.begin(LOCAL_HOST, USABLE_PORT);
		server.stop();

		final boolean received = server.getIsRunning();

		assertEquals("Once the server is stopped, should return false",
				expected, received);
	}
}
