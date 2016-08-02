package config;

import net.p2p.client.P2PClient;
import net.p2p.kademlia.manager.NodeManager;
import net.p2p.kademlia.net.NodeDiscoveryWithUDP;
import net.p2p.peerdiscovery.PeerDiscoveryManager;
import net.p2p.server.P2PServer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import facade.P2P;

/**
 * <p>
 * <b> Spring bean configuration</b>
 * </p>
 */
@Configuration
@Import({CommonConfig.class, TestConfig.class})
public class AppConfig {

    @Bean
    public PeerDiscoveryManager peerDiscoveryManager() {

        return new PeerDiscoveryManager();
    }

    @Bean
    public P2PServer p2pServer() {
        return new P2PServer();
    }

    @Bean
    public P2PClient p2pClient() {
        return new P2PClient();
    }

    @Bean
    public P2P p2p() {
        return new P2P();
    }

    @Bean
    public NodeManager nodeManager() {

        return new NodeManager();
    }

    @Bean
    public NodeDiscoveryWithUDP nodeDiscoveryWithUDP() {
        return new NodeDiscoveryWithUDP();
    }
}