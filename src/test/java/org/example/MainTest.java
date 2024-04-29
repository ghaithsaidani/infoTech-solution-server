package org.example;

import org.example.controllers.AVAController;
import org.example.controllers.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MainTest {

    @Autowired
    private UserController userController;

    @Autowired
    private AVAController avaController;

    @Test
    void UserControllerIsNotNull() throws Exception {
        assertNotNull(userController);
    }

    @Test
    void AVAControllerIsNotNull() throws Exception {
        assertNotNull(avaController);
    }

}