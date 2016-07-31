package net.p2p.kademlia.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.net.InetSocketAddress;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class DataPacketEncoder extends MessageToMessageEncoder<ByteBuf> {

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

    protected void encode(final ChannelHandlerContext ctx, final ByteBuf buff, final List<Object> out) throws Exception {

        DatagramPacket packet = new DatagramPacket(buff.retain(), new InetSocketAddress("127.0.0.1", 8889));

        out.add(packet);

    }
}
