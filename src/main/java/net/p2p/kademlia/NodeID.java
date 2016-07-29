package net.p2p.kademlia;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class NodeID {

    public static int ID_BIT_LENGTH = 120;

    private byte[] IDBytes;

    public NodeID() {

        this.IDBytes = new byte[NodeID.ID_BIT_LENGTH / 8];// 120 bit=8
                                                          // bytes
        new Random().nextBytes(this.IDBytes);
    }

    public NodeID(final byte[] IDBytes) {

        if (IDBytes.length != NodeID.ID_BIT_LENGTH / 8) {
            throw new IllegalArgumentException("Invalid byte length");
        }

        this.IDBytes = IDBytes;

    }

    public byte[] getBytes() {

        return this.IDBytes;

    }

    public NodeID xor(final NodeID id) {

        byte[] result = new byte[NodeID.ID_BIT_LENGTH / 8];
        byte[] idByte = id.getBytes();


        for (int i = 0; i < NodeID.ID_BIT_LENGTH / 8; i++) {

            result[i] = (byte) (this.IDBytes[i] ^ idByte[i]);
        }

        NodeID resId = new NodeID(result);

        return resId;

    }

    public BigInteger getInt() {

        return new BigInteger(1, this.getBytes());

    }

    public String toHexString() {

        BigInteger bigInteger = new BigInteger(1, this.IDBytes);

        return String.format("%0" + (this.IDBytes.length << 1) + "x", bigInteger);

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
