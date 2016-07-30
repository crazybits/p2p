package net.p2p.kademlia.message;

public enum MessageTypes {

    PING(0x00), STORE(0x01), FIND_NODE(0x02), FIND_VALUE(0x03);

    private int type;

    private MessageTypes(final int type) {
        this.type = type;

    }
}