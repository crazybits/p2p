package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
@Configuration
public class AppConfig {

    @Bean
    public Config config() {
        Config config = ConfigFactory.load("application.properties");
        return config;

    }

}
