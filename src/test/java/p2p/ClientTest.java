/*
 * COPYRIGHT. HSBC HOLDINGS PLC 2016. ALL RIGHTS RESERVED.
 * 
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of HSBC Holdings plc.
 */
package p2p;

import net.p2p.client.P2PClient;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.SpringConfig;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class ClientTest extends P2PClient {

    public static void main(final String[] args) {

        AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        ClientTest clientTest = new ClientTest();

        clientTest.connect();


    }
}
