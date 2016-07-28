package facade;

import net.p2p.client.P2PClient;
import net.p2p.server.P2PServer;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class P2P {

    @Autowired
    P2PServer server;
    @Autowired
    P2PClient client;

    public void start() throws InterruptedException {

        this.server.start(1234);

        this.client.start();
    }


}
