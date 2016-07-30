package net.p2p.kademlia.dht;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;

/**
 * <p>
 * <b> Impmentation of kademlia,the procotal details, refer to
 * procotal,http://xlattice.sourceforge.net/components
 * /protocol/kademlia/specs.html </b>
 * </p>
 */
public class NodeID {

    public static int ID_BIT_LENGTH = 120;// 120 bit=20 bytes

    private byte[] IDBytes;


    /**
     * <p>
     * <b> To genereate a random 120 bit node id</b>
     * </p>
     * 
     * 
     */
    public NodeID() {

        this.IDBytes = new byte[NodeID.ID_BIT_LENGTH / 8];

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

        if (IDBytes.length != NodeID.ID_BIT_LENGTH / 8) {
            throw new IllegalArgumentException("Invalid byte length");
        }

        this.IDBytes = IDBytes;

    }

    public byte[] getBytes() {

        return this.IDBytes;

    }


    /**
     * <p>
     * <b> cacluate the distance of the target node id,the return result date
     * type is also NodeID</b>
     * </p>
     * 
     * @param NodeID
     */
    public NodeID xor(final NodeID id) {

        byte[] result = new byte[NodeID.ID_BIT_LENGTH / 8];
        byte[] idByte = id.getBytes();


        for (int i = 0; i < NodeID.ID_BIT_LENGTH / 8; i++) {

            result[i] = (byte) (this.IDBytes[i] ^ idByte[i]);
        }

        NodeID resId = new NodeID(result);

        return resId;

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

        NodeID xorResult = xor(target);

        // find the first "1" bit of the xor result

        BitSet bitSet = BitSet.valueOf(xorResult.getBytes());

        int position = 0;

        for (int i = 0; i < NodeID.ID_BIT_LENGTH; i++) {

            if (bitSet.get(i)) {
                position = i;
                break;
            }

        }

        return NodeID.ID_BIT_LENGTH - position;

    }

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

        BigInteger bigInteger = new BigInteger(1, this.IDBytes);

        return bigInteger.toString(16);


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
        return this.toHexString();
    }
}
