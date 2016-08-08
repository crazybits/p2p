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

    public KademliaBucket(final int depth) {
        this.depth = depth;
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

    public synchronized void removeNode(final NodeContact contact) {

        for (NodeContact entry : this.nodes) {
            if (entry.equals(contact)) {
                this.nodes.remove(contact);
                break;
            }
        }
    }

    public int getDepth() {

        return this.depth;
    }

    public List<NodeContact> getNodes() {

        return this.nodes;
    }

    public int getNodesCount() {

        return this.nodes.size();
    }
}
