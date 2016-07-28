package net.p2p.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.p2p.protocol.Peer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class P2PClient {

    static final Logger logger = LoggerFactory.getLogger("P2PClient");

    public void connect(final Peer peer) {

        P2PClient.logger.info("Start to discory connectable peers");

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
                        P2PClient.logger.info("Connect to {}:{} successfully.", peer.getAddress().getHostName(), peer.getAddress()
                            .getPort());
                    } else {
                        P2PClient.logger.info("Failed to connect to {}:{}", peer.getAddress().getHostName(), peer.getAddress()
                            .getPort());
                    }
                }

            });

            ch.channel().closeFuture().sync();

        } catch (Exception e) {

            P2PClient.logger.error("failed to connect to {}:{}", peer.getAddress().getHostName(), peer.getAddress().getPort());

        } finally {
            // The connection is closed automatically on shutdown.
            group.shutdownGracefully();
        }
    }
}
