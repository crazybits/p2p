package serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.p2p.protocol.Message;
import net.p2p.protocol.MessageFactory;

import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.Unmarshaller;
import org.junit.Test;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class MarshallingTest {

    @Test
    public void testSerialObject() throws IOException, ClassNotFoundException {


        Message msg = MessageFactory.createHelloMessage();

        Marshaller marshaller = MarshallingFactory.buildMarshaller();


        final ByteArrayOutputStream baos = new ByteArrayOutputStream(10240);


        marshaller.start(Marshalling.createByteOutput(baos));
        marshaller.writeObject(msg);
        marshaller.finish();

        final byte[] out = baos.toByteArray();

        ByteBuf buff = Unpooled.copiedBuffer(out);
        ByteInput input = new ChannelBufferByteInput(buff);
        Unmarshaller unmarshaller = MarshallingFactory.buildUnmarshaller();
        unmarshaller.start(input);
        unmarshaller.finish();
        Message msgMessage = unmarshaller.readObject(Message.class);

        System.out.println(msgMessage.getHeader().getMessageType());

    }
}
