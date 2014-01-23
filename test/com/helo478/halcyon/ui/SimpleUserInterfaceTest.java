package com.helo478.halcyon.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.helo478.halcyon.NetworkException;

public class SimpleUserInterfaceTest {

	private static final int USABLE_PORT = 44444;
	private static final String TEST_HOST = "google.com";
	private static final String TEST_MESSAGE = "test message";
	private static final String SIMPLE_USER_INTERFACE_TO_STRING = "A Simple User Interface";

	private UserInterface userInterface = new SimpleUserInterface();

	@Mock
	InputListener mockInputListener;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void toStringTest() {
		final String expected = SIMPLE_USER_INTERFACE_TO_STRING;
		final String received = userInterface.toString();
		
		assertEquals("Should return a description of the class", expected, received);
	}

	// TODO: figure out how to test go()

	@Test
	public void goTest_null_shouldCreateClientByDefault() throws InvalidInputException {
		
		final String[] parameter = null;
		
		userInterface.addInputListener(mockInputListener);
		userInterface.go(parameter);
		
		Mockito.verify(mockInputListener, Mockito.times(1)).createClient();
	};
	
	@Test
	public void goTest_createServer_shouldNotCreateClient() throws InvalidInputException {
		
		final String[] parameter = {"-s"};
		
		userInterface.addInputListener(mockInputListener);
		userInterface.go(parameter);
		
		Mockito.verify(mockInputListener, Mockito.never()).createClient();
	}
	
	@Test
	public void getInputListenersTest_shouldNeverBeNull() {

		final Collection<InputListener> received = userInterface
				.getInputListeners();

		assertNotNull("Should never return null", received);
	}

	@Test
	public void addInputListenerTest() {

		final int expected = 1;

		userInterface.addInputListener(mockInputListener);

		final int received = userInterface.getInputListeners().size();

		assertEquals(
				"After adding 1 listeners, the total listeners should be 1",
				expected, received);
	}

	@Test
	public void removeInputListenerTest_True() {

		final int expectedSize = 0;

		userInterface.addInputListener(mockInputListener);

		final boolean receivedBoolean = userInterface
				.removeInputListener(mockInputListener);
		final int receivedSize = userInterface.getInputListeners().size();

		assertTrue(receivedBoolean);
		assertEquals("After adding and removing 1, there should be exactly 0",
				expectedSize, receivedSize);
	}

	@Test
	public void removeInputListenerTest_False() {

		final boolean received = userInterface
				.removeInputListener(mockInputListener);
		assertFalse("Should return false when the listener is not present",
				received);
	}

	@Test
	public void setHost_initial_shouldDoNothing() throws InvalidInputException {

		userInterface.setHost(TEST_HOST);

		Mockito.verifyZeroInteractions(mockInputListener);
	}

	@Test
	public void setHost_addListener_shouldCallListener()
			throws InvalidInputException {

		userInterface.addInputListener(mockInputListener);

		userInterface.setHost(TEST_HOST);

		Mockito.verify(mockInputListener, Mockito.times(1)).setHost(TEST_HOST);
	}

	@Test
	public void setPortTest_initial_shouldDoNothing()
			throws InvalidInputException {

		userInterface.setPort(USABLE_PORT);

		Mockito.verifyZeroInteractions(mockInputListener);
	}

	@Test
	public void setPortTest_addListener_shouldCallListener()
			throws InvalidInputException {

		userInterface.addInputListener(mockInputListener);

		userInterface.setPort(USABLE_PORT);

		Mockito.verify(mockInputListener, Mockito.times(1))
				.setPort(USABLE_PORT);
	}

	@Test
	public void createClientTest() throws InvalidInputException {

		userInterface.addInputListener(mockInputListener);

		userInterface.createClient();

		Mockito.verify(mockInputListener, Mockito.times(1)).createClient();
	}

	@Test
	public void createServerTest() throws InvalidInputException {

		userInterface.addInputListener(mockInputListener);

		userInterface.createServer();

		Mockito.verify(mockInputListener, Mockito.times(1)).createServer();
	}

	@Test
	public void beginTest() throws UnknownHostException, InvalidInputException, NetworkException {

		userInterface.addInputListener(mockInputListener);

		userInterface.begin();

		Mockito.verify(mockInputListener, Mockito.times(1)).begin();
	}

	@Test
	public void stopTest() {

		userInterface.addInputListener(mockInputListener);

		userInterface.stop();

		Mockito.verify(mockInputListener, Mockito.times(1)).stop();
	}

	@Test
	public void sendMessageTest() {

		userInterface.addInputListener(mockInputListener);

		userInterface.sendMessage(TEST_MESSAGE);

		Mockito.verify(mockInputListener, Mockito.times(1)).outNetwork(
				TEST_MESSAGE);
	}

	// TODO find a way to test println
}
