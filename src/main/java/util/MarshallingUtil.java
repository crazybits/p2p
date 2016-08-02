package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.ByteOutput;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.Unmarshaller;

import serialization.MarshallingFactory;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class MarshallingUtil {

    public static byte[] marshallingObjecToByte(final Object obj) throws IOException {

        Marshaller marshaller = MarshallingFactory.buildMarshaller();

        ByteArrayOutputStream baos = new ByteArrayOutputStream(10240);
        ByteOutput byteOutput = Marshalling.createByteOutput(baos);
        marshaller.start(byteOutput);

        try {
            marshaller.writeObject(obj);

        } finally {
            marshaller.finish();
        }

        byte[] bytes = baos.toByteArray();
        return bytes;

    }

    public static Object unMarshallingByteToObject(final byte[] bytes) throws IOException, ClassNotFoundException {

        Unmarshaller unmarshaller = MarshallingFactory.buildUnmarshaller();

        ByteInput byteInput = Marshalling.createByteInput(new ByteArrayInputStream(bytes));
        unmarshaller.start(byteInput);
        try {
            Object object = unmarshaller.readObject();
            return object;

        } finally {
            unmarshaller.finish();
        }
    }
}
