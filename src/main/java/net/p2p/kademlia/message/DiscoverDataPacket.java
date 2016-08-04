package net.p2p.kademlia.message;

import java.net.InetSocketAddress;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class DiscoverDataPacket {

    private Message message;

    private InetSocketAddress address;

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @param message
     * @param address
     */
    public DiscoverDataPacket(final Message message, final InetSocketAddress address) {
        super();
        this.message = message;
        this.address = address;
    }

    /**
     * @return the message
     */
    public Message getMessage() {
        return this.message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(final Message message) {
        this.message = message;
    }

    /**
     * @return the address
     */
    public InetSocketAddress getAddress() {
        return this.address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(final InetSocketAddress address) {
        this.address = address;
    }


}
