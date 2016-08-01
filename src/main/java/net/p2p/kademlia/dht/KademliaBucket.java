package net.p2p.kademlia.dht;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.p2p.kademlia.comparator.TimeComparator;
import net.p2p.kademlia.config.KademliaConfig;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class KademliaBucket implements IKademliaBucket {


    private final int depth;

    private List<NodeContact> nodes = new ArrayList<NodeContact>();
    private final KademliaConfig config;


    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public KademliaBucket(final int depth) {
        this(depth, new KademliaConfig());
    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @param depth
     * @param config
     */
    public KademliaBucket(final int depth, final KademliaConfig config) {
        super();
        this.depth = depth;
        this.config = config;
    }


    public synchronized NodeContact addNode(final NodeContact e) {
        if (!this.nodes.contains(e)) {
            if (this.nodes.size() >= KademliaConfig.BUCKET_SIZE) {
                return getLastSeen();
            } else {
                this.nodes.add(e);
            }
        }

        return null;
    }

    private NodeContact getLastSeen() {

        List<NodeContact> sorted = this.nodes;
        Collections.sort(sorted, new TimeComparator());
        return sorted.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.p2p.kademlia.IBucket#removeNode(net.p2p.kademlia.Node)
     */
    public synchronized void removeNode(final NodeContact contact) {

        for (NodeContact entry : this.nodes) {
            if (contact.getNode().equals(entry.getNode())) {
                this.nodes.remove(contact);
                break;
            }
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see net.p2p.kademlia.IBucket#getDepth()
     */
    public int getDepth() {

        return this.depth;
    }


    /*
     * (non-Javadoc)
     * 
     * @see net.p2p.kademlia.IBucket#getDepth()
     */
    public List<NodeContact> getNodes() {

        return this.nodes;
    }

    public int getNodesCount() {

        return this.nodes.size();
    }
}
