import java.io.UnsupportedEncodingException;
import java.util.BitSet;
import java.util.TreeMap;

import org.junit.Test;


/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class XORTest {

    @Test
    public void testXOR() throws UnsupportedEncodingException {


        BitSet bitSet = new BitSet(32);

        bitSet.set(0, true);
        bitSet.set(2, true);
        bitSet.set(4, true);
        bitSet.set(6, true);

        BitSet bitSet2 = new BitSet(32);


        bitSet2.xor(bitSet);

        System.out.println(bitSet2.toString());

        System.out.println(Integer.toBinaryString(8));
    }

    @Test
    public void testBit() {
        int i = 1;

        i = i << 0;

        System.out.println(i);

        i = i << 2;

        System.out.println(i);
    }

    @Test
    public void testTree() {
        TreeMap tree = new TreeMap<>();

        String test = null;

        tree.put("test", test.trim());
    }
}
