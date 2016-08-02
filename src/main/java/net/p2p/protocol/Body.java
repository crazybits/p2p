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


    @Override
    public String toString() {
        return "Body [body=" + this.body + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.body == null) ? 0 : this.body.hashCode());
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
        Body other = (Body) obj;
        if (this.body == null) {
            if (other.body != null) {
                return false;
            }
        } else if (!this.body.equals(other.body)) {
            return false;
        }
        return true;
    }


}
