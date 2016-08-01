package net.p2p.kademlia.dht;

import java.net.InetSocketAddress;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class Node {

    private NodeID nodeId;

    private InetSocketAddress address;

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @param nodeId
     * @param address
     */
    public Node(final NodeID nodeId, final InetSocketAddress address) {
        super();
        this.nodeId = nodeId;
        this.address = address;
    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @param address
     */
    public Node(final InetSocketAddress address) {
        super();
        this.nodeId = new NodeID();
        this.address = address;
    }


    /**
     * @return the nodeId
     */
    public NodeID getNodeId() {
        return this.nodeId;
    }

    /**
     * @param nodeId
     *            the nodeId to set
     */
    public void setNodeId(final NodeID nodeId) {
        this.nodeId = nodeId;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.address == null) ? 0 : this.address.hashCode());
        result = prime * result + ((this.nodeId == null) ? 0 : this.nodeId.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Node other = (Node) obj;
        if (this.address == null) {
            if (other.address != null) {
                return false;
            }
        } else if (!this.address.equals(other.address)) {
            return false;
        }
        if (this.nodeId == null) {
            if (other.nodeId != null) {
                return false;
            }
        } else if (!this.nodeId.equals(other.nodeId)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Node [nodeId=" + this.nodeId.toBinaryString() + ", address=" + this.address + "]";
    }

}
