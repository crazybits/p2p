package serialization;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

import java.io.IOException;

import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class MarshallingFactory {

    public static MarshallingDecoder buildMarshallingDecoder() {

        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("river");

        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(3);

        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);

        int maxSize = 1024 << 2;
        MarshallingDecoder decoder = new MarshallingDecoder(provider, maxSize);
        return decoder;

    }

    public static MarshallingEncoder buildMarshallingEncoder() {

        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("river");

        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(3);

        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);
        MarshallingEncoder encoder = new MarshallingEncoder(provider);


        return encoder;
    }

    public static Marshaller buildMarshaller() throws IOException {

        MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("river");
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(3);

        Marshaller marshaller = marshallerFactory.createMarshaller(configuration);
        return marshaller;

    }

    public static Unmarshaller buildUnmarshaller() throws IOException {

        MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("river");
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(3);

        Unmarshaller unmarshaller = marshallerFactory.createUnmarshaller(configuration);
        return unmarshaller;

    }
}
