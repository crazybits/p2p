package config;

import net.p2p.client.P2PClient;
import net.p2p.peerdiscovery.PeerDiscoveryManager;
import net.p2p.server.P2PServer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import facade.P2P;

/**
 * <p>
 * <b> Spring bean configuration</b>
 * </p>
 */
@Configuration
public class SpringConfig {


    @Bean
    public PeerDiscoveryManager peerDiscoveryManager() {

        return new PeerDiscoveryManager();
    }

    @Bean
    public P2PServer p2PServer() {
        return new P2PServer();
    }

    @Bean
    public P2PClient p2PClient() {
        return new P2PClient();
    }

    @Bean
    public P2P p2p() {
        return new P2P();
    }
}
