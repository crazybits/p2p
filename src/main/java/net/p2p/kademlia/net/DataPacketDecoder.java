package net.p2p.kademlia.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class DataPacketDecoder extends MessageToMessageDecoder<DatagramPacket> {

    static final Logger logger = LoggerFactory.getLogger("DataPacketDecoder");

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public DataPacketDecoder() {


        DataPacketDecoder.logger.info("DataPacketDecoder instance is created");
    }

    protected void decode(final ChannelHandlerContext ctx, final DatagramPacket packet, final List<Object> out) throws Exception {

        out.add(packet.content().retain());

    }
}
