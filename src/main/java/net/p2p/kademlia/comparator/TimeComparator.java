package net.p2p.kademlia.comparator;

import java.util.Comparator;

import net.p2p.kademlia.dht.NodeContact;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class TimeComparator implements Comparator<NodeContact> {

    @Override
    public int compare(final NodeContact contact1, final NodeContact contact2) {
        long t1 = contact1.getLastSeen();
        long t2 = contact2.getLastSeen();

        if (t1 < t2) {
            return 1;
        } else if (t1 >= t2) {
            return -1;
        } else {
            return 0;
        }
    }
}
