package net.p2p.kademlia.net;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.net.InetSocketAddress;
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

    @Override
    protected void decode(final ChannelHandlerContext ctx, final DatagramPacket packet, final List<Object> out) throws Exception {

        try {

            ByteBuf buf = Unpooled.copiedBuffer(packet.content());

            InetSocketAddress sender = packet.sender();

            Message message = (Message) EncoderUtil.byteToObject(buf);

            DiscoverDataPacket inPacket = new DiscoverDataPacket(message, sender);

            out.add(inPacket);


        } catch (Exception e) {
            DataPacketDecoder.logger.error("failed to decode the dicovert packet", e);
            ctx.close();
        }

    }
}
