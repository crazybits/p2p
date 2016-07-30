package net.p2p.kademlia;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public interface IKademliaBucket {

    public void insert(Node node);

    public void removeNode(Node node);

    public boolean containNode(Node node);

    public int getDepth();


}
