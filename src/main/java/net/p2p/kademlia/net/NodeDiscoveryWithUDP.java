package net.p2p.kademlia.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.logging.LoggingHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import serialization.MarshallingFactory;


/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class NodeDiscoveryWithUDP {

    static final Logger logger = LoggerFactory.getLogger("NodeDiscoveryWithUDP");

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @param args
     */

    public static void startKademliaDiscovry() {


        // new thread to start server with UDP channel
        new Thread("server") {
            @Override
            public void run() {
                try {
                    startServer();
                } catch (Exception e) {
                    NodeDiscoveryWithUDP.logger.error("start UDP server failure", e);
                }

            }
        }.start();

        // new thread to start client with UDP channel
        new Thread("client") {
            @Override
            public void run() {
                try {
                    startClient();
                } catch (Exception e) {
                    NodeDiscoveryWithUDP.logger.error("start UDP client failure", e);
                }

            }
        }.start();


    }

    private static void startServer() {


        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(worker);
            b.channel(NioDatagramChannel.class);
            b.option(ChannelOption.SO_BROADCAST, true);

            b.handler(new ChannelInitializer<NioDatagramChannel>() {

                protected void initChannel(final NioDatagramChannel ch) throws Exception {
                    ch.pipeline().addLast("netty logggin handler", new LoggingHandler("Netty Server Logging"));
                    ch.pipeline().addLast("data packet decoder", new DataPacketDecoder());
                    ch.pipeline().addLast("data packet encoder", new DataPacketEncoder());
                    ch.pipeline().addLast("marshalling Decoder", MarshallingFactory.buildMarshallingDecoder());
                    ch.pipeline().addLast("marshalling Encoder", MarshallingFactory.buildMarshallingEncoder());
                    ch.pipeline().addLast("kademlia prococal discovery handler", new DiscoveryServerHandler());

                }


            });

            ChannelFuture ch = b.bind(8888).sync();
            ch.channel().closeFuture().sync();

        } catch (Exception e) {

            NodeDiscoveryWithUDP.logger.error("discovery bootstrat is shutdown with error", e);

        } finally {

            worker.shutdownGracefully();

        }


    }

    private static void startClient() {

        EventLoopGroup worker = new NioEventLoopGroup();

        try {

            Bootstrap b = new Bootstrap();
            b.group(worker);
            b.channel(NioDatagramChannel.class);
            b.option(ChannelOption.SO_BROADCAST, true);
            b.handler(new ChannelInitializer<NioDatagramChannel>() {

                protected void initChannel(final NioDatagramChannel ch) throws Exception {

                    ch.pipeline().addLast("netty logggin handler", new LoggingHandler("Netty Client Logging"));
                    ch.pipeline().addLast("data packet decoder", new DataPacketDecoder());
                    ch.pipeline().addLast("data packet encoder", new DataPacketEncoder());
                    ch.pipeline().addLast("marshalling Decoder", MarshallingFactory.buildMarshallingDecoder());
                    ch.pipeline().addLast("marshalling Encoder", MarshallingFactory.buildMarshallingEncoder());
                    ch.pipeline().addLast("kademlia prococal discovery handler", new DiscoveryClientHandler());

                }

            });

            ChannelFuture ch = b.bind(8889).sync();

            ch.channel().closeFuture().sync();

        } catch (Exception e) {

            NodeDiscoveryWithUDP.logger.error("discovery bootstrat is shutdown with error", e);

        } finally {

            worker.shutdownGracefully();

        }


    }
}
