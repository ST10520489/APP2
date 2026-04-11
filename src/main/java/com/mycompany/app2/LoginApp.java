/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app2;

/**
 *
 * @author Alulutho
 */
public class LoginApp {
    
    private String storedUsername;
    private String storedPassword;
    private String storedCellPhone;
    
    //checking the username
    public boolean checkUsername(String username){
        return username.contains("_") && username.length() <=5;
    }
    //checking the password
    public boolean checkPasswordComplexity(String password){
        boolean hasUpperCase = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;
        
        for (char ch: password.toCharArray()){
            if (Character.isUpperCase(ch)){
                hasUpperCase = true;
            }if (Character.isDigit(ch)){
                hasNumber = true;
            }if (!Character.isLetterOrDigit(ch)){
                hasSpecialChar = true;
            }
        }
        return password.length() >=8 && hasUpperCase && hasNumber && hasSpecialChar;
    }
    public boolean checkCellPhone(String cellPhone){
        return cellPhone.startsWith("+27") && cellPhone.length()==12;
    }
    public String registerUser(String username, String password, String CellPhone){
        if (!checkUsername(username)){
            return "Username is not correctly formatted.";
        }
        if (!checkPasswordComplexity(password)){
            return "Password does not meet complexity requirements.";
        }
        if (!checkCellPhone(CellPhone)){
            return "Cell phone number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
        //store details if all is correct
        storedUsername = username;
        storedPassword = password;
        storedCellPhone = CellPhone;
        
        return "User registered successfully";
    }
    //login user
    public boolean loginUser(String username, String password){
        return username.equals(storedUsername) && password.equals(storedPassword);
    }
    //return login status
    public String returnLoginStatus(boolean loginSuccess){
        if (loginSuccess){
            return "Login successfully! Welcome back.";
        }else{
            return "Login failed! Username or password incorrect.";
        }
    }
    
}
