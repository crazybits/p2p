package net.p2p.kademlia.dht;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.p2p.kademlia.comparator.DistanceComparator;
import net.p2p.kademlia.config.KademliaConfig;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class DHT {


    private Node self;

    private KademliaBucket[] buckets;

    private List<NodeContact> allNodesOfDHT;


    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public DHT(final Node self) {

        this(self, true);

    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public DHT(final Node self, final boolean includeSelf) {

        this.self = self;
        this.allNodesOfDHT = new ArrayList<NodeContact>();

        this.buckets = new KademliaBucket[KademliaConfig.BUCKETS_COUNT];
        for (int i = 0; i < KademliaConfig.BUCKETS_COUNT; i++) {
            this.buckets[i] = new KademliaBucket(i);
        }

        if (includeSelf) {
            addNode(this.self);
        }

    }


    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public synchronized Node addNode(final Node n) {

        NodeContact contact = new NodeContact(this.self.getNodeId(), n);
        NodeContact lastSeen = this.buckets[getBucketId(contact)].addNode(contact);
        if (lastSeen != null) {
            return lastSeen.getRemoteNode();
        }
        if (!this.allNodesOfDHT.contains(contact)) {
            this.allNodesOfDHT.add(contact);
        }
        return null;
    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public synchronized void removeNode(final Node n) {

        NodeContact node = new NodeContact(this.self.getNodeId(), n);

        this.buckets[getBucketId(node)].removeNode(node);
        this.allNodesOfDHT.remove(node);
    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public synchronized boolean contains(final Node n) {

        NodeContact e = new NodeContact(this.self.getNodeId(), n);
        for (KademliaBucket b : this.buckets) {
            if (b.getNodes().contains(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public synchronized void touchNode(final Node n) {

        NodeContact e = new NodeContact(this.self.getNodeId(), n);
        for (KademliaBucket b : this.buckets) {
            if (b.getNodes().contains(e)) {
                b.getNodes().get(b.getNodes().indexOf(e)).touch();
                break;
            }
        }
    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public int getBucketsCount() {

        int i = 0;
        for (KademliaBucket b : this.buckets) {
            if (b.getNodesCount() > 0) {
                i++;
            }
        }
        return i;
    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public synchronized int getNodesCount() {
        return this.allNodesOfDHT.size();
    }


    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public synchronized List<NodeContact> getAllNodes() {

        List<NodeContact> nodes = new ArrayList<NodeContact>();

        for (KademliaBucket b : this.buckets) {

            for (NodeContact e : b.getNodes()) {
                if (!e.getRemoteNode().equals(this.self)) {
                    nodes.add(e);
                }
            }
        }
        return nodes;
    }


    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public synchronized List<Node> getClosestNodes(final NodeID targetId) {

        List<NodeContact> closestEntries = getAllNodes();
        List<Node> closestNodes = new ArrayList<Node>();
        Collections.sort(closestEntries, new DistanceComparator(targetId));
        if (closestEntries.size() > KademliaConfig.FIND_NODE_MAX_RETURN_COUNT) {
            closestEntries = closestEntries.subList(0, KademliaConfig.FIND_NODE_MAX_RETURN_COUNT);
        }

        for (NodeContact e : closestEntries) {
            closestNodes.add(e.getRemoteNode());
        }
        return closestNodes;
    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public int getBucketId(final NodeContact contact) {

        int id = this.self.getNodeId().distance(contact.getRemoteNode().getNodeId()) - 1;
        return id < 0 ? 0 : id;
    }

    public Node getSelfNode() {
        return this.self;
    }

    public KademliaBucket[] getBuckets() {
        return this.buckets;
    }
}
