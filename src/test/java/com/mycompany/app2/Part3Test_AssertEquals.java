/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app2;

/**
 *
 * @author Alulutho
 */
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Part3Test_AssertEquals {

    @Before
    public void setUp() {
        // Initialize arrays
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
    public void testLongestMessage() {
        String longest = "";

        for (String msg : Messaging.messages) {
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

        String first = "";
        String second = "";
        int count = 0;

        for (int i = 0; i < Messaging.recipients.length; i++) {
            if (Messaging.recipients[i] != null &&
                Messaging.recipients[i].equals(recipient)) {

                if (count == 0) {
                    first = Messaging.messages[i];
                } else {
                    second = Messaging.messages[i];
                }
                count++;
            }
        }

        assertEquals(
            "Where are you? You are late! I have asked you to be on time.",
            first
        );

        assertEquals(
            "Ok, I am leaving without you.",
            second
        );
    }

    @Test
    public void testDeleteMessage() {
        String hash = Messaging.messageHashes[1];

        for (int i = 0; i < Messaging.messageHashes.length; i++) {
            if (Messaging.messageHashes[i] != null &&
                Messaging.messageHashes[i].equals(hash)) {

                Messaging.storedMessages[i] = null;
            }
        }

        assertEquals(null, Messaging.storedMessages[1]);
    }

    @Test
    public void testSearchMessage() {
        String result = "";

        for (int i = 0; i < Messaging.recipients.length; i++) {
            if (Messaging.recipients[i] != null &&
                Messaging.recipients[i].equals("0838884567")) {

                result = Messaging.messages[i];
            }
        }

        assertEquals("It is dinner time !", result);
    }
}