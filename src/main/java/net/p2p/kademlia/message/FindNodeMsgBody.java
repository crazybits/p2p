package net.p2p.kademlia.message;


import java.io.Serializable;

import net.p2p.kademlia.dht.NodeID;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class FindNodeMsgBody extends MessageBody implements Serializable {

    /**
     * <p>
     * <b> TODO : Insert description of the field. </b>
     * </p>
     */
    private static final long serialVersionUID = -3789480811310780787L;

    NodeID targetNodeID;


}
