package store;

import org.junit.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class ConfigTest {

    @Test
    public void testConfig() {

        Config config = ConfigFactory.defaultApplication();

        System.out.println(config.getString("database.dir"));
    }
}
