import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.SpringConfig;
import facade.P2PFactory;


/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class Starter {

    static AnnotationConfigApplicationContext springContext;

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     * 
     * @param args
     * @throws InterruptedException
     */

    public static void main(final String[] args) throws InterruptedException {

        Starter.springContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        P2PFactory.createP2P(Starter.springContext);

    }
}
