package kademlia;

import java.net.InetSocketAddress;
import java.util.BitSet;
import java.util.List;

import net.p2p.kademlia.config.KademliaConfig;
import net.p2p.kademlia.dht.DHT;
import net.p2p.kademlia.dht.Node;
import net.p2p.kademlia.dht.NodeContact;
import net.p2p.kademlia.dht.NodeID;

import org.junit.Test;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class KademliaTest {

    @Test
    public void testDistance() {

        BitSet bit1 = new BitSet(KademliaConfig.ID_BIT_LENGTH);
        bit1.set(KademliaConfig.ID_BIT_LENGTH - 1);
        bit1.set(40);
        NodeID id = new NodeID(bit1);
        Node node = new Node(id, InetSocketAddress.createUnresolved("127.0.0.1", 8888));

        BitSet bit2 = new BitSet(KademliaConfig.ID_BIT_LENGTH);
        bit2.set(KademliaConfig.ID_BIT_LENGTH - 1);
        bit2.set(30);
        NodeID id2 = new NodeID(bit2);
        Node node2 = new Node(id2, InetSocketAddress.createUnresolved("127.0.0.1", 8888));

        BitSet selfBits = new BitSet(KademliaConfig.ID_BIT_LENGTH);
        selfBits.set(KademliaConfig.ID_BIT_LENGTH - 1);
        NodeID selfID = new NodeID(selfBits);
        Node self = new Node(selfID, InetSocketAddress.createUnresolved("127.0.0.1", 8888));

        DHT table = new DHT(self);

        table.addNode(node);
        table.addNode(node2);


        System.out.println(table.getBucketId(new NodeContact(node)));
        System.out.println(table.getBucketId(new NodeContact(node2)));

        // assertEquals(117, table.getBucketId(new NodeContact(node2)));

        List<Node> nodesList = table.getClosestNodes(new NodeID());
        for (Node node1 : nodesList) {

            System.out.println(node1.toString());

        }

    }
}
