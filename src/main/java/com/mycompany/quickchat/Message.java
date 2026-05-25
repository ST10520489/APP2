/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

/**
 *
 * @author Alulutho
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Message {

    long messageID;
    String recipient;
    String messageText;
    String messageHash;

    static int totalMessages = 0;

    static String allMessages = "";

    Scanner input = new Scanner(System.in);

    public Message(long messageID, String recipient, String messageText){          
        this.messageID = messageID;
        this.recipient = recipient;
        this.messageText = messageText;
    }
    public boolean checkMessageID(){

        String id = String.valueOf(messageID);
        return id.length() == 10;
    }
    public String checkRecipientCell(){

        if(recipient.startsWith("+27") && recipient.length() == 12){
            return "Recipient valid";
        }
        return "Recipient invalid";
    }
    public String createMessageHash(){

        String[] words =  messageText.split(" ");
        String firstWord = words[0];
        String lastWord =  words[words.length-1];
        String id = String.valueOf(messageID);

        messageHash = id.substring(0,2) + ":" +totalMessages +":" + firstWord + lastWord;     
        messageHash = messageHash.toUpperCase();

        return messageHash;
    }
    // Send message options
    public String SentMessage(){
        System.out.println("==== MESSAGE MENU ====");
        System.out.println("1) Send");
        System.out.println("2) Store");
        System.out.println("3) Disregard");
        
        System.out.print("Enter valid option: ");
        int option = input.nextInt();
        input.nextLine();

        switch(option){

            case 1:
                totalMessages++;

                allMessages += "ID: " + messageID +
                   "\nRecipient: " + recipient +
                   "\nMessage: " + messageText +
                   "\nHash: " + messageHash +
                   "\n\n";
                return "Message sent";
                
            case 2:
                totalMessages++; // Stored messages now consume an allocation slot
                storeMessage();
                return "Message stored";

            case 3:
                return "Message disregarded";

            default:
                return "Invalid option";
        }
    }
    // Print all messages
    public static String printMessages(){
        return allMessages;
    }
    // Return number of total messages
    public static int returnTotalMessages(){
        return totalMessages;
    }
    // Store JSON
    public void storeMessage(){
        try{
            FileWriter file =  new FileWriter("messages.json",true);

            file.write("{");
            file.write("\"MessageID\":\"" + messageID +"\",\n");
            file.write( "\"Recipient\":\"" +recipient +"\",\n");          
            file.write( "\"Message\":\"" + messageText  +"\",\n");          
            file.write("\"Hash\":\"" + messageHash +"\",\n");
           
            file.write("}\n");
            file.close();
        }
        catch(IOException e){
            System.out.println("Error saving file");
        }
    }
}

