package net.p2p.protocol;

import java.util.Set;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class MessageFactory {

    public static Message createPingMessage() {

        Header header = new Header(1, MessageTypes.PING);
        Body body = new Body();
        Message message = new Message(header, body);
        return message;

    }

    public static Message createPongMessage() {

        Header header = new Header(1, MessageTypes.PONG);
        Body body = new Body();
        Message message = new Message(header, body);
        return message;

    }

    public static Message createPeersMessage(final Set<Peer> peers) {

        Header header = new Header(1, MessageTypes.PEERS);
        Body body = new Body(peers);
        Message message = new Message(header, body);
        return message;

    }

    public static Message createHelloMessage() {

        Header header = new Header(1, MessageTypes.HELLO);
        Body body = new Body();
        Message message = new Message(header, body);
        return message;

    }
}
