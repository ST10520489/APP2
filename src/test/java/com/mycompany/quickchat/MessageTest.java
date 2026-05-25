/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.quickchat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
/**
 *
 * @author Alulutho
 */
public class MessageTest{

   @BeforeEach
   public void setUp(){
       Message.totalMessages = 0;
       Message.allMessages = "";
   }

    @Test
    public void testMessage1_CheckMessageID() {
        Message msg = new Message(1000000001L,
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?"
        );
        assertTrue(msg.checkMessageID());
    }
    
    @Test
    public void testMessage1_CheckRecipientCell() {
        Message msg = new Message(1000000001L,
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?"
        );
        assertEquals("Recipient valid", msg.checkRecipientCell());
    }

    @Test
    public void testMessage1_CreateHash() {
        Message msg = new Message(1000000001L,
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?"
        );
        assertNotNull(msg.createMessageHash());
    }

    @Test
    public void testMessage1_SendMessage() {
        Message msg = new Message(1000000001L,
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?"
        );
        Message.totalMessages++;
        assertEquals(1, Message.returnTotalMessages());
    }

    @Test
    public void testMessage2_InvalidRecipient() {
        Message msg = new Message(1000000002L,
            "08575975889",
            "Hi Keegan, did you receive the payment?"
        );
        assertEquals("Recipient invalid", msg.checkRecipientCell());
    }

    @Test
    public void testMessage2_CreateHash() {
        Message msg = new Message(1000000002L,
            "08575975889",
            "Hi Keegan, did you receive the payment?"
        );
        assertNotNull(msg.createMessageHash());
    }

    @Test
    public void testReturnTotalMessages(){
        Message msg1 = new Message(1000000001L,
              "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );
        Message msg2 = new Message(1000000002L,
               "08575975889",
                "Hi Keegan, did you receive the payment?"
        );
        Message.totalMessages++;       
        assertEquals(1, Message.returnTotalMessages());
    }
}
