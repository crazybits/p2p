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
public class DiscoveryHandler extends ChannelHandlerAdapter {

    static final Logger logger = LoggerFactory.getLogger("DiscoveryHandler");


    public DiscoveryHandler() {

        DiscoveryHandler.logger.info("DiscoveryHandler instance has been created");
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {

        DiscoveryHandler.logger.info("new discovery channel activatet");

        ctx.writeAndFlush(MessageFactory.createMessage(MessageTypes.PING));

    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) {

        Message message = (Message) msg;

        MessageTypes types = message.getTypes();

        switch (types) {
        case PING:
            DiscoveryHandler.logger.info("Ping message recevied");
            ctx.write(MessageFactory.createMessage(MessageTypes.FIND_NODE));
            break;
        case STORE:
            DiscoveryHandler.logger.info("Store message recevied");
            // ctx.writeAndFlush(MessageFactory.createMessage(MessageTypes.FIND_NODE));
            break;
        case FIND_NODE:
            DiscoveryHandler.logger.info("FindNode message recevied");
            ctx.writeAndFlush(MessageFactory.createMessage(MessageTypes.FIND_VALUE));
            break;
        case FIND_VALUE:
            DiscoveryHandler.logger.info("FindValue message recevied");
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
        DiscoveryHandler.logger.error("failed on DiscoveryClientHandler", e);
        ctx.close();
    }

}
