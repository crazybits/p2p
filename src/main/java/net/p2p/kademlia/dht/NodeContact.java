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


    public NodeContact(final Node n) {
        this.node = n;
        this.ownNodeID = n.getNodeId();
        this.distance = this.ownNodeID.distance(n.getNodeId());
        touch();
    }

    public NodeContact(final NodeID ownerId, final Node n) {
        this.node = n;
        this.ownNodeID = ownerId;
        this.distance = this.ownNodeID.distance(n.getNodeId());
        touch();
    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public int distance(final NodeID from, final NodeID to) {
        return from.distance(to);
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


    public void touch() {
        this.lastSeen = System.currentTimeMillis();
    }

    /**
     * @return the ownNodeID
     */
    public NodeID getOwnNodeID() {
        return this.ownNodeID;
    }

    /**
     * @return the node
     */
    public Node getNode() {
        return this.node;
    }

    /**
     * @return the lastSeen
     */
    public long getLastSeen() {

        return this.lastSeen;
    }

}
