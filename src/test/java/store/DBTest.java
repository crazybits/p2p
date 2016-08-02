package store;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import net.p2p.protocol.Message;
import net.p2p.protocol.MessageFactory;
import util.MarshallingUtil;
import datasource.KeyValueDataSource;
import datasource.mapdb.MapDBDataSource;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class DBTest {

    public static void main(final String[] args) throws ClassNotFoundException {


        KeyValueDataSource dataSource = new MapDBDataSource();

        dataSource.setName("test");
        dataSource.init();

        Message msgMessage = MessageFactory.createHelloMessage();

        try {

            byte[] in = MarshallingUtil.objectToByte(msgMessage);

            dataSource.put(in, in);

            byte[] out = dataSource.get(in);

            assertEquals(in.length, out.length);

            Message unmarMessage = (Message) MarshallingUtil.byteToObject(out);

            assertEquals(unmarMessage, msgMessage);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
