package kademlia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

import net.p2p.kademlia.config.KademliaConfig;
import net.p2p.kademlia.dht.DHT;
import net.p2p.kademlia.dht.KademliaBucket;
import net.p2p.kademlia.dht.Node;
import net.p2p.kademlia.dht.NodeContact;
import net.p2p.kademlia.dht.NodeID;

import org.junit.Test;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class KademliaBucketTest {


    @Test
    public void test2() {
        DHT t = getTestDHT(0);
        Node n = getNode();

        System.out.println("only self node");

        dumpNodes(t);

        t.addNode(n);

        System.out.println("after add node");

        dumpNodes(t);

        assertTrue(containsNode(t, n));

        t.removeNode(n);

        System.out.println("after remove node");

        dumpNodes(t);

        assertFalse(containsNode(t, n));
    }


    @Test
    public void test3() {
        DHT t = getTestDHT(1000000);
        showBuckets(t);

        List<Node> closest1 = t.getClosestNodes(t.getSelfNode().getNodeId());
        List<Node> closest2 = t.getClosestNodes(new NodeID());

        assertNotEquals(closest1, closest2);
    }


    @Test
    public void test4() {
        DHT t = getTestDHT(0);
        Node self = t.getSelfNode();

        // t.getBucketsCount() returns non empty buckets
        assertEquals(t.getBucketsCount(), 1);

        // creates very close nodes
        for (int i = 1; i < KademliaConfig.BUCKET_SIZE; i++) {
            Node n = getNode(self.getNodeId().getBytes(), i);

            t.addNode(n);
        }

        showBuckets(t);


        assertEquals(KademliaConfig.BUCKET_SIZE, t.getNodesCount());
    }


    public static Node getNode(final byte[] id, final int i) {


        byte[] newID = Arrays.copyOf(id, id.length);

        newID[KademliaConfig.ID_BIT_LENGTH / 8 - 1] += (byte) i;

        Node node = new Node(new InetSocketAddress("127.0.0.1", 30303));
        node.setNodeId(new NodeID(newID));

        return node;
    }

    public static Node getNode() {
        return new Node(new InetSocketAddress("127.0.0.1", 30303));
    }

    public static DHT getTestDHT(final long nodesQuantity) {
        DHT testTable = new DHT(getNode());

        for (int i = 0; i < nodesQuantity; i++) {
            testTable.addNode(getNode());
        }

        return testTable;
    }

    public static void showBuckets(final DHT t) {
        for (KademliaBucket b : t.getBuckets()) {
            if (b.getNodesCount() > 0) {
                System.out.println(String.format("Bucket %d nodes %d depth %d", b.getDepth(), b.getNodesCount(), b.getDepth()));
            }
        }
    }

    public static boolean containsNode(final DHT t, final Node n) {
        for (NodeContact e : t.getAllNodes()) {
            if (e.getRemoteNode().toString().equals(n.toString())) {
                return true;
            }
        }
        return false;
    }

    public static void dumpNodes(final DHT t) {

        List<NodeContact> nodes = t.getAllNodes();
        for (NodeContact nodeContact : nodes) {

            System.out.println(nodeContact.toString());
        }

    }
}
