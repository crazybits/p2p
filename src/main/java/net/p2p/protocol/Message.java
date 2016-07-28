package net.p2p.protocol;

import java.io.Serializable;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class Message implements Serializable {


    private static final long serialVersionUID = 4923081103118853877L;

    public Header header;
    public Body body;

    public Message(final Header header, final Body body) {
        this.header = header;
        this.body = body;
    }

    /**
     * @return the header
     */
    public Header getHeader() {
        return this.header;
    }

    /**
     * @param header
     *            the header to set
     */
    public void setHeader(final Header header) {
        this.header = header;
    }

    /**
     * @return the body
     */
    public Body getBody() {
        return this.body;
    }

    /**
     * @param body
     *            the body to set
     */
    public void setBody(final Body body) {
        this.body = body;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Message [header=" + this.header + ", body=" + this.body + "]";
    }

}
