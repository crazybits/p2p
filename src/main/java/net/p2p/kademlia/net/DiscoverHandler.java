package net.p2p.kademlia.net;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

import net.p2p.kademlia.manager.NodeManager;
import net.p2p.kademlia.message.DiscoverDataPacket;
import net.p2p.kademlia.message.MessageFactory;
import net.p2p.kademlia.message.MessageTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class DiscoverHandler extends ChannelHandlerAdapter {

    static final Logger logger = LoggerFactory.getLogger("DiscoveryHandler");

    NodeManager nodeManager;
    NioDatagramChannel channel;


    public DiscoverHandler(final NodeManager nodeManager, final NioDatagramChannel channel) {
        this.nodeManager = nodeManager;
        this.channel = channel;
        DiscoverHandler.logger.info("DiscoveryHandler instance has been created");
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {

        DiscoverHandler.logger.info("new discovery channel activated");
        ctx.writeAndFlush(new DiscoverDataPacket(MessageFactory.createMessage(MessageTypes.PING), new InetSocketAddress(
            "127.0.0.1", 8889)));


    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) {
        this.nodeManager.handleInbound(ctx, msg);
    }

    @Override
    public void channelReadComplete(final ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable e) {
        DiscoverHandler.logger.error("failed on DiscoveryClientHandler", e);
        ctx.close();
    }

}
