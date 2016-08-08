package kademlia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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

        NodeID id1 = new NodeID();

        NodeID id2 = new NodeID();

        NodeID id3 = new NodeID();

        System.out.println("ID1=" + id1.toBinaryString());
        System.out.println("ID2=" + id2.toBinaryString());
        System.out.println("ID3=" + id3.toBinaryString());


        assertNotEquals(id1, id2);
        assertNotEquals(id1, id3);
        assertNotEquals(id2, id3);


        System.out.println("distance of ID and ID2=" + id1.distance(id2));
        System.out.println("distance of ID and ID3=" + id1.distance(id3));
        System.out.println("distance of ID2 and ID3=" + id2.distance(id3));


        System.out.println("Node ID as Hex=" + id1.toHexString());

        System.out.println("Node ID as BigInteger=" + id1.toBigInteger());

        System.out.println("Long MAX VALUE   = " + Long.toHexString(Long.MAX_VALUE));
        System.out.println("Node ID as long = " + Long.toHexString(id1.toBigInteger().longValue()));
        System.out.println("Node ID as Hex  = " + id1.toBigInteger().toString(16));


    }

    @Test
    public void testORX() {


        int a = 8; // 1000
        int b = 12;// 1100

        int resulta = a ^ b; // 100=4

        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        System.out.println(Integer.toBinaryString(resulta));

        assertEquals(4, resulta);

    }
}
