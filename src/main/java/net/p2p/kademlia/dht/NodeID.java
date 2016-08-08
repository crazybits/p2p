package net.p2p.kademlia.dht;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

import net.p2p.kademlia.config.KademliaConfig;

/**
 * <p>
 * <b> Impmentation of kademlia,the procotal details, refer to
 * procotal,http://xlattice.sourceforge.net/components
 * /protocol/kademlia/specs.html </b>
 * </p>
 */
public class NodeID implements Serializable {

    /**
     * <p>
     * <b> TODO : Insert description of the field. </b>
     * </p>
     */
    private static final long serialVersionUID = 8967217876343846137L;
    private byte[] IDBytes;

    /**
     * <p>
     * <b> To genereate a random node id</b>
     * </p>
     * 
     * 
     */
    public NodeID() {

        this.IDBytes = new byte[KademliaConfig.ID_BIT_LENGTH / 8];

        new Random().nextBytes(this.IDBytes);
    }

    /**
     * <p>
     * <b> Genereate the node id with given 8 bytes</b>
     * </p>
     * 
     * @param byte [] IDBytes
     */

    public NodeID(final byte[] IDBytes) {

        if (IDBytes.length != KademliaConfig.ID_BIT_LENGTH / 8) {
            throw new IllegalArgumentException("Invalid byte length");
        }

        this.IDBytes = IDBytes;

    }


    public byte[] getBytes() {

        return this.IDBytes;

    }


    /**
     * <p>
     * <b> cacluate the distance of the target node id</b>
     * </p>
     * 
     * @param NodeID
     */
    public byte[] xor(final NodeID to) {

        byte[] xorResult = new byte[KademliaConfig.ID_BIT_LENGTH / 8];
        byte[] toNodeIDBytes = to.getBytes();

        for (int i = 0; i < KademliaConfig.ID_BIT_LENGTH / 8; i++) {

            xorResult[i] = (byte) (toNodeIDBytes[i] ^ this.IDBytes[i]);

        }

        return xorResult;

    }

    /**
     * <p>
     * <b> cacluate the distance of the target node id,find the first "1" bit
     * of the xor result</b>
     * </p>
     * 
     * @param NodeID
     */
    public int distance(final NodeID target) {

        byte[] xorResult = xor(target);

        int d = KademliaConfig.ID_BIT_LENGTH;

        // find the first "1" bit of the xor result

        for (byte b : xorResult) {
            if (b == 0) {
                d -= 8;
            } else {
                int count = 0;
                for (int i = 7; i >= 0; i--) {
                    boolean a = (b & (1 << i)) == 0;
                    if (a) {
                        count++;
                    } else {
                        break;
                    }
                }

                d -= count;

                break;
            }
        }
        return d;


    }


    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @return
     */
    public BigInteger toBigInteger() {

        return new BigInteger(1, this.getBytes());

    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @return
     */
    public String toHexString() {

        return new BigInteger(1, this.IDBytes).toString(16);

    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @return
     */
    public String toBinaryString() {
        return new BigInteger(1, this.IDBytes).toString(2);

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(this.IDBytes);
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
        NodeID other = (NodeID) obj;
        if (!Arrays.equals(this.IDBytes, other.IDBytes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.toBinaryString();
    }
}
