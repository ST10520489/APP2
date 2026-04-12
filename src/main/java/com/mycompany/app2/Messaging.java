/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.app2;

/**
 *
 * @author Alulutho
 */
import java.util.Scanner;
import java.util.Random;

public class Messaging {
    static Scanner input = new Scanner(System.in);
    static int messageCount = 0;
//part 3 was incorparated into this
    private String messageID;
    private String recipient;
    private String messageText;
    private int messageNumber;
    
    public Messaging(String messageID, String recipient, String messageText, int messageNumber) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
    }

    // Arrays
    static String[] messages;
    static String[] messageIDs;
    static String[] messageHashes;
    static String[] recipients;

    // Part 3 arrays
    static String[] sentMessages;
    static String[] disregardedMessages;
    static String[] storedMessages;

    // Login
    public static boolean loginUser(String username, String password) {
        return username.equals("kyl_1") && password.equals("Ch&&sec@ke99!");
    }

    public static void main(String[] args) {

        System.out.print("Enter username: ");
        String username = input.nextLine().trim();

        System.out.print("Enter password: ");
        String password = input.nextLine().trim();

        if (!loginUser(username, password)) {
            System.out.println("Login failed. Cannot send messages.");
            return;
        }

        System.out.println("Welcome to QuickChat.");

        System.out.print("How many messages would you like to send? ");
        int totalMessages = input.nextInt();
        input.nextLine();

        // Initialize the arrays
        messages = new String[totalMessages];
        messageIDs = new String[totalMessages];
        messageHashes = new String[totalMessages];
        recipients = new String[totalMessages];

        sentMessages = new String[totalMessages];
        disregardedMessages = new String[totalMessages];
        storedMessages = new String[totalMessages];

        int choice = 0;

        while (choice != 4) {

            System.out.println("\n1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Stored Messages");
            System.out.println("4) Quit");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    sendMessages(totalMessages);
                    break;

                case 2:
                    showSentMessages();
                    break;

                case 3:
                    storedMessagesMenu();
                    break;

                case 4:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // Send messages
    public static void sendMessages(int totalMessages) {

        while (messageCount < totalMessages) {

            System.out.print("Enter recipient number: ");
            String recipient = input.nextLine();

            if (!recipient.matches("\\+27\\d{9}")) {
                System.out.println("Invalid recipient number.");
                continue;
            }

            System.out.print("Enter your message: ");
            String message = input.nextLine();

            if (message.length() > 250) {
                System.out.println("Please enter a message of less than 250 characters.");
                continue;
            }

            String messageID = generateMessageID();
            String messageHash = createMessageHash(messageID, message, messageCount);

            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message");

            int option = input.nextInt();
            input.nextLine();

            switch (option) {

                case 1: // SEND
                    messages[messageCount] = message;
                    sentMessages[messageCount] = message;
                    messageIDs[messageCount] = messageID;
                    messageHashes[messageCount] = messageHash;
                    recipients[messageCount] = recipient;

                    messageCount++;

                    System.out.println("Message successfully sent");
                    displayMessage(messageID, messageHash, recipient, message);
                    break;

                case 2: // DISREGARD or DELETE
                    disregardedMessages[messageCount] = message;
                    System.out.println("Message disregarded");
                    messageCount++;
                    break;

                case 3: // STORE
                    storedMessages[messageCount] = message;
                    messageIDs[messageCount] = messageID;
                    messageHashes[messageCount] = messageHash;
                    recipients[messageCount] = recipient;

                    System.out.println("Message stored");
                    messageCount++;
                    break;
            }
        }
           System.out.println("Total messages sent: " + messageCount);
    }

    // stored messages menu
    public static void storedMessagesMenu() {

        System.out.println("\nStored Messages Menu:");
        System.out.println("1) Show sender & recipient");
        System.out.println("2) Longest message");
        System.out.println("3) Search by ID");
        System.out.println("4) Search by recipient");
        System.out.println("5) Delete by hash");
        System.out.println("6) Full report");

        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
                displaySendersAndRecipients();
                break;
            case 2:
                displayLongestMessage();
                break;
            case 3:
                searchByID();
                break;
            case 4:
                searchByRecipient();
                break;
            case 5:
                deleteByHash();
                break;
            case 6:
                displayFullReport();
                break;
        }
    }

    
    public static void displaySendersAndRecipients() {
        for (int i = 0; i < storedMessages.length; i++) {
            if (storedMessages[i] != null) {
                System.out.println("Sender: User | Recipient: " + recipients[i]);
            }
        }
    }

    public static void displayLongestMessage() {
        String longest = "";

        for (String msg : storedMessages) {
            if (msg != null && msg.length() > longest.length()) {
                longest = msg;
            }
        }

        System.out.println("Longest message: " + longest);
    }

    public static void searchByID() {
        System.out.print("Enter ID: ");
        String id = input.nextLine();

        for (int i = 0; i < messageIDs.length; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(id)) {
                System.out.println("Recipient: " + recipients[i]);
                System.out.println("Message: " + storedMessages[i]);
                return;
            }
        }

        System.out.println("Not found.");
    }

    public static void searchByRecipient() {
        System.out.print("Enter recipient: ");
        String rec = input.nextLine();

        for (int i = 0; i < recipients.length; i++) {
            if (storedMessages[i] != null && recipients[i].equals(rec)) {
                System.out.println(storedMessages[i]);
            }
        }
    }

    public static void deleteByHash() {
        System.out.print("Enter hash: ");
        String hash = input.nextLine();

        for (int i = 0; i < messageHashes.length; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(hash)) {
                storedMessages[i] = null;
                System.out.println("Message deleted");
                return;
            }
        }

        System.out.println("Hash not found.");
    }

    public static void displayFullReport() {
        for (int i = 0; i < storedMessages.length; i++) {
            if (storedMessages[i] != null) {
                System.out.println("ID: " + messageIDs[i]);
                System.out.println("Hash: " + messageHashes[i]);
                System.out.println("Recipient: " + recipients[i]);
                System.out.println("Message: " + storedMessages[i]);
                System.out.println("------------------");
            }
        }
    }

    public static void showSentMessages() {
        for (String msg : sentMessages) {
            if (msg != null) {
                System.out.println(msg);
            }
        }
        }
    public static void populateTestData() {

    // Message 1 (Sent)
    recipients[0] = "+27834557896";
    messages[0] = "Did you get the cake?";
    sentMessages[0] = messages[0];
    messageIDs[0] = generateMessageID();
    messageHashes[0] = createMessageHash(messageIDs[0], messages[0], 0);

    // Message 2 (Stored)
    recipients[1] = "+27838884567";
    messages[1] = "Where are you? You are late! I have asked you to be on time.";
    storedMessages[1] = messages[1];
    messageIDs[1] = generateMessageID();
    messageHashes[1] = createMessageHash(messageIDs[1], messages[1], 1);

    // Message 3
    recipients[2] = "+27834484567";
    messages[2] = "Yohoooo, I am at your gate.";
    disregardedMessages[2] = messages[2];
    messageIDs[2] = generateMessageID();
    messageHashes[2] = createMessageHash(messageIDs[2], messages[2], 2);

    // Message 4 
    recipients[3] = "0838884567";
    messages[3] = "It is dinner time !";
    sentMessages[3] = messages[3];
    messageIDs[3] = generateMessageID();
    messageHashes[3] = createMessageHash(messageIDs[3], messages[3], 3);

    // Message 5 (Stored)
    recipients[4] = "+27838884567";
    messages[4] = "Ok, I am leaving without you.";
    storedMessages[4] = messages[4];
    messageIDs[4] = generateMessageID();
    messageHashes[4] = createMessageHash(messageIDs[4], messages[4], 4);

    messageCount = 5;

    }

    public static String generateMessageID() {
        Random rand = new Random();
        long number = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    public static String createMessageHash(String messageID, String message, int count) {
        String firstTwo = messageID.substring(0, 2);
        String[] words = message.split(" ");
        return (firstTwo + ":" + count + ":" + words[0] + words[words.length - 1]).toUpperCase();
    }

    public String createMessageHash() {
        return createMessageHash(this.messageID, this.messageText, this.messageNumber);
    }

    public static void displayMessage(String id, String hash, String recipient, String message) {
        System.out.println("--- Message Details ---");
        System.out.println("Message ID: " + id);
        System.out.println("Message Hash: " + hash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + message);
    }

    public String sentMessage(int option) {
        if (option == 1) {
            messageCount++;
            return "Message successfully sent";
        } else {
            return "Press 0 to delete the message";
        }
    }

    public static void resetTotalMessages() {
        messageCount = 0;
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("\\+27\\d{9}")) {
            return "Cell number successfully captured.";
        } else {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
    }

    public static int returnTotalMessages() {
        return messageCount;
    }
}