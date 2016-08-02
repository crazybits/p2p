package net.p2p.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class P2PServer implements Runnable {

    static final Logger logger = LoggerFactory.getLogger("P2PServer");

    static Config config = ConfigFactory.defaultApplication();

    public void start() throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap b = new ServerBootstrap();

            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.option(ChannelOption.SO_BACKLOG, 1024);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.childHandler(new P2PServerChannelInitializer());

            ChannelFuture ch = b.bind(P2PServer.config.getInt("peer.discovery.TCP.Listener.port")).sync();

            ch.addListener(new ChannelFutureListener() {

                public void operationComplete(final ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        P2PServer.logger.info("Start up server successfully,listen on port:{}", ((InetSocketAddress) future
                            .channel().localAddress()).getPort());
                    } else {
                        P2PServer.logger.info("Failed to start up the server");
                    }

                }

            });


            ch.channel().closeFuture().sync();

        } catch (Exception e) {

            P2PServer.logger.error("start up server failed", e);

        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }


    public void run() {
        try {
            start();
        } catch (InterruptedException e) {
            P2PServer.logger.error("start failed", e);
        }
    }
}
