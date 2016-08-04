package net.p2p.kademlia.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

import net.p2p.kademlia.message.DiscoverDataPacket;
import net.p2p.kademlia.message.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.EncoderUtil;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class DataPacketEncoder extends MessageToMessageEncoder<DiscoverDataPacket> {

    static final Logger logger = LoggerFactory.getLogger("DataPacketEncoder");

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     */
    public DataPacketEncoder() {

        DataPacketEncoder.logger.info("DataPacketEncoder instance is created");
    }

    @Override
    protected void encode(final ChannelHandlerContext ctx, final DiscoverDataPacket packet, final List<Object> out)
        throws Exception {

        Message msgMessage = packet.getMessage();

        DatagramPacket outPacket = new DatagramPacket(EncoderUtil.objectToByte(msgMessage), packet.getAddress());

        out.add(outPacket);

    }
}