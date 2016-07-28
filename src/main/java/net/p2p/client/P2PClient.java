package net.p2p.client;

import net.p2p.peerdiscovery.PeerDiscoveryManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.SpringConfig;


public class P2PClient {

    @Autowired
    PeerDiscoveryManager peerDiscoveryManager;

    public static AnnotationConfigApplicationContext springContext;


    public static void main(final String[] args) {

        P2PClient.springContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        P2PClient.springContext.getBean(PeerDiscoveryManager.class).startDiscovery();

    }

    public void start() throws InterruptedException {

        this.peerDiscoveryManager.startDiscovery();

    }

}
