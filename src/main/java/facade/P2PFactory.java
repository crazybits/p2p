package facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class P2PFactory {

    private static final Logger logger = LoggerFactory.getLogger("P2PFactory");

    public static void createP2P(final ApplicationContext springContext) {

        P2P p2p = springContext.getBean(P2P.class);
        try {
            p2p.start();
        } catch (Exception e) {
            P2PFactory.logger.error("Failed to start up P2P", e);
        }
    }
}
