/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.app2;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 *
 * @author Alulutho
 */
public class MessageTest{

   @Before
   public void setUp(){
       Messaging.resetTotalMessages();
   }

    @Test
    public void testMessage1_CheckMessageID() {
        Messaging msg = new Messaging("001",
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?",
            1
        );

        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testMessage1_CheckRecipientCell() {
        Messaging msg = new Messaging("001",
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?",
            1
        );

        assertEquals("Cell number successfully captured.",
                msg.checkRecipientCell());
    }

    @Test
    public void testMessage1_CreateHash() {
        Messaging msg = new Messaging("001",
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?",
            1
        );

        assertNotNull(msg.createMessageHash());
    }

    @Test
    public void testMessage1_SendMessage() {
        Messaging msg = new Messaging("001",
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?",
            1
        );

        assertEquals("Message successfully sent",
                msg.sentMessage(1));
    }

   // Test data for message 2
    @Test
    public void testMessage2_InvalidRecipient() {
        Messaging msg = new Messaging("002",
            "08575975889",
            "Hi Keegan, did you receive the payment?",
            2
        );

        assertEquals(
            "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.",
            msg.checkRecipientCell()
        );
    }

    @Test
    public void testMessage2_CreateHash() {
        Messaging msg = new Messaging("002",
            "08575975889",
            "Hi Keegan, did you receive the payment?",
            2
        );

        assertNotNull(msg.createMessageHash());
    }

    @Test
    public void testMessage2_DiscardMessage() {
        Messaging msg = new Messaging("002",
            "08575975889",
            "Hi Keegan, did you receive the payment?",
            2
        );

        assertEquals("Press 0 to delete the message",
                msg.sentMessage(2));
    }
    // Total messages test
    @Test
    public void testReturnTotalMessages(){
        Messaging msg1 = new Messaging("001",
              "+27718693002",
                "Hi Mike, can you join us for dinner tonight?",
                1
        );
        Messaging msg2 = new Messaging("002",
               "08575975889",
                "Hi Keegan, did you receive the payment?",
                2
        );
        msg1.sentMessage(1);// message sent
        msg2.sentMessage(2);// message deleted 
        
        assertEquals(1, Messaging.returnTotalMessages());
    }
}