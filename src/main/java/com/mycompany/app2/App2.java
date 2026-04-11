/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.app2;

/**
 *
 * @author Alulutho
 */
import java.util.Scanner;
public class App2 {
    
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        
        System.out.print("create a username: ");
        String username = scanner.nextLine();
        System.out.println("length: " + username.length());
        System.out.println("has underscore: " + username.contains("_"));
        
      if(username.length() <=5 && username.contains("_")){
          System.out.println("username captured successfully");
      }
      else {
          System.out.println("username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters long");
      }
      //conditions the password must meet IF IT DOESNT WORK COME CHANGE THE REGEX
      String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&+=]).{8,}$";
      
      System.out.print("create password: ");
      String password = scanner.nextLine();
      
      if (password.matches(regex)){
          System.out.println("Password successfully captured");      
      }
      else{
          System.out.println("Password is not formatted correctly please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character");
      }
      System.out.print("Enter your cell phone number (with country code): ");
      String PhoneNumber = scanner.nextLine().trim();
      
      //must have a different regex name
      String PhoneNumregex = "^\\+\\d{1,3}\\d{1,10}$";
      
      if (PhoneNumber.matches(PhoneNumregex)){
          System.out.println("cell phone number successfully added");
      } else {
          System.out.println("cell phone number incorrectly formatted or does not contain international code");
      }
      //loging in
      String loginUsername;
      String loginPassword;
      
      while(true){
          System.out.print("Enter username: ");
          loginUsername = scanner.nextLine();
          
          System.out.print("Enter password: ");
          loginPassword = scanner.nextLine();
          
          if (loginUsername.equals(username) && loginPassword.equals(password)){
              System.out.println("Welcome " + firstName + "," + lastName + " it is great to see you again");
              break;
          }
          else{
              System.out.println("username or password is incorrect, please try again");
          }
      }
    }     
    }

