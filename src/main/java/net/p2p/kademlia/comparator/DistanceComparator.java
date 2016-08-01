package net.p2p.kademlia.comparator;

import java.util.Comparator;

import net.p2p.kademlia.dht.NodeContact;
import net.p2p.kademlia.dht.NodeID;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class DistanceComparator implements Comparator<NodeContact> {


    private NodeID targeId;

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @param targeId
     */
    public DistanceComparator(final NodeID targeId) {
        super();
        this.targeId = targeId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(final NodeContact contact1, final NodeContact contact2) {

        int d1 = contact1.getNode().getNodeId().distance(this.targeId);
        int d2 = contact2.getNode().getNodeId().distance(this.targeId);

        if (d1 > d2) {
            return 1;
        } else if (d1 < d2) {
            return -1;
        } else {
            return 0;
        }
    }
}
