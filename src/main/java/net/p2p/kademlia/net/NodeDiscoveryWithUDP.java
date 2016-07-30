package net.p2p.kademlia.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

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

    public void startKademliaDiscovry() {

        new Thread("server") {
            @Override
            public void run() {
                try {
                    NodeDiscoveryWithUDP.this.startServer();
                } catch (Exception e) {
                    NodeDiscoveryWithUDP.logger.error("start UDP server failure", e);
                }

            }
        }.start();


        new Thread("client") {
            @Override
            public void run() {
                try {
                    NodeDiscoveryWithUDP.this.startClient();
                } catch (Exception e) {
                    NodeDiscoveryWithUDP.logger.error("start UDP server failure", e);
                }

            }
        }.start();


    }

    public void startServer() {


        EventLoopGroup worker = new NioEventLoopGroup();

        try {

            Bootstrap b = new Bootstrap();

            b.group(worker);
            b.channel(NioDatagramChannel.class);
            b.handler(new ChannelInitializer<NioDatagramChannel>() {

                protected void initChannel(final NioDatagramChannel ch) throws Exception {

                    ch.pipeline().addLast("marshalling Decoder", MarshallingFactory.buildMarshallingDecoder());
                    ch.pipeline().addLast("marshalling Encoder", MarshallingFactory.buildMarshallingEncoder());
                    ch.pipeline().addLast("kademlia prococal discovery handler", new DiscoveryHandler("server"));

                }

            });

            ChannelFuture ch = b.bind(9999).sync();
            ch.channel().closeFuture().sync();

        } catch (Exception e) {

            NodeDiscoveryWithUDP.logger.error("discovery bootstrat is shutdown with error", e);

        } finally {

            worker.shutdownGracefully();

        }


    }

    public void startClient() {

        EventLoopGroup boss = new NioEventLoopGroup();

        try {

            Bootstrap b = new Bootstrap();

            b.group(boss);
            b.channel(NioDatagramChannel.class);
            b.handler(new ChannelInitializer<NioDatagramChannel>() {

                protected void initChannel(final NioDatagramChannel ch) throws Exception {

                    ch.pipeline().addLast("marshalling Decoder", MarshallingFactory.buildMarshallingDecoder());
                    ch.pipeline().addLast("marshalling Encoder", MarshallingFactory.buildMarshallingEncoder());
                    ch.pipeline().addLast("kademlia prococal discovery handler", new DiscoveryHandler("client"));

                }

            });

            ChannelFuture ch = b.connect("127.0.0.1", 9999).sync();
            ch.channel().closeFuture().sync();

        } catch (Exception e) {

            NodeDiscoveryWithUDP.logger.error("discovery bootstrat is shutdown with error", e);

        } finally {

            boss.shutdownGracefully();

        }


    }

}
