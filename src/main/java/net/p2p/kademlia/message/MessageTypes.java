package net.p2p.kademlia.message;

public enum MessageTypes {

    PING(0x00), PONG(0x01), STORE(0x02), FIND_NODE(0x03), FOUND_VALUES(0x04);

    private int type;

    private MessageTypes(final int type) {
        this.type = type;

    }
}