package net.p2p.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import serialization.MarshallingFactory;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class P2PServerChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    private static final Logger logger = LoggerFactory.getLogger("P2PChannelInitializer");

    @Override
    protected void initChannel(final NioSocketChannel ch) throws Exception {

        P2PServerChannelInitializer.logger.debug("init handler");

        ch.pipeline().addLast("netty logging", new LoggingHandler());
        ch.pipeline().addLast("time out handler", new ReadTimeoutHandler(61, TimeUnit.SECONDS));
        ch.pipeline().addLast("ping pong", new IdleStateHandler(60, 60, 60));
        ch.pipeline().addLast("marshalling Decoder", MarshallingFactory.buildMarshallingDecoder());
        ch.pipeline().addLast("marshalling Encoder", MarshallingFactory.buildMarshallingEncoder());
        ch.pipeline().addLast(new P2PServerHandler());

    }
}
