package serialization;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;

import net.p2p.protocol.Body;
import net.p2p.protocol.Header;
import net.p2p.protocol.Message;
import net.p2p.protocol.MessageFactory;
import net.p2p.protocol.MessageTypes;
import net.p2p.protocol.Peer;

import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.Unmarshaller;
import org.junit.Test;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class JbossMarshallingTest {

    @Test
    public void testMarshalling() throws IOException, ClassNotFoundException {

        Header header = new Header(1, MessageTypes.GET_PEERS);

        Peer peer = new Peer();
        peer.setAddress(InetSocketAddress.createUnresolved("127.0.0.1", 1234));

        Body body = new Body(peer);

        Message message = new Message(header, body);

        final Marshaller marshaller = MarshallingFactory.buildMarshaller();

        FileOutputStream oStream = new FileOutputStream("object.obj");
        marshaller.start(Marshalling.createByteOutput(oStream));
        marshaller.writeObject(message);
        marshaller.finish();
        oStream.close();

        final Unmarshaller unMarshaller = MarshallingFactory.buildUnmarshaller();

        FileInputStream iStream = new FileInputStream("object.obj");

        unMarshaller.start(Marshalling.createByteInput(iStream));

        Message unMarMessage = (Message) unMarshaller.readObject();

        unMarshaller.finish();

        System.out.println(unMarMessage.getHeader().getVersion());
        System.out.println(unMarMessage.getHeader().getMessageType());
        System.out.println(((Peer) unMarMessage.getBody().body).getAddress());

        assertEquals(message.getHeader().getVersion(), unMarMessage.getHeader().getVersion());
        assertEquals(message.getHeader().getMessageType(), unMarMessage.getHeader().getMessageType());
        assertEquals(((Peer) message.getBody().body).getAddress(), ((Peer) unMarMessage.getBody().body).getAddress());

    }

    @Test
    public void testMarshallToByte() throws IOException, ClassNotFoundException {

        Message message = MessageFactory.createHelloMessage();

        final Marshaller marshaller = MarshallingFactory.buildMarshaller();

        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        marshaller.start(Marshalling.createByteOutput(byteArrayOutputStream));
        marshaller.writeObject(message);

        byte[] result = byteArrayOutputStream.toByteArray();
        marshaller.finish();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(result);
        final Unmarshaller unMarshaller = MarshallingFactory.buildUnmarshaller();
        unMarshaller.start(Marshalling.createByteInput(byteArrayInputStream));

        Message unMarMessage = (Message) unMarshaller.readObject();

        assertEquals(unMarMessage, message);

        marshaller.finish();


    }
}
