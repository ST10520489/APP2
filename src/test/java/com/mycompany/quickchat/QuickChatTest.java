/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.quickchat;
/**
 *
 * @author Alulutho
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuickChatTest {
    
    public QuickChatTest() {
    }

    Login app = new Login();

    @Test
    public void testUsernameCorrect() {
        assertTrue(app.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrect() {
        assertFalse(app.checkUserName("kyle!!!!!!"));
    }

    @Test
    public void testPasswordCorrect() {
        assertTrue(app.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordIncorrect() {
        assertFalse(app.checkPasswordComplexity("password"));
    }
    
    @Test
    public void testCellPhoneCorrect() {
        assertTrue(app.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrect() {
        assertFalse(app.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testRegisterUserSuccess() {
        String result = app.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("User registered successfully", result);
    }

    @Test
    public void testRegisterUserUsernameFail() {
        String result = app.registerUser("kyle!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Username is not correctly formatted.", result);
    }

    @Test
    public void testRegisterUserPasswordFail() {
        String result = app.registerUser("kyl_1", "password", "+27838968976");
        assertEquals("Password does not meet complexity requirements.", result);
    }

    @Test
    public void testRegisterUserCellFail() {
        String result = app.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals("Cell phone number incorrectly formatted.", result);
    }

    @Test
    public void testLoginSuccess() {
        app.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(app.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFail() {
        app.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(app.loginUser("kyl_1", "wrong"));
    }
}
