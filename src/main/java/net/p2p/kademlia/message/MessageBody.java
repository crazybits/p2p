package net.p2p.kademlia.message;

import java.io.Serializable;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class MessageBody implements Serializable {

    /**
     * <p>
     * <b> TODO : Insert description of the field. </b>
     * </p>
     */
    private static final long serialVersionUID = -2412788850851017521L;

    Object bodyObject;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "MessageBody [bodyObject=" + this.bodyObject + "]";
    }

}
