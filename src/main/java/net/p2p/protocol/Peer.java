package net.p2p.protocol;

import java.io.Serializable;
import java.net.InetSocketAddress;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class Peer implements Serializable {

    public static final long serialVersionID = 4923081103118853833L;

    private InetSocketAddress address;


    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @param address
     * @param id
     */
    public Peer() {
        super();
    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @param address
     */
    public Peer(final InetSocketAddress address) {
        super();
        this.address = address;

    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @param address
     * @param id
     */
    public Peer(final String host, final int port) {
        super();
        this.address = InetSocketAddress.createUnresolved(host, port);

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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Peer [address=" + this.address + "]";
    }


}
