package net.p2p.kademlia.message;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class MessageFactory {

    public static Message createMessage(final MessageTypes type) {
        Message msg = null;
        switch (type) {
        case PING:
            msg = new Message(MessageTypes.PING);
            break;
        case PONG:
            msg = new Message(MessageTypes.PONG);
            break;
        case FIND_NODE:
            msg = new Message(MessageTypes.FIND_NODE);
            break;
        case FIND_VALUE:
            msg = new Message(MessageTypes.FIND_VALUE);
            break;
        case STORE:
            msg = new Message(MessageTypes.STORE);
            break;
        default:
            break;
        }
        return msg;

    }

}
