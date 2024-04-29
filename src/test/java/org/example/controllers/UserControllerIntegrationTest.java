package org.example.controllers;

import org.example.Main;
import org.example.config.PasswordEncoder;
import org.example.controllers.helper.HttpHelper;
import org.example.dto.UserDto;
import org.example.dto.UsersDto;
import org.example.request.UserRequest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {
    private static final String USER_URL = "http://localhost:%s/api/users";
    private static final String USER_ID_URL = "http://localhost:%s/api/users/%s";

    private static final long ID= 1L;
    private static final String EMAIL= "contact@infoTech.solutions.com";
    private static final String PASSWORD= "root";
    private static final String FIRSTNAME= "InfoTech";
    private static final String LASTNAME= "Solutions";
    private static final long PHONE= 12345678;
    private static final String ROLE= "Agent";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void should_save_user() {
        //Given
        String url = String.format(USER_URL, port);
        UserRequest createRequest = givenUserRequest();
        HttpEntity<UserRequest> request = HttpHelper.getHttpEntity(createRequest);
        //When
        ResponseEntity<UserDto> response = testRestTemplate.exchange(url, HttpMethod.POST, request, UserDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        //System.out.println(response.getBody());
        assertEquals(response.getBody().getId(),ID,0);
        assertEquals(response.getBody().getEmail(), EMAIL);
        assertEquals(response.getBody().getPassword(), PASSWORD);
        assertEquals(response.getBody().getFirstName(), FIRSTNAME);
        assertEquals(response.getBody().getLastName(), LASTNAME);
        assertEquals(response.getBody().getPhone(), PHONE,0);
        assertEquals(response.getBody().getRole(), ROLE);
    }

    @Test
    public void should_find_user_by_id() {
        //Given
        String url = String.format(USER_ID_URL, port, ID);
        HttpEntity<String> request = HttpHelper.getHttpEntity();
        //When
        ResponseEntity<UserDto> response = testRestTemplate.exchange(url, HttpMethod.GET, request, UserDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(),ID,0);
        assertEquals(response.getBody().getEmail(), EMAIL);
        assertEquals(response.getBody().getPassword(), PASSWORD);
        assertEquals(response.getBody().getFirstName(), FIRSTNAME);
        assertEquals(response.getBody().getLastName(), LASTNAME);
        assertEquals(response.getBody().getPhone(), PHONE,0);
        assertEquals(response.getBody().getRole(), ROLE);
    }

    @Test
    public void should_find_all_users() {
        //Given
        String url = String.format(USER_URL, port);
        HttpEntity<String> request = HttpHelper.getHttpEntity();
        //When
        ResponseEntity<UsersDto> response = testRestTemplate.exchange(url, HttpMethod.GET, request, UsersDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        List<UserDto> userDtos = response.getBody().getUserDtos();
        assertEquals(1, userDtos.size());
        assertEquals(ID, userDtos.get(0).getId(),0);
        assertEquals(EMAIL,userDtos.get(0).getEmail());
        assertEquals(PASSWORD,userDtos.get(0).getPassword());
        assertEquals(FIRSTNAME,userDtos.get(0).getFirstName());
        assertEquals(LASTNAME,userDtos.get(0).getLastName());
        assertEquals(PHONE,userDtos.get(0).getPhone(),0);
        assertEquals(ROLE, userDtos.get(0).getRole());
    }

    @Test
    public void should_update_user() {
        //Given
        String url = String.format(USER_ID_URL, port, ID);
        UserRequest userRequest = givenUserRequest();
        HttpEntity<UserRequest> request = HttpHelper.getHttpEntity(userRequest);
        //When
        ResponseEntity<UserDto> response = testRestTemplate.exchange(url, HttpMethod.PUT, request, UserDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(ID,response.getBody().getId(),0);
        assertEquals(EMAIL,response.getBody().getEmail());
        assertEquals(PASSWORD,response.getBody().getPassword());
        assertEquals(FIRSTNAME,response.getBody().getFirstName());
        assertEquals(LASTNAME,response.getBody().getLastName());
        assertEquals(PHONE,response.getBody().getPhone(),0);
        assertEquals(ROLE,response.getBody().getRole());
    }

    @Test
    public void should_delete_user_by_id() {
        //Given
        String url = String.format(USER_ID_URL, port, ID);
        HttpEntity<String> request = HttpHelper.getHttpEntity();
        //When
        ResponseEntity<UserDto> response = testRestTemplate.exchange(url, HttpMethod.DELETE, request, UserDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(ID,response.getBody().getId(),0);
        assertEquals(EMAIL,response.getBody().getEmail());
        assertEquals(PASSWORD,response.getBody().getPassword());
        assertEquals(FIRSTNAME,response.getBody().getFirstName());
        assertEquals(LASTNAME,response.getBody().getLastName());
        assertEquals(PHONE,response.getBody().getPhone(),0);
        assertEquals(ROLE,response.getBody().getRole());
    }

    private UserRequest givenUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(EMAIL);
        userRequest.setPassword(PASSWORD);
        userRequest.setFirstName(FIRSTNAME);
        userRequest.setLastName(LASTNAME);
        userRequest.setPhone(PHONE);
        userRequest.setRole(ROLE);
        return userRequest;
    }


}
