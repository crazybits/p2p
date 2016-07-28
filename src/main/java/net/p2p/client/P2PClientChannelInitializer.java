/*
 * COPYRIGHT. HSBC HOLDINGS PLC 2016. ALL RIGHTS RESERVED.
 * 
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of HSBC Holdings plc.
 */
package net.p2p.client;

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
public class P2PClientChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    private static final Logger logger = LoggerFactory.getLogger("P2PChannelInitializer");

    @Override
    protected void initChannel(final NioSocketChannel ch) throws Exception {

        P2PClientChannelInitializer.logger.debug("init handler");

        ch.pipeline().addLast("netty logging", new LoggingHandler());
        ch.pipeline().addLast("time out handler", new ReadTimeoutHandler(61, TimeUnit.SECONDS));
        ch.pipeline().addLast("ping pong", new IdleStateHandler(60, 60, 60));
        ch.pipeline().addLast("marshalling Decoder", MarshallingFactory.buildMarshallingDecoder());
        ch.pipeline().addLast("marshalling Encoder", MarshallingFactory.buildMarshallingEncoder());
        ch.pipeline().addLast("Client handler", new P2PClientHandler());

    }
}
