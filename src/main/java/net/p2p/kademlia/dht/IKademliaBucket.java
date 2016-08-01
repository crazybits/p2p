package net.p2p.kademlia.dht;

import java.util.List;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public interface IKademliaBucket {

    public NodeContact addNode(NodeContact node);

    public void removeNode(NodeContact node);

    public int getDepth();

    public List<NodeContact> getNodes();

    public int getNodesCount();

}
