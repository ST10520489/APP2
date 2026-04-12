/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.app2;

/**
 *
 * @author Alulutho
 */
import org.junit.Test;
import static org.junit.Assert.*;
public class LoginAppTest {
    
    public LoginAppTest() {
    }

    LoginApp app = new LoginApp();


    @Test
    public void testUsernameCorrect() {
        assertTrue(app.checkUsername("kyl_1"));
    }

    @Test
    public void testUsernameIncorrect() {
        assertFalse(app.checkUsername("kyle!!!!!!"));
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
        assertTrue(app.checkCellPhone("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrect() {
        assertFalse(app.checkCellPhone("08966553"));
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

        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code; please correct the number and try again.", result);
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