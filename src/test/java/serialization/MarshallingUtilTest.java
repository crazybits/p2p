package serialization;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import net.p2p.protocol.Message;
import net.p2p.protocol.MessageFactory;

import org.junit.Test;

import util.MarshallingUtil;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class MarshallingUtilTest {

    @Test
    public void testMarshallingUtil() throws IOException, ClassNotFoundException {


        Message msgMessage = MessageFactory.createHelloMessage();


        byte[] out = MarshallingUtil.marshallingObjecToByte(msgMessage);


        Message object = (Message) MarshallingUtil.unMarshallingByteToObject(out);


        assertEquals(1, object.getHeader().getVersion());


    }
}
