package net.p2p.client;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

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
public class P2PClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger("P2PClientHandler");

    @Autowired
    private PeerDiscoveryManager peerDiscoveryManager;

    public P2PClientHandler() {
        P2PClientHandler.logger.info("create P2PClientHandler instance");
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {

        P2PClientHandler.logger.debug("new channel activated");

        ctx.writeAndFlush(MessageFactory.createHelloMessage());

    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) {


        Message message = (Message) msg;

        MessageTypes type = message.getHeader().getMessageType();

        switch (type) {
        case HELLO:
            // sentPeersMessage(ctx);
            P2PClientHandler.logger.info("Receive hello message:{}", msg);
            break;
        case GET_PEERS:
            // sentPeersMessage(ctx);
            P2PClientHandler.logger.info("Receive get peers message:{}", msg);
            break;
        case PEERS:
            processPeersMessage(msg);
            // sentPeersMessage(ctx);
            P2PClientHandler.logger.info("Receive peers message:{}", msg);
            break;
        case PING:
            P2PClientHandler.logger.info("Receive Ping Message:{}", msg);
            // response pong message
            ctx.writeAndFlush(MessageFactory.createPongMessage());
            break;
        case PONG:
            P2PClientHandler.logger.info("Receive Pong Message:{}", msg);
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
     */
    private void sentPeersMessage(final ChannelHandlerContext ctx) {

        Set<Peer> peers = this.peerDiscoveryManager.getPeers();

        Message message = MessageFactory.createPeersMessage(peers);
        ctx.writeAndFlush(message);

    }

    /**
     * <p>
     * 
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

    @Override
    public void channelReadComplete(final ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable e) {
        P2PClientHandler.logger.error("failed on P2G handler", e);
        ctx.close();
    }

    @Override
    public void userEventTriggered(final ChannelHandlerContext ctx, final Object evt) throws Exception {

        if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.ALL_IDLE) {
                P2PClientHandler.logger.info("channel is idle,sent a ping message");
                ctx.writeAndFlush(MessageFactory.createPingMessage());
            }
        }
    }
}
