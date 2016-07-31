package store;

import java.math.BigInteger;
import java.util.Random;

import datasource.KeyValueDataSource;
import datasource.mapdb.MapDBDataSource;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class DBTest {

    public static void main(final String[] args) {


        KeyValueDataSource dataSource = new MapDBDataSource();

        dataSource.setName("test");
        dataSource.init();

        byte[] key = new byte[100];
        byte[] value = new byte[100];
        new Random().nextBytes(key);
        new Random().nextBytes(value);
        dataSource.put(key, value);

        BigInteger bigInteger = new BigInteger(1, dataSource.get(key));

        System.out.println(bigInteger.toString(16));

    }
}
