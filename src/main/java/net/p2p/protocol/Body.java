package net.p2p.protocol;

import java.io.Serializable;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class Body implements Serializable {


    private static final long serialVersionUID = 7665444679369895793L;

    public Object body;

    public Body() {}

    public Body(final Object body) {
        this.body = body;

    }

    /**
     * @return the body
     */
    public Object getBody() {
        return this.body;
    }

    /**
     * @param body
     *            the body to set
     */
    public void setBody(final Object body) {
        this.body = body;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Body [body=" + this.body + "]";
    }


}
