package net.p2p.peerdiscovery;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import net.p2p.protocol.Peer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class PeerDiscoveryManager {

    private static final Logger logger = LoggerFactory.getLogger("PeerDiscoveryManager");

    private static int corePoolSize = 10;

    private static int maximumPoolSize = 30;

    private static int keepAliveTime = 30;

    private static TimeUnit unit = TimeUnit.SECONDS;

    private static ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(10);

    // TODO: above settings should be read from the config file

    private final static Set<Peer> peers = Collections.synchronizedSet(new HashSet<Peer>());

    private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(PeerDiscoveryManager.corePoolSize,
        PeerDiscoveryManager.maximumPoolSize, PeerDiscoveryManager.keepAliveTime, PeerDiscoveryManager.unit,
        PeerDiscoveryManager.workQueue);

    @PostConstruct
    private void init() {

        System.out.println("Manage init");


        Peer seedNodePeer = new Peer("127.0.0.1", 1234);
        Peer seedNodePeer2 = new Peer("127.0.0.1", 1235);
        Peer seedNodePeer3 = new Peer("127.0.0.1", 1236);
        Peer seedNodePeer4 = new Peer("127.0.0.1", 1237);

        PeerDiscoveryManager.peers.add(seedNodePeer);
        PeerDiscoveryManager.peers.add(seedNodePeer2);
        PeerDiscoveryManager.peers.add(seedNodePeer3);
        PeerDiscoveryManager.peers.add(seedNodePeer4);

    }

    public void runTask(final Runnable runable) {

        try {

            PeerDiscoveryManager.poolExecutor.execute(runable);

        } catch (RejectedExecutionException e) {
            PeerDiscoveryManager.logger.error("Exceed the allowed maximumPoolSize", e);
        } catch (Exception e) {
            PeerDiscoveryManager.logger.error("failed to create more thread in pool", e);
        }

    }

    public void startDiscovery() {

        Set<Peer> peers = getPeers();

        for (Peer peer : peers) {

            PeerDiscoveryThread thread = new PeerDiscoveryThread(peer);

            runTask(thread);

        }


    }

    public void addPeers(final Set<Peer> peers) {

        peers.addAll(peers);

    }


    public Set<Peer> getPeers() {

        return PeerDiscoveryManager.peers;

    }

}
