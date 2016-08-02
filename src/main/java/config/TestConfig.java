package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import p2p.ClientTest;
import p2p.ServerTest;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
@Configuration
public class TestConfig {

    @Bean
    public ClientTest clientTest() {
        return new ClientTest();

    }

    @Bean
    public ServerTest serverTest() {
        return new ServerTest();
    }

}
