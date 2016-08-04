package kademlia;

import net.p2p.kademlia.net.KademliaNodeDiscovery;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConfig;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class KademliaListenerTest {

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @param args
     */
    public static void main(final String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        KademliaNodeDiscovery nodeDiscovery = ctx.getBean(KademliaNodeDiscovery.class);
        nodeDiscovery.startKademliaDiscovry();
        ctx.close();

    }
}
