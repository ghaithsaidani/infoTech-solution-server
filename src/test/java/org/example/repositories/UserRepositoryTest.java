package org.example.repositories;

import org.example.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    private static final long ID = 1L;
    private static final String EMAIL = "contact@infoTech.solutions.com";
    private static final String PASSWORD = "root";
    private static final String FIRSTNAME = "InfoTech";
    private static final String UPDATEDFIRSTNAME = "InfoTech1";
    private static final String LASTNAME = "Solutions";
    private static final long PHONE = 12345678;
    private static final String ROLE = "Agent";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void should_save_user() {
        //Given
        User user = givenUser();
        //When
        user = userRepository.save(user);
        //Then
        User actual = testEntityManager.find(User.class, user.getId());
        assertEquals(actual, user);
    }

    @Test
    public void should_find_user_by_id() {
        //Given
        User user = givenUser();
        user = testEntityManager.merge(user);
        testEntityManager.persist(user);
        //When
        Optional<User> actual = userRepository.findById(user.getId());
        //System.out.println(actual);
        //Then
        assertNotEquals(actual, Optional.empty());
        assertEquals(actual.get(), user);
        ;
    }

    @Test
    public void should_find_all_users() {
        //Given
        User user = givenUser();
        user = testEntityManager.merge(user);
        testEntityManager.persist(user);
        //When
        List<User> users = userRepository.findAll();
        //Then
        assertThat(users).contains(user);
        System.out.println(users);
    }

    @Test
    public void should_delete_user_by_id() {
        //Given
        User user = givenUser();
        user = testEntityManager.merge(user);
        testEntityManager.persist(user);
        //When
        userRepository.deleteById(user.getId());
        //Then
        User actual = testEntityManager.find(User.class, user.getId());
        assertNull(actual);
    }

    @Test
    public void should_update_user() {
        //Given
        User user = givenUser();
        user = testEntityManager.merge(user);
        testEntityManager.persist(user);
        User updatedUser = givenUpdatedUser();
        updatedUser.setId(user.getId());
        //When
        updatedUser = userRepository.save(updatedUser);
        //Then
        User actual = testEntityManager.find(User.class, user.getId());
        assertEquals(actual, updatedUser);

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

    private User givenUpdatedUser() {
        User user = new User();
        user.setId(ID);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setFirstName(UPDATEDFIRSTNAME);
        user.setLastName(LASTNAME);
        user.setPhone(PHONE);
        user.setRole(ROLE);
        return user;
    }

}