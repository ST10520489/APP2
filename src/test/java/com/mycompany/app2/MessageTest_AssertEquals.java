/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.app2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alulutho
 */
public class MessageTest_AssertEquals {

    @Test
    public void testMessageLength_Success() {
        String message = "Hi Mike, can you join us for dinner tonight?";
        assertTrue(message.length() <= 250);
    }

    @Test
    public void testMessageLength_Failure() {
        String message = "A".repeat(260); // 260 characters

        int excess = message.length() - 250;

        String expected = "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        String actual;

        if (message.length() > 250) {
            actual = "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        } else {
            actual = "Message ready to send.";
        }

        assertEquals(expected, actual);
    }

    @Test
    public void testRecipientNumber_Success() {
        Messaging msg = new Messaging("001", "+27718693002",
                "Hi Mike, can you join us for dinner tonight?", 1);

        assertEquals("Cell number successfully captured.",
                msg.checkRecipientCell());
    }

    @Test
    public void testRecipientNumber_Failure() {
        Messaging msg = new Messaging("002", "08575975889",
                "Hi Keegan, did you receive the payment?", 2);

        assertEquals(
            "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.",
            msg.checkRecipientCell()
        );
    }

    @Test
    public void testMessageHash_Correct() {
        Messaging msg = new Messaging("00", "+27718693002",
                "Hi Mike, can you join us for dinner tonight", 0);

        assertEquals("00:0:HITONIGHT", msg.createMessageHash());
    }

    @Test
    public void testMultipleMessageHashes() {

        Messaging[] messages = {
            new Messaging("01", "+27718693002", "Call me tommorrow", 1),
            new Messaging("02", "+27718693002", "See you later", 2),
            new Messaging("03", "+27718693002", "How are you", 3)
        };

        for (Messaging msg : messages) {
            assertNotNull(msg.createMessageHash());
        }
    }

    @Test
    public void testMessageID_Created() {
        Messaging msg = new Messaging("12345", "+27718693002",
                "Test message", 1);

        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testSendMessage() {
        Messaging msg = new Messaging("001", "+27718693002",
                "Hello", 1);

        assertEquals("Message sent successfully",
                msg.sentMessage(1));
    }

    @Test
    public void testDisregardMessage() {
        Messaging msg = new Messaging("002", "+27718693002",
                "Hello", 2);

        assertEquals("Message deleted",
                msg.sentMessage(2));
    }

    @Test
    public void testStoreMessage() {
        Messaging msg = new Messaging("003", "+27718693002",
                "Hello", 3);

        assertEquals("Message stored successfully",
                msg.sentMessage(3));
    }
}