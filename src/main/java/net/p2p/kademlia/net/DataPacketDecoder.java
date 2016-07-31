package net.p2p.kademlia.net;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class DataPacketDecoder extends ChannelHandlerAdapter {


    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) {

        DatagramPacket packet = (DatagramPacket) msg;
        // /ctx.writeAndFlush(packet.content());
        ctx.fireChannelRead(packet.content());
    }

}
