package net.p2p.kademlia.message;

import java.io.Serializable;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class Message implements Serializable {

    /**
     * <p>
     * <b> TODO : Insert description of the field. </b>
     * </p>
     */
    private static final long serialVersionUID = 5540605926627038194L;

    MessageTypes types;

    MessageBody body;

    /**
     * @return the types
     */
    public MessageTypes getTypes() {
        return this.types;
    }

    /**
     * @param types
     *            the types to set
     */
    public void setTypes(final MessageTypes types) {
        this.types = types;
    }


    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @param types
     */
    public Message(final MessageTypes types) {
        super();
        this.types = types;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Message [types=" + this.types + ", body=" + this.body + "]";
    }


}
