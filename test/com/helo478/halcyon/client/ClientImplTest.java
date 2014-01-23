package com.helo478.halcyon.client;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.helo478.halcyon.controller.Controller;

public class ClientImplTest {

	private static final int USABLE_PORT = 80;
	private static final String LOCAL_HOST = "127.0.0.1";

	private ClientImpl client;

	@Mock
	Controller mockController;

	@Before
	public void setUp() {
		client = new ClientImpl(mockController);
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() {
		client.stop();
		client = null;
	}

	@Test
	public void beginTest_localHost_usablePort_shouldNotError()
			throws UnknownHostException, IOException {
		
		ServerSocket testServerSock = new ServerSocket(USABLE_PORT);
		
		client.begin(LOCAL_HOST, USABLE_PORT);
	}

	@Test
	public void stopTest_shouldNotError() {
		client.stop();
	}

	@Test
	public void getIsRunningTest_shouldAlwaysReturnFalse() {

		final boolean expected = false;
		final boolean received = client.getIsRunning();

		assertEquals(
				"A client component is never running, so should always return false",
				expected, received);
	}
}
