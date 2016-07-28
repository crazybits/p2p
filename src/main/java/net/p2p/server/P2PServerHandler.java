package net.p2p.server;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Set;

import net.p2p.peerdiscovery.PeerDiscoveryManager;
import net.p2p.protocol.Message;
import net.p2p.protocol.MessageFactory;
import net.p2p.protocol.MessageTypes;
import net.p2p.protocol.Peer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class P2PServerHandler extends ChannelHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger("P2PServerHandler");

    @Autowired
    private PeerDiscoveryManager peerDiscoveryManager;

    public P2PServerHandler() {
        P2PServerHandler.logger.info("create P2PServerHandler instance");
    }


    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) {

        P2PServerHandler.logger.debug("chanel read activate");

        Message message = (Message) msg;

        MessageTypes type = message.getHeader().getMessageType();

        switch (type) {
        case HELLO:
            // sentPeersMessage(ctx);
            P2PServerHandler.logger.info("Receive hello message:{}", msg);
            break;
        case GET_PEERS:
            // sentPeersMessage(ctx);
            P2PServerHandler.logger.info("Receive get peers message:{}", msg);
            break;
        case PEERS:
            // processPeersMessage(msg);
            P2PServerHandler.logger.info("Receive peers message:{}", msg);
            break;
        case PING:
            P2PServerHandler.logger.info("Receive Ping Message:{}", msg);
            ctx.writeAndFlush(MessageFactory.createPongMessage());
            break;
        case PONG:
            P2PServerHandler.logger.info("Receive Pong Message:{}", msg);
            break;

        default:
            break;
        }

    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @param msg
     */
    private void processPeersMessage(final Object msg) {
        Message message = (Message) msg;
        Set<Peer> peers = (Set<Peer>) message.getBody().body;
        this.peerDiscoveryManager.addPeers(peers);
    }

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    private void sentPeersMessage(final ChannelHandlerContext ctx) {

        Set<Peer> peers = this.peerDiscoveryManager.getPeers();

        Message message = MessageFactory.createPeersMessage(peers);
        ctx.writeAndFlush(message);

    }

    @Override
    public void channelReadComplete(final ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable e) {
        P2PServerHandler.logger.error("failed on P2 handler", e);
        ctx.close();
    }


}
