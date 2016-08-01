import static org.junit.Assert.assertNotEquals;

import java.util.BitSet;

import net.p2p.kademlia.dht.NodeID;

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

        NodeID id2 = new NodeID();

        NodeID id3 = new NodeID();

        NodeID id4 = new NodeID();

        System.out.println("ID 1=" + id.toHexString());
        System.out.println("ID 1=" + id.toBinaryString());
        System.out.println("ID 2=" + id2.toHexString());
        System.out.println("ID 3=" + id3.toHexString());
        System.out.println("ID 4=" + id4.toHexString());


        assertNotEquals(id, id2);
        assertNotEquals(id2, id3);
        assertNotEquals(id3, id4);

        BitSet bitSet1 = BitSet.valueOf(id.getBytes());
        BitSet bitSet2 = BitSet.valueOf(id2.getBytes());
        BitSet bitSet3 = BitSet.valueOf(id3.getBytes());
        BitSet bitSet4 = BitSet.valueOf(id4.getBytes());

        System.out.println("id 1 bit=" + bitSet1);
        System.out.println("id 2 bit=" + bitSet2);
        System.out.println("id 3 bit=" + bitSet3);
        System.out.println("id 4 bit=" + bitSet4);

        System.out.println("distance of ID and ID2=" + id.distance(id2));
        System.out.println("distance of ID and ID3=" + id.distance(id3));
        System.out.println("distance of ID and ID4=" + id.distance(id4));


        System.out.println("Node ID as Hex=" + id.toHexString());

        System.out.println("Node ID as BigInteger=" + id.toBigInteger());

        System.out.println("Long MAX VALUE   = " + Long.toHexString(Long.MAX_VALUE));
        System.out.println("Node ID as long = " + Long.toHexString(id.toBigInteger().longValue()));
        System.out.println("Node ID as Hex  = " + id.toBigInteger().toString(16));


    }
}
