/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

/**
 *
 * @author Alulutho
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Message {

    long messageID;
    String recipient;
    String messageText;
    String messageHash;
    //part 3 beginnning
    static String[] sentMessages = new String[100];
    static String[] storedMessages = new String[100];
    static String[] disregardedMessages = new String[100];

    static String[] messageHashes = new String[100];
    static String[] messageIDs = new String[100];
    static String[] recipients = new String[100];
    static String[] messages = new String[100];

    static int sentCount = 0;
    static int storedCount = 0;
    static int disregardCount = 0;
//end of part 3 (arrays)
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
                sentMessages[sentCount] = messageText;
                messageHashes[sentCount] = messageHash;
                messageIDs[sentCount] = String.valueOf(messageID);
                recipients[sentCount] = recipient;
                messages[sentCount] = messageText;

                sentCount++;
                totalMessages++;

                allMessages += "ID: " + messageID +
                   "\nRecipient: " + recipient +
                   "\nMessage: " + messageText +
                   "\nHash: " + messageHash +
                   "\n\n";
                return "Message sent";
                
            case 2:
                storedMessages[storedCount] = messageText;
                storedCount++;
                totalMessages++; 
                storeMessage();
                return "Message stored";

            case 3:
                disregardedMessages[disregardCount] = messageText;
                disregardCount++;
                return "Message disregarded";

            default:
                return "Invalid option";
        }
    }
    public static String printMessages(){
        return allMessages;
    }
    public static int returnTotalMessages(){
        return totalMessages;
    }
    public void storeMessage(){
        try{
            FileWriter file =  new FileWriter("messages.json",true);

            file.write("{");
            file.write("\"MessageID\":\"" + messageID +"\",\n");
            file.write( "\"Recipient\":\"" +recipient +"\",\n");          
            file.write( "\"Message\":\"" + messageText  +"\",\n");          
            file.write("\"Hash\":\"" + messageHash +"\"\n");
            file.write("}\n");
            file.close();
        }
        catch(IOException e){
            System.out.println("Error saving file");
        }
    }
    //part 3 methods
    public static String getLongestMessage() {
        String longest = "";

        for (int i = 0; i < storedCount; i++) {
          if (storedMessages[i] != null &&
              storedMessages[i].length() > longest.length()) {
               longest = storedMessages[i];
        }
    }
    return longest;
}
    public static String searchMessageID(String id) {

        for (int i = 0; i < sentCount; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(id)) {
            return messages[i];
        }
    }
    return "Message not found";
}
    public static String searchRecipient(String rec) {
        String result = "";

    for (int i = 0; i < sentCount; i++) {
        if (recipients[i] != null && recipients[i].equals(rec)) {
            result += messages[i] + "\n";
        }
    }
    return result.trim();
}
    public static String deleteMessage(String hash) {

        for (int i = 0; i < sentCount; i++) {
          if (messageHashes[i] != null && messageHashes[i].equals(hash)) {
              
            String deleted = messages[i];
            messages[i] = null;
            recipients[i] = null;
            messageIDs[i] = null;
            messageHashes[i] = null;

            return "Message: \"" + deleted + "\" successfully deleted.";
        }
    }
    return "Message not found";
}
    public static String displayReport() {
          String report = "";

         for (int i = 0; i < sentCount; i++) {
            if (messages[i] != null) {
            report += "Hash: " + messageHashes[i] + "\n";
            report += "Recipient: " + recipients[i] + "\n";
            report += "Message: " + messages[i] + "\n\n";
        }
    }

    return report;
}
    public static String readStoredMessages() {
    String result = "";

    try {
        BufferedReader reader = new BufferedReader(
                new FileReader("messages.json"));

        String line;
        while ((line = reader.readLine()) != null) {
            result += line + "\n";
        }
        reader.close();

    } catch (IOException e) {
        return "Error reading file";
    }
    return result;
}   
}

