package net.p2p.kademlia.message;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class MessageFactory {

    public static Message createPingMessage() {


        Message msg = new Message(MessageTypes.PING);


        return msg;

    }
}
