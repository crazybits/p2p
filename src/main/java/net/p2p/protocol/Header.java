package net.p2p.protocol;

import java.io.Serializable;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class Header implements Serializable {

    private static final long serialVersionUID = 6145542742184893825L;

    public int version;

    public MessageTypes messageType;

    /**
     * @return the version
     */
    public int getVersion() {
        return this.version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(final int version) {
        this.version = version;
    }

    /**
     * @return the messageType
     */
    public MessageTypes getMessageType() {
        return this.messageType;
    }

    /**
     * @param messageType
     *            the messageType to set
     */
    public void setMessageType(final MessageTypes messageType) {
        this.messageType = messageType;
    }

    public Header(final int version, final MessageTypes messageType) {

        this.version = version;
        this.messageType = messageType;

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Header [version=" + this.version + ", messageType=" + this.messageType + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.messageType == null) ? 0 : this.messageType.hashCode());
        result = prime * result + this.version;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Header other = (Header) obj;
        if (this.messageType != other.messageType) {
            return false;
        }
        if (this.version != other.version) {
            return false;
        }
        return true;
    }


}
