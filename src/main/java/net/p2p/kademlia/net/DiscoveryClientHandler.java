package net.p2p.kademlia.net;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import net.p2p.kademlia.message.Message;
import net.p2p.kademlia.message.MessageFactory;
import net.p2p.kademlia.message.MessageTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class DiscoveryClientHandler extends ChannelHandlerAdapter {

    static final Logger logger = LoggerFactory.getLogger("DiscoveryClientHandler");


    public DiscoveryClientHandler() {

        DiscoveryClientHandler.logger.info("DiscoveryClientHandler instance has been created");
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {

        DiscoveryClientHandler.logger.info("new discovery channel activated on DiscoveryClientHandler");

        ctx.writeAndFlush(MessageFactory.createMessage(MessageTypes.PING));

    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) {

        Message message = (Message) msg;

        MessageTypes types = message.getTypes();

        switch (types) {
        case PING:
            DiscoveryServerHandler.logger.info("Ping message recevied");
            ctx.write(MessageFactory.createMessage(MessageTypes.FIND_NODE));
            break;
        case STORE:
            DiscoveryServerHandler.logger.info("Store message recevied");
            // ctx.writeAndFlush(MessageFactory.createMessage(MessageTypes.FIND_NODE));
            break;
        case FIND_NODE:
            DiscoveryServerHandler.logger.info("FindNode message recevied");
            // ctx.writeAndFlush(MessageFactory.createMessage(MessageTypes.FIND_NODE));
            break;
        case FIND_VALUE:
            DiscoveryServerHandler.logger.info("FindValue message recevied");
            // ctx.writeAndFlush(MessageFactory.createMessage(MessageTypes.FIND_NODE));
            break;

        default:
            break;
        }

    }

    @Override
    public void channelReadComplete(final ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable e) {
        DiscoveryClientHandler.logger.error("failed on DiscoveryClientHandler", e);
        ctx.close();
    }

}
