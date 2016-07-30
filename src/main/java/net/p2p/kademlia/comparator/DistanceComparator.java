package net.p2p.kademlia.comparator;

import java.util.Comparator;

import net.p2p.kademlia.dht.NodeID;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class DistanceComparator implements Comparator<NodeID> {


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
    public int compare(final NodeID nodeID1, final NodeID nodeID2) {
        int d1 = nodeID1.distance(this.targeId);
        int d2 = nodeID2.distance(this.targeId);

        if (d1 > d2) {
            return 1;
        } else if (d1 < d2) {
            return -1;
        } else {
            return 0;
        }
    }

}
