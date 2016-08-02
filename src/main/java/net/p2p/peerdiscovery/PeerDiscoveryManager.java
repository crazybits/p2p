package net.p2p.peerdiscovery;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import net.p2p.protocol.Peer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
@Component
public class PeerDiscoveryManager {

    private static final Logger logger = LoggerFactory.getLogger("PeerDiscoveryManager");

    private static int corePoolSize = 10;

    private static int maximumPoolSize = 30;

    private static int keepAliveTime = 30;

    private static TimeUnit unit = TimeUnit.SECONDS;

    private static ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);

    // TODO: above settings should be read from the config file

    private final static Set<Peer> initPeers = Collections.synchronizedSet(new HashSet<Peer>());

    private final static Set<Peer> connectablePeers = Collections.synchronizedSet(new HashSet<Peer>());

    private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(PeerDiscoveryManager.corePoolSize,
        PeerDiscoveryManager.maximumPoolSize, PeerDiscoveryManager.keepAliveTime, PeerDiscoveryManager.unit,
        PeerDiscoveryManager.workQueue);


    public PeerDiscoveryManager() {
        PeerDiscoveryManager.logger.info("PeerDiscoveryManager instance has been initialed");
    }

    @PostConstruct
    private void initSeedNode() {

        Peer seedNode1 = new Peer("127.0.0.1", 8888);
        Peer seedNode2 = new Peer("127.0.0.1", 8888);
        Peer seedNode3 = new Peer("127.0.0.1", 8882);
        Peer seedNode4 = new Peer("127.0.0.1", 8881);

        PeerDiscoveryManager.initPeers.add(seedNode1);
        PeerDiscoveryManager.initPeers.add(seedNode2);
        PeerDiscoveryManager.initPeers.add(seedNode3);
        PeerDiscoveryManager.initPeers.add(seedNode4);

        // TODO: Move the real init seed node to config file

    }


    public void startDiscovery() {

        Set<Peer> peers = getInitPeers();

        for (Peer peer : peers) {

            PeerDiscovery peerDiscovery = new PeerDiscovery(peer);

            PeerDiscoveryManager.poolExecutor.execute(peerDiscovery);

        }


    }

    public void addPeers(final Set<Peer> peers) {

        PeerDiscoveryManager.connectablePeers.addAll(peers);

    }

    public void addPeer(final Peer peer) {

        PeerDiscoveryManager.connectablePeers.add(peer);

    }


    public Set<Peer> getConnectablePeers() {

        return PeerDiscoveryManager.connectablePeers;

    }

    public Set<Peer> getInitPeers() {

        return PeerDiscoveryManager.initPeers;

    }

}
