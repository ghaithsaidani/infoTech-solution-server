package org.example.services;

import org.example.models.User;
import org.example.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private static final long ID = 1L;
    private static final String EMAIL = "contact@infoTech.solutions.com";
    private static final String PASSWORD = "root";
    private static final String FIRSTNAME = "InfoTech";
    private static final String UPDATEDFIRSTNAME = "InfoTech1";
    private static final String LASTNAME = "Solutions";
    private static final long PHONE = 12345678;
    private static final String ROLE = "Agent";

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void should_find_all_users() {
        //Given
        User user = givenUser();
        List<User> users = Collections.singletonList(user);
        when(userRepository.findAll()).thenReturn(users);
        //When
        //Then
        assertEquals(users, userService.findAll());
    }

    @Test
    public void should_find_user_by_id() {
        //Given
        User user = givenUser();
        //When
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));
        //Then
        assertEquals(user, userService.findById(ID));
    }

    @Test
    public void should_return_null_find_user_by_id() {
        //Given
        when(userRepository.findById(ID)).thenReturn(Optional.empty());
        //When
        //Then
        assertNull(userService.findById(ID));
    }

    @Test
    public void should_save_user() {
        //Given
        User user = givenUser();
        user.setId(1L);
        when(userRepository.save(user)).thenReturn(user);
        //When
        //Then
        assertEquals(user, userService.save(user));
    }

    /*@Test
    public void should_return_null_save_user() {
        //Given
        User user = givenUser();
        when(userRepository.save(user)).thenReturn(user);
        //When
        //Then
        assertNull(userService.save(user));
    }*/

    @Test
    public void should_update_user() {
        //Given
        User user = givenUser();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        //When
        //Then
        assertEquals(user, userService.update(user));
    }

    /*@Test
    public void should_return_null_update_user() {
        //Given
        User user = givenUser();
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);
        //When

        //Then
        assertNull(userService.update(user));
    }*/

    @Test
    public void should_delete_user_by_id() {
        //Given
        User user = givenUser();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        //When
        doNothing().when(userRepository).deleteById(ID);
        //Then
        assertEquals(user, userService.deleteById(ID));
    }

    @Test
    public void should_return_null_delete_user_by_id() {
        //Given
        User user = givenUser();
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        //When
        doNothing().when(userRepository).deleteById(ID);
        //Then
        assertNull(userService.deleteById(ID));
    }

    private User givenUser() {
        User user = new User();
        user.setId(ID);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setFirstName(FIRSTNAME);
        user.setLastName(LASTNAME);
        user.setPhone(PHONE);
        user.setRole(ROLE);
        return user;
    }

}