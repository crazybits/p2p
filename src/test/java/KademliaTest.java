import net.p2p.kademlia.NodeID;

import org.junit.Test;


/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class KademliaTest {

    @Test
    public void testKademliaIDToHexString() {

        NodeID id = new NodeID();

        System.out.println(id.toHexString());
    }

}
