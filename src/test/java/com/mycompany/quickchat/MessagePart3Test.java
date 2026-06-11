/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.quickchat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessagePart3Test {

    @BeforeEach
    public void setUp() {

    Message.sentCount = 0;
    Message.storedCount = 0;
    Message.disregardCount = 0;

    Message.sentMessages[0] = "Did you get the cake?";
    Message.messageIDs[0] = "1000000001";
    Message.recipients[0] = "+27834557896";
    Message.messages[0] = "Did you get the cake?";
    Message.messageHashes[0] = "H1";
    Message.storedMessages[0] = "Where are you? You are late! I have asked you to be on time.";
    Message.disregardedMessages[0] = "Yohoooo, I am at your gate.";
        
    Message.sentMessages[1] = "It is dinner time!";
    Message.messageIDs[1] = "083884567";
    Message.recipients[1] = "+27838884567";
    Message.messages[1] = "It is dinner time!";
    Message.messageHashes[1] = "H2";
    Message.storedMessages[1] =  "Ok, I am leaving without you.";

    Message.sentCount = 2;
    Message.storedCount = 2;
    Message.disregardCount = 1;
}
    @Test
    public void testSentMessagesArray() {

        assertEquals("Did you get the cake?", Message.sentMessages[0]);
        assertEquals("It is dinner time!", Message.sentMessages[1]);
    }

    @Test
    public void testLongestMessage() {

        String expected = "Where are you? You are late! I have asked you to be on time.";
            
        assertEquals(expected, Message.getLongestMessage());
    }

    @Test
    public void testSearchMessageID() {

        assertEquals("It is dinner time!", Message.searchMessageID("083884567"));
    }

    @Test
    public void testSearchRecipient() {

        String expected = "It is dinner time!";
            
        assertEquals(expected, Message.searchRecipient("+27838884567"));
            
    }
    @Test
    public void testDeleteMessage() {

        String hash = Message.messageHashes[0];

        String expected = "Message: \"Did you get the cake?\" successfully deleted.";
            
        assertEquals(expected, Message.deleteMessage(hash));
            
    }

    @Test
    public void testDisplayReport() {

        String report = Message.displayReport();

        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("It is dinner time!"));
    }
}