/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

/**
 *
 * @author Alulutho
 */
import java.util.Scanner;
import java.util.Random;

public class Messaging {
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
            }else{
                break;                      
            }
        }
        
        Message msg = new Message(messageID, recipient, message);
        
        if(!msg.checkMessageID()){
            System.out.println("Message ID invalid");
            return;
        }
        msg.createMessageHash();
        System.out.println(msg.SentMessage());
    }
}

