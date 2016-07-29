package net.p2p.kademlia;

import java.util.TreeSet;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class Bucket implements IBucket {


    private final int depth;

    private final TreeSet<Node> nodes = new TreeSet<Node>();

    private final TreeSet<Node> cache = new TreeSet<Node>();


    private final KademliaConfig config;


    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @param depth
     * @param config
     */
    public Bucket(final int depth, final KademliaConfig config) {
        super();
        this.depth = depth;
        this.config = config;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.p2p.kademlia.IBucket#insert(net.p2p.kademlia.Node)
     */
    public void insert(final Node node) {


    }

    /*
     * (non-Javadoc)
     * 
     * @see net.p2p.kademlia.IBucket#removeNode(net.p2p.kademlia.Node)
     */
    public void removeNode(final Node node) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see net.p2p.kademlia.IBucket#containNode(net.p2p.kademlia.Node)
     */
    public boolean containNode(final Node node) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.p2p.kademlia.IBucket#getDepth()
     */
    public int getDepth() {
        // TODO Auto-generated method stub
        return 0;
    }

}
