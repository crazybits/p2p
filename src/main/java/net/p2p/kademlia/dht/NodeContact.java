package net.p2p.kademlia.dht;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class NodeContact {

    private NodeID ownNodeID;

    private Node node;

    private long lastSeen;

    private int distance;


    public void justSeeIt() {
        this.lastSeen = System.currentTimeMillis();
    }

    /**
     * @return the ownNodeID
     */
    public NodeID getOwnNodeID() {
        return this.ownNodeID;
    }

    /**
     * @param ownNodeID
     *            the ownNodeID to set
     */
    public void setOwnNodeID(final NodeID ownNodeID) {
        this.ownNodeID = ownNodeID;
    }

    /**
     * @return the node
     */
    public Node getNode() {
        return this.node;
    }

    /**
     * @param node
     *            the node to set
     */
    public void setNode(final Node node) {
        this.node = node;
    }

    /**
     * @return the lastSeen
     */
    public long getLastSeen() {

        return this.lastSeen;
    }

    /**
     * @param lastSeen
     *            the lastSeen to set
     */
    public void setLastSeen(final long lastSeen) {
        this.lastSeen = lastSeen;
    }

    /**
     * @return the distance
     */
    public int getDistance() {
        return this.distance;
    }

    /**
     * @param distance
     *            the distance to set
     */
    public void setDistance(final int distance) {
        this.distance = distance;
    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @param node
     */
    public NodeContact(final Node node) {
        super();
        this.node = node;
    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @param ownNodeID
     * @param node
     * @param lastSeen
     */
    public NodeContact(final NodeID ownNodeID, final Node node, final long lastSeen) {
        super();
        this.ownNodeID = ownNodeID;
        this.node = node;
        this.lastSeen = lastSeen;
        this.distance = ownNodeID.distance(node.getNodeId());
    }


}
