package net.p2p.kademlia.manager;

import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;

import net.p2p.kademlia.dht.DHT;
import net.p2p.kademlia.message.DiscoverDataPacket;
import net.p2p.kademlia.message.MessageFactory;
import net.p2p.kademlia.message.MessageTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
@Component
public class NodeManager {


    static final Logger logger = LoggerFactory.getLogger("NodeManager");


    DHT dhtTable;

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public NodeManager() {
        NodeManager.logger.info("Node instance has been created");
    }


    public void test() {
        System.out.println("test");
    }

    public void sentMessage() {

    }

    public void handleInbound(final ChannelHandlerContext ctx, final Object msg) {


        DiscoverDataPacket message = (DiscoverDataPacket) msg;
        MessageTypes type = message.getMessage().getType();

        switch (type) {
        case PING:
            NodeManager.logger.info("Ping message recevied");
            ctx.writeAndFlush(new DiscoverDataPacket(MessageFactory.createMessage(MessageTypes.PONG), new InetSocketAddress(
                "127.0.0.1", 8889)));
            break;
        case PONG:
            NodeManager.logger.info("Pong message recevied");
            ctx.writeAndFlush(new DiscoverDataPacket(MessageFactory.createMessage(MessageTypes.FIND_NODE), new InetSocketAddress(
                "127.0.0.1", 8889)));
            break;
        case FIND_NODE:
            NodeManager.logger.info("FindNode message recevied");
            ctx.writeAndFlush(new DiscoverDataPacket(MessageFactory.createMessage(MessageTypes.FIND_VALUE), new InetSocketAddress(
                "127.0.0.1", 8889)));
            break;
        case FIND_VALUE:
            NodeManager.logger.info("FindValue message recevied");
            ctx.writeAndFlush(new DiscoverDataPacket(MessageFactory.createMessage(MessageTypes.STORE), new InetSocketAddress(
                "127.0.0.1", 8889)));
            break;
        case STORE:
            NodeManager.logger.info("Store message recevied");
            // ctx.writeAndFlush(MessageFactory.createMessage(MessageTypes.FIND_NODE));
            break;
        default:
            break;
        }
    }
}
