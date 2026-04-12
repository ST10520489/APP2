/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.app2;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alulutho
 */
public class MessagePart3Test {
    
    @Before
    public void setUp() {
        Messaging.messages = new String[5];
        Messaging.messageIDs = new String[5];
        Messaging.messageHashes = new String[5];
        Messaging.recipients = new String[5];

        Messaging.sentMessages = new String[5];
        Messaging.disregardedMessages = new String[5];
        Messaging.storedMessages = new String[5];

        Messaging.populateTestData();
    }

    @Test
    public void testSentMessages() {
        assertEquals("Did you get the cake?", Messaging.sentMessages[0]);
        assertEquals("It is dinner time !", Messaging.sentMessages[3]);
    }

    @Test
    public void testStoredMessages() {
        assertEquals(
            "Where are you? You are late! I have asked you to be on time.",
            Messaging.storedMessages[1]
        );

        assertEquals(
            "Ok, I am leaving without you.",
            Messaging.storedMessages[4]
        );
    }

    @Test
    public void testDisregardedMessage() {
        assertEquals(
            "Yohoooo, I am at your gate.",
            Messaging.disregardedMessages[2]
        );
    }

    @Test
    public void testLongestMessage() {
        String longest = "";

        for (String msg : Messaging.storedMessages) {
            if (msg != null && msg.length() > longest.length()) {
                longest = msg;
            }
        }

        assertEquals(
            "Where are you? You are late! I have asked you to be on time.",
            longest
        );
    }

    @Test
    public void testSearchByRecipient() {
        String recipient = "+27838884567";
        int count = 0;

        for (int i = 0; i < Messaging.recipients.length; i++) {
            if (Messaging.recipients[i] != null &&
                Messaging.recipients[i].equals(recipient) &&
                Messaging.storedMessages[i] != null) {
                count++;
            }
        }

        assertEquals(2, count); // Message 2 & 5
    }
}