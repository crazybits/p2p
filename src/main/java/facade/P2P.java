package facade;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.p2p.peerdiscovery.PeerDiscoveryManager;
import net.p2p.server.P2PServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
@Component
public class P2P {

    @Autowired
    P2PServer p2pServer;

    @Autowired
    PeerDiscoveryManager peerDiscoveryManager;


    public void start() throws InterruptedException {


        ExecutorService threadExecutorService = Executors.newFixedThreadPool(1);

        threadExecutorService.execute(this.p2pServer);

        Thread.sleep(3000);

        this.peerDiscoveryManager.startDiscovery();


    }
}
