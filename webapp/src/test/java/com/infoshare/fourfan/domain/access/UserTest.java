package com.infoshare.fourfan.domain.access;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    //TODO check Junit library correct implementation for @Test to work
    //done: typo error i package name (fourfun instead of fourfan)
//    @Test
//    @DisplayName("Verifies if object UserBuilder and subsequently User created/exists with input name an surname data provided in method's parameter")
//    public void testIfUserCreatedCorrectlyForValidNameAndSurname() {
//        // given
//
//        // when
//        User user = new User.UserBuilder("Piotr", "Kowalski")
//                .build();
//        System.out.println(user);
//        // then
//        assertEquals("Piotr", user.getName());
//        assertEquals("Kowalski", user.getLastName());
//        assertNotEquals("Pawel", user.getName());
//    }
//
//    @Test
//    @DisplayName("Checks if User created if it equals null")
//    public void testIfUserCreatedForNullNameAndSurname() {
//        // given
//
//        // when
//        User user = new User.UserBuilder(null, null)
//                .build();
//        System.out.println(user);
//        // then
//        assertNull(null, user.getName());
//        assertNull(null, user.getLastName());
//    }
//
//    @Test
//    @DisplayName("Check if in case empty name and surname given, exception would be thrown")
//    public void testIfExceptionThrownForEmptyNameAndSurname() {
//        assertThrows(IllegalArgumentException.class, () -> new User.UserBuilder());
//    }
//
//    @Test
//    @DisplayName("Check if in case nulls name and surname for user given, exception would be thrown")
//    public void testIfExceptionThrownForNullNameAndSurname() {
//        assertThrows(IllegalArgumentException.class, () -> new User.UserBuilder(null,null));
//    }
//}

    @Test
    @DisplayName("Verifies if user created/exists with input name and surname data provided as constructor arguments")
    public void testIfUserCreatedForValidNameAndSurname() {

        // given & when
        User user = new User("Peter", "peter-password");

        // then
        assertEquals("Peter", user.getLogin());
        assertEquals("peter-password", user.getPassword());
        assertNotEquals("peter", user.getLogin());

    }

    @Test
    @DisplayName("Checks if Exception thrown in case of emty/bank login")
    public void testIfExceptionThrownInCaseOfEmptyLogin(){
        // given & when
        User user = new User("","password-check");
        assertThrows(IllegalArgumentException.class, ()->new User("","password-check"));
        
    }
}