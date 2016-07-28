package net.p2p.protocol;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public enum MessageTypes {


    /* P2P protocol */

    /**
     * [0x00, P2P_VERSION, CLIEND_ID, CAPS, LISTEN_PORT, CLIENT_ID] <br>
     * First packet sent over the connection, and sent once by both sides. No
     * other messages may be sent until a Hello is received.
     */
    HELLO(0x00),

    /**
     * [0x01, REASON] <br>
     * Inform the peer that a disconnection is imminent; if received, a peer
     * should disconnect immediately. When sending, well-behaved hosts give
     * their peers a fighting chance (read: wait 2 seconds) to disconnect to
     * before disconnecting themselves.
     */
    DISCONNECT(0x01),

    /**
     * [0x02] <br>
     * Requests an immediate reply of Pong from the peer.
     */
    PING(0x02),

    /**
     * [0x03] <br>
     * Reply to peer's Ping packet.
     */
    PONG(0x03),

    /**
     * [0x04] <br>
     * Request the peer to enumerate some known peers for us to connect to.
     * This should include the peer itself.
     */
    GET_PEERS(0x04),

    /**
     * [0x05, [IP1, Port1, Id1], [IP2, Port2, Id2], ... ] <br>
     * Specifies a number of known peers. IP is a 4-byte array 'ABCD' that
     * should be interpreted as the IP address A.B.C.D. Port is a 2-byte array
     * that should be interpreted as a 16-bit big-endian integer. Id is the
     * 512-bit hash that acts as the unique identifier of the node.
     */
    PEERS(0x05),


    /**
     *
     */
    USER(0x0F);

    private int type;

    private MessageTypes(final int type) {
        this.type = type;

    }

}
