/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.quickchat;
/**
 *
 * @author Alulutho
 */
import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class QuickChat {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Login user = new Login();

        System.out.println("===== REGISTER =====");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        String username;
        String password;
        String phone;

        // Username
        while (true) {
            System.out.print("Create username: ");
            username = scanner.nextLine();

            if (user.checkUserName(username)) {
                System.out.println("Username captured successfully");                     
                break;
            } else {
                System.out.println("Username must contain an underscore (_) and be no more than 5 characters");
            }
        }
        while (true) {
            System.out.print("Create password: ");
            password = scanner.nextLine();

            if (user.checkPasswordComplexity(password)) {
                System.out.println("Password captured successfully");
                break;
            } else {
                System.out.println("Password must contain a capital letter, a number, a special character and be at least 8 characters long");
            }
        }
        while (true) {

            System.out.print("Enter phone number (+27): ");
            phone = scanner.nextLine();

            if (user.checkCellPhoneNumber(phone)) {
                System.out.println("Phone number captured");
                break;
            } else {
                System.out.println("Invalid phone number");                     
            }
        }
        System.out.println(user.registerUser( username, password, phone));
                
   

        System.out.println();
        // Login
        while (true) {
            System.out.println("===== LOGIN =====");

            System.out.print("Enter username: ");
            String loginUsername =  scanner.nextLine();

            System.out.print("Enter password: ");
            String loginPassword =  scanner.nextLine();

            if (user.loginUser(loginUsername,loginPassword)){
                System.out.println(user.returnLoginStatus (loginUsername, loginPassword, firstName, lastName));                      

                Messaging.main(null);
                break;
            } else {
                System.out.println("Incorrect login details");
            }
        }
    }
}

class Login {
    String registeredUsername;
    String registeredPassword;
    String registeredPhone;

   
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        String regex =  "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&+=]).{8,}$";
       
        return password.matches(regex);
    }
    
    public boolean checkCellPhoneNumber(String phone) {
        return phone.startsWith("+27") && phone.length() == 12;
    }
    
    // Register user
    public String registerUser(String username, String password, String phone) {
            
        if (!checkUserName(username)) {
            return
            "Username is not correctly formatted.";
        }
        
        if (!checkPasswordComplexity(password)) {
            return
                "Password does not meet complexity requirements.";
        }

        if (!checkCellPhoneNumber(phone)) {
            return
                "Cell phone number incorrectly formatted.";
        }
        registeredUsername = username;
        registeredPassword = password;
        registeredPhone = phone;

        return
             "User registered successfully";
    }
 
    public boolean loginUser(String username,String password) {

        return username.equals(registeredUsername) && password.equals(registeredPassword);       
    }
    // Return login message
    public String returnLoginStatus(String username, String password,String firstName,  String lastName) {          

        if (loginUser( username, password)) {              
            return
                 "Welcome " + firstName + ", " + lastName + " it is great to see you again";          
        }
        return
             "Username or password incorrect";
    }
}
// Part 2 starts from here
class Messaging {
    static Scanner input = new Scanner(System.in);
    static int totalMessagesSent = 0;

    public static void main(String[] args){
        System.out.println();
        System.out.println("Welcome to QuickChat");
        System.out.println("How many messages would you like to send?");
        int maxMessages =  input.nextInt();      
        input.nextLine();

        while(true){
            System.out.println("===== QUICKCHAT MENU =====");
            System.out.println("1) Send Message");
            System.out.println("2) Show Sent Messages");
            System.out.println("3) Quit");
            System.out.print("Enter valid option: ");
            int choice = input.nextInt();
            input.nextLine();

            switch(choice){
                case 1:
                   if (Message.returnTotalMessages() < maxMessages) {
                        sendMessage();
                    }else{
                        System.out.println("Message limit reached");
                    }
                    break;

                case 2:
                    System.out.println(Message.printMessages());                
                    break;

                case 3:
                    System.out.println("Total messages sent: " +  Message.returnTotalMessages());
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    public static void sendMessage(){

        Random random = new Random();
        long messageID = 1000000000L+(long) (random.nextDouble()*9000000000L);
      
        String recipient = "";
        while(true){
        System.out.println("Recipient (+27): ");
            recipient = input.nextLine();
            
            if (recipient.startsWith("+27") && recipient.length() == 12) {
                System.out.println("Recipient valid");
                break;
            }else{
                System.out.println("Recipient invalid, please try again");
            }
    }
        String message = "";
        while(true){
        System.out.println("Enter message: ");
             message = input.nextLine();
 
        if (message.length() > 250) {
             System.out.println("Please enter a message of less than 250 characters"); 
        if (input.hasNextLine()){
            input.nextLine();
        }
        }else{
           break;                      
        }
    }
        Message msg = new Message( messageID, recipient, message);
        
        if(!msg.checkMessageID()){
            System.out.println("Message ID invalid");
            return;
        }
        msg.createMessageHash();
        System.out.println(msg.SentMessage());
    }
}
class Message {

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
        return id.length() ==10;
    }
    public String checkRecipientCell(){

        if(recipient.startsWith("+27") && recipient.length() ==12){
            return
            "Recipient valid";
        }
        return
        "Recipient invalid";
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
                return
                "Message sent";
                
            case 2:
                storeMessage();
                return
                "Message stored";

            case 3:
                return
                "Message disregarded";

            default:
                return
                "Invalid option";
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