package org.example.controllers;

import org.example.controllers.helper.JsonHelper;
import org.example.dto.UserDto;
import org.example.dto.UsersDto;
import org.example.mapper.UserMapper;
import org.example.models.User;
import org.example.request.UserRequest;
import org.example.services.UserService;
import org.junit.Test;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerMVCTest {

    private static final long ID= 1L;
    private static final String EMAIL= "contact@infoTech.solutions.com";
    private static final String PASSWORD= "root";
    private static final String FIRSTNAME= "InfoTech";
    private static final String LASTNAME= "Solutions";
    private static final long PHONE= 12345678;
    private static final String ROLE= "Agent";

    private static final String USER_URL = "/api/users";
    private static final String USER_ID_URL = "/api/users/{id}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Test
    public void should_save_user_returns_200() throws Exception{
        UserRequest createRequest = givenUserRequest();
        User user = givenUser();
        UserDto userDto = givenUserDto();
        when(userMapper.map(createRequest)).thenReturn(user);
        when(userService.save(user)).thenReturn(user);
        when(userMapper.map(user)).thenReturn(userDto);
        String createRequestJson = JsonHelper.toJson(createRequest).orElse("");
        String expected = JsonHelper.toJson(userDto).orElse("");
        //When
        mockMvc.perform(post(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createRequestJson))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_find_all_returns_200() throws Exception{
        User user = givenUser();
        List<User> users = Collections.singletonList(user);
        when(userService.findAll()).thenReturn(users);
        UserDto userDto = givenUserDto();
        List<UserDto> userDtos = Collections.singletonList(userDto);
        when(userMapper.map(users)).thenReturn(userDtos);
        UsersDto usersDto = new UsersDto(userDtos);
        String expected = JsonHelper.toJson(usersDto).orElse("");
        mockMvc.perform(get(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));

    }

    @Test
    public void should_find_user_by_id_returns_200() throws Exception{
        User user = givenUser();
        when(userService.findById(ID)).thenReturn(user);
        UserDto userDto = givenUserDto();
        when(userMapper.map(user)).thenReturn(userDto);
        String expected = JsonHelper.toJson(userDto).orElse("");
        mockMvc.perform(get(USER_ID_URL, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_update_user_returns_200() throws Exception{
        User user = givenUser();
        UserRequest userRequest = givenUserRequest();
        UserDto userDto = givenUserDto();
        when(userMapper.map(ID, userRequest)).thenReturn(user);
        when(userService.update(user)).thenReturn(user);
        when(userMapper.map(user)).thenReturn(userDto);
        String userRequestJson = JsonHelper.toJson(userRequest).orElse("");
        String expected = JsonHelper.toJson(userDto).orElse("");
        //When
        mockMvc.perform(put(USER_ID_URL, ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRequestJson))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_delete_user_returns_200() throws Exception{
        User user = givenUser();
        when(userService.deleteById(ID)).thenReturn(user);
        UserDto userDto = givenUserDto();
        when(userMapper.map(user)).thenReturn(userDto);
        String expected = JsonHelper.toJson(userDto).orElse("");

        mockMvc.perform(delete(USER_ID_URL, ID)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(content().json(expected));
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

    private UserDto givenUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(ID);
        userDto.setEmail(EMAIL);
        userDto.setFirstName(FIRSTNAME);
        userDto.setLastName(LASTNAME);
        userDto.setPassword(PASSWORD);
        userDto.setPhone(PHONE);
        userDto.setRole(ROLE);
        return userDto;
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