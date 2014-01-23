package com.helo478.halcyon.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.helo478.halcyon.AlreadyRunningException;
import com.helo478.halcyon.HalcyonComponent;
import com.helo478.halcyon.NetworkException;
import com.helo478.halcyon.NetworkListener;
import com.helo478.halcyon.client.ClientImpl;
import com.helo478.halcyon.server.ServerImpl;
import com.helo478.halcyon.ui.InputListener;
import com.helo478.halcyon.ui.InvalidInputException;

public class ControllerImplTest {

	private static final String INITIAL_HOST = "127.0.0.1";
	private static final String LOCAL_HOST = "127.0.0.1";
	private static final String TEST_HOST = "google.com";
	private static final int INITIAL_PORT = 1;
	private static final int USABLE_PORT = 44444;
	private static final int BAD_PORT_0 = 0;
	private static final int BAD_PORT_65536 = 65536;
	private static final String TEST_MESSAGE = "test message";

	private ControllerImpl controller;

	@Mock
	HalcyonComponent mockHalcyonComponent;

	@Mock
	NetworkListener mockNetworkListener;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new ControllerImpl();
	}

	@Test
	public void createClientTest_shouldNotError() throws InvalidInputException {
		controller.createClient();
	}

	@Test
	public void createClientTest_shouldNotBeNull() throws InvalidInputException {
		controller.createClient();
		final HalcyonComponent received = controller.getHalcyonComponent();
		assertNotNull("After creating, should not be null", received);
	}

	@Test
	public void createClientTest_shouldBeClient() throws InvalidInputException {
		controller.createClient();
		final HalcyonComponent received = controller.getHalcyonComponent();
		assertTrue("After creating a client, should be a client",
				received instanceof ClientImpl);
	}

	@Test
	public void createServerTest_shouldNotError() throws InvalidInputException {
		controller.createServer();
	}

	@Test
	public void createServerTest_shouldNotBeNull() throws InvalidInputException {
		controller.createServer();
		final HalcyonComponent received = controller.getHalcyonComponent();
		assertNotNull("After creating, should not be null", received);
	}

	@Test
	public void createServerTest_shouldBeClient() throws InvalidInputException {
		controller.createServer();
		final HalcyonComponent received = controller.getHalcyonComponent();
		assertTrue("After creating a client, should be a client",
				received instanceof ServerImpl);
	}

	@Test
	public void getHost_initial_shouldReturnLocalHost() {

		final String expected = LOCAL_HOST;
		final String received = controller.getHost();

		assertEquals("Initial value should be local host", expected, received);
	}

	@Test
	public void setHost_testHost_shouldReturnTestHost()
			throws InvalidInputException {

		final String parameter = TEST_HOST;
		final String expected = TEST_HOST;

		controller.setHost(parameter);

		final String received = controller.getHost();

		assertEquals("Test host should return test host", expected, received);
	}

	@Test(expected = InvalidInputException.class)
	public void setHost_null_shouldError() throws InvalidInputException {

		final String parameter = null;

		controller.setHost(parameter);
	}

	@Test
	public void getPort_intitial_shouldReturn1() {

		final int expected = 1;
		final int received = controller.getPort();

		assertEquals("Initial value should be 1", expected, received);
	}

	@Test
	public void setPort_usablePort_shouldReturnUsablePort()
			throws InvalidInputException {

		final int parameter = USABLE_PORT;
		final int expected = USABLE_PORT;

		controller.setPort(parameter);

		final int received = controller.getPort();

		assertEquals("Usable port should return usable port", expected,
				received);
	}

	@Test(expected = InvalidInputException.class)
	public void setPort_badPort0_shouldError() throws InvalidInputException {

		final int parameter = BAD_PORT_0;

		controller.setPort(parameter);
	}

	@Test(expected = InvalidInputException.class)
	public void setPort_badPort65536_shouldError() throws InvalidInputException {

		final int parameter = BAD_PORT_65536;

		controller.setPort(parameter);
	}

	@Test
	public void beginTest_null_shouldNotError() throws InvalidInputException,
			NetworkException {
		controller.begin();
	}

	@Test
	public void beginTest_null_shouldOutputErrorMessage()
			throws InvalidInputException, NetworkException {

		final String expected = "Unable to begin; No Halcyon component loaded";

		controller.addNetworkListener(mockNetworkListener);
		controller.begin();

		Mockito.verify(mockNetworkListener, Mockito.times(1)).outUI(expected);
	}

	@Test
	public void beginTest_mockHalcyonComponent_shouldCallMock()
			throws InvalidInputException, NetworkException,
			UnknownHostException, AlreadyRunningException, IOException {

		controller.setHalcyonComponent(mockHalcyonComponent);
		controller.begin();

		Mockito.verify(mockHalcyonComponent, Mockito.times(1)).begin(
				INITIAL_HOST, INITIAL_PORT);
	}

	@Test
	public void stopTest_null_shouldNotError() {

		controller.setHalcyonComponent(null);
		controller.stop();
	}

	@Test
	public void stopTest_mockHalcyonComponent_shouldCallMock() {

		controller.setHalcyonComponent(mockHalcyonComponent);
		controller.stop();

		Mockito.verify(mockHalcyonComponent, Mockito.times(1)).stop();
	}

	@Test
	public void getIsRunningTest_null_shouldReturnFalse() {

		final boolean received = controller.getIsRunning();

		assertFalse(
				"If there is no Halcyon component loaded, should return false",
				received);
	}

	@Test
	public void getIsRunningTest_mockHalcyonComponent_trueShouldReturnTrue() {

		controller.setHalcyonComponent(mockHalcyonComponent);

		Mockito.when(mockHalcyonComponent.getIsRunning()).thenReturn(true);

		final boolean received = controller.getIsRunning();

		assertTrue("When halcyon component is running, should return true",
				received);
	}

	@Test
	public void getIsRunningTest_mockHalcyonComponent_falseShouldReturnfalse() {

		controller.setHalcyonComponent(mockHalcyonComponent);

		Mockito.when(mockHalcyonComponent.getIsRunning()).thenReturn(false);

		final boolean received = controller.getIsRunning();

		assertFalse(
				"When halcyon component is not running, should return false",
				received);
	}

	@Test
	public void outNetworkTest_shouldPipeMessage() {

		final String parameter = TEST_MESSAGE;
		final String expected = TEST_MESSAGE;

		controller.setHalcyonComponent(mockHalcyonComponent);

		controller.outNetwork(parameter);

		Mockito.verify(mockHalcyonComponent, Mockito.times(1)).outNetwork(
				expected);

	}

	@Test
	public void getNetworkListenersTest_initial_shouldNotBeNull() {
		final Collection<NetworkListener> received = controller
				.getNetworkListeners();

		assertNotNull("Should never return null", received);
	}

	@Test
	public void addNetworkListenerTest() {

		final int expected = 1;

		controller.addNetworkListener(mockNetworkListener);

		final int received = controller.getNetworkListeners().size();

		assertEquals("After adding 1, there should be exactly 1", expected,
				received);
	}

	@Test
	public void removeNetworkListenerTest_addingAndThenRemoving1() {

		final int expectedSize = 0;

		controller.addNetworkListener(mockNetworkListener);

		final boolean receivedBoolean = controller
				.removeNetworkListener(mockNetworkListener);

		assertTrue("Should return true when successfully removed",
				receivedBoolean);

		final int receivedSize = controller.getNetworkListeners().size();

		assertEquals(
				"After removing adding 1 and removing 1, there should be exactly 0",
				expectedSize, receivedSize);
	}

	@Test
	public void removeNetworkListenerTest_removing1_shouldReturnFalse() {

		final boolean received = controller
				.removeNetworkListener(mockNetworkListener);

		assertFalse("Should return false when not present to be removed",
				received);
	}

	@Test
	public void outUITest_shouldPipeMessage() {

		final String parameter = TEST_MESSAGE;
		final String expected = TEST_MESSAGE;

		controller.addNetworkListener(mockNetworkListener);

		controller.outUI(parameter);

		Mockito.verify(mockNetworkListener, Mockito.times(1)).outUI(expected);
	}

	@Test
	public void inputListener_createClientTest_shouldNotError()
			throws InvalidInputException {
		controller.createClient();
	}

	@Test
	public void inputListener_createClientTest_shouldNotBeNull()
			throws InvalidInputException {
		
		controller.createClient();
		final HalcyonComponent received = controller.getHalcyonComponent();
		assertNotNull("After creating, should not be null", received);
	}

	@Test
	public void inputListener_createClientTest_shouldBeClient()
			throws InvalidInputException {
		
		controller.createClient();
		final HalcyonComponent received = controller.getHalcyonComponent();
		assertTrue("After creating a client, should be a client",
				received instanceof ClientImpl);
	}

	@Test
	public void inputListener_createServerTest_shouldNotError()
			throws InvalidInputException {
		controller.createServer();
	}

	@Test
	public void inputListener_createServerTest_shouldNotBeNull()
			throws InvalidInputException {
		
		controller.createServer();
		final HalcyonComponent received = controller.getHalcyonComponent();
		assertNotNull("After creating, should not be null", received);
	}

	@Test
	public void inputListener_createServerTest_shouldBeClient()
			throws InvalidInputException {
		
		controller.createServer();
		final HalcyonComponent received = controller.getHalcyonComponent();
		assertTrue("After creating a client, should be a client",
				received instanceof ServerImpl);
	}

	@Test
	public void inputListener_setHost_testHost_shouldReturnTestHost()
			throws InvalidInputException {

		final String parameter = TEST_HOST;
		final String expected = TEST_HOST;

		controller.setHost(parameter);

		final String received = controller.getHost();

		assertEquals(
				"After setting host to test host, should return test host",
				expected, received);
	}

	@Test
	public void inputListener_setPort_usablePort_shouldReturnUsablePort()
			throws InvalidInputException {

		final int parameter = USABLE_PORT;
		final int expected = USABLE_PORT;

		controller.setPort(parameter);

		final int received = controller.getPort();

		assertEquals(
				"After setting port to usable port, should return usable port",
				expected, received);
	}

	// TODO add tests for InputListener begin(), stop(), getIsRunning(), and
	// outNetwork()
}