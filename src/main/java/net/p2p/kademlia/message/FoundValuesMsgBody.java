package net.p2p.kademlia.message;

import java.io.Serializable;
import java.util.ArrayList;

import net.p2p.kademlia.dht.Node;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class FoundValuesMsgBody extends MessageBody implements Serializable {

    /**
     * <p>
     * <b> TODO : Insert description of the field. </b>
     * </p>
     */
    private static final long serialVersionUID = 7641282807457096541L;

    ArrayList<Node> foundNodes = new ArrayList<Node>();

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public FoundValuesMsgBody(final ArrayList<Node> nodes) {
        this.foundNodes = nodes;
    }


}
