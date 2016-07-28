import net.p2p.protocol.MessageTypes;

import org.junit.Test;

/*
 * COPYRIGHT. HSBC HOLDINGS PLC 2016. ALL RIGHTS RESERVED.
 * 
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of HSBC Holdings plc.
 */

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class testEnumSize {

    @Test
    public void testEmuesize() {
        MessageTypes testTypes = MessageTypes.HELLO;
        System.out.println(testTypes);
    }
}
