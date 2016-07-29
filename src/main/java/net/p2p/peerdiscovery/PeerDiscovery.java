package net.p2p.peerdiscovery;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.p2p.client.P2PClientChannelInitializer;
import net.p2p.protocol.Peer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class PeerDiscovery implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger("PeerDiscovery");

    Peer targetPeer;

    public PeerDiscovery(final Peer peer) {

        this.targetPeer = peer;

    }

    public void run() {

        tryConnect(this.targetPeer);
    }


    public void tryConnect(final Peer peer) {

        PeerDiscovery.logger.info("Start to discory connectable peers");

        EventLoopGroup group = new NioEventLoopGroup();

        try {

            final Bootstrap b = new Bootstrap();
            b.group(group);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.TCP_NODELAY, true);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new P2PClientChannelInitializer());

            ChannelFuture ch = b.connect(peer.getAddress().getHostName(), peer.getAddress().getPort()).sync();

            ch.addListener(new ChannelFutureListener() {

                public void operationComplete(final ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        PeerDiscovery.logger.info("Connect to {}:{} successfully.", peer.getAddress().getHostName(), peer
                            .getAddress().getPort());

                    } else {
                        PeerDiscovery.logger.info("Failed to connect to {}:{}", peer.getAddress().getHostName(), peer.getAddress()
                            .getPort());
                    }
                }

            });

            ch.channel().closeFuture().sync();

        } catch (Exception e) {

            PeerDiscovery.logger.error("failed to connect to {}:{}", peer.getAddress().getHostName(), peer.getAddress().getPort());

        } finally {
            // The connection is closed automatically on shutdown.
            group.shutdownGracefully();
        }
    }
}
