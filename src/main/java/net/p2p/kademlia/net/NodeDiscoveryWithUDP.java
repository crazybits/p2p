package net.p2p.kademlia.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.logging.LoggingHandler;
import net.p2p.kademlia.manager.NodeManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import serialization.MarshallingFactory;

import com.typesafe.config.Config;


/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
@Component
public class NodeDiscoveryWithUDP {

    static final Logger logger = LoggerFactory.getLogger("NodeDiscoveryWithUDP");

    @Autowired
    NodeManager nodeManager;

    @Autowired
    Config config;


    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @param args
     */

    public void startKademliaDiscovry() {

        // new thread to start UDP listener with UDP channel
        new Thread("client") {
            @Override
            public void run() {
                try {
                    startUDPListener();
                } catch (Exception e) {
                    NodeDiscoveryWithUDP.logger.error("start UDP client failure", e);
                }

            }
        }.start();


    }

    private void startUDPListener() {

        EventLoopGroup worker = new NioEventLoopGroup();

        try {

            Bootstrap b = new Bootstrap();
            b.group(worker);
            b.channel(NioDatagramChannel.class);
            b.option(ChannelOption.SO_BROADCAST, true);
            b.handler(new ChannelInitializer<NioDatagramChannel>() {

                protected void initChannel(final NioDatagramChannel ch) throws Exception {

                    ch.pipeline().addLast("netty logggin handler", new LoggingHandler());
                    ch.pipeline().addLast("data packet decoder", new DataPacketDecoder());
                    ch.pipeline().addLast("data packet encoder", new DataPacketEncoder());
                    ch.pipeline().addLast("marshalling Decoder", MarshallingFactory.buildMarshallingDecoder());
                    ch.pipeline().addLast("marshalling Encoder", MarshallingFactory.buildMarshallingEncoder());
                    ch.pipeline().addLast("kademlia protocol discovery handler",
                        new DiscoveryHandler(NodeDiscoveryWithUDP.this.nodeManager, ch));

                }

            });

            ChannelFuture ch = b.bind(this.config.getInt("peer.discovery.UDP.Listener.port")).sync();

            ch.channel().closeFuture().sync();

        } catch (Exception e) {

            NodeDiscoveryWithUDP.logger.error("discovery bootstrat is shutdown with error", e);

        } finally {

            worker.shutdownGracefully();

        }


    }
}
