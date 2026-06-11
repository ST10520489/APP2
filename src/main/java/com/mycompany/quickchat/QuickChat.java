/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.quickchat;
/**
 *
 * @author Alulutho
 */
import java.util.Scanner;
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