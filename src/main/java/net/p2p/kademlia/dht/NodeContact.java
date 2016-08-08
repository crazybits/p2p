package net.p2p.kademlia.dht;


/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class NodeContact {

    private NodeID selfNodeID;

    private Node remoteNode;

    private long lastSeen;

    private int distance;

    public NodeContact(final NodeID selfNodeID, final Node remoteContactNode) {
        this.remoteNode = remoteContactNode;
        this.selfNodeID = selfNodeID;
        this.distance = this.selfNodeID.distance(remoteContactNode.getNodeId());
        touch();
    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public int distance(final NodeID self, final NodeID remote) {
        return self.distance(remote);
    }


    public void touch() {
        this.lastSeen = System.currentTimeMillis();
    }

    /**
     * @return the ownNodeID
     */
    public NodeID getSelfNodeID() {
        return this.selfNodeID;
    }

    /**
     * @return the node
     */
    public Node getRemoteNode() {
        return this.remoteNode;
    }

    /**
     * @return the lastSeen
     */
    public long getLastSeen() {

        return this.lastSeen;
    }

    /**
     * @return the distance
     */
    public int getDistance() {
        return this.distance;
    }

    @Override
    public String toString() {
        return "NodeContact [selfNodeID=" + this.selfNodeID.toBinaryString() + ", remoteNode=" + this.remoteNode + ", lastSeen="
            + this.lastSeen + ", distance=" + this.distance + "]";
    }

    @Override
    public int hashCode() {
        return this.remoteNode.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        boolean ret = false;

        if (o instanceof NodeContact) {
            NodeContact e = (NodeContact) o;
            ret = this.getRemoteNode().equals(e.getRemoteNode());
        }

        return ret;
    }
}
