package com.helo478.halcyon;

/**
 * The listener interface for receiving network events.
 * The class that is interested in processing a network
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addNetworkListener<code> method. When
 * the network event occurs, that object's appropriate
 * method is invoked.
 *
 * @see NetworkEvent
 * 
 * @author Donald Subert
 */
public interface NetworkListener {
	
	void outUI(final String line);
}
