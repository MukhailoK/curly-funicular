package com.ait.grooming.controller;

import com.ait.grooming.TestHelper;
import com.ait.grooming.dto.user.UserDto;
import com.ait.grooming.repository.UserRepository;
import com.ait.grooming.service.UserService;
import com.ait.grooming.service.auth.JwtTokenProvider;
import com.ait.grooming.utils.request.ChangePasswordRequest;
import com.ait.grooming.utils.request.auth.AuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.ait.grooming.utils.maper.user.UserMapper.allToUserDtos;
import static com.ait.grooming.utils.maper.user.UserMapper.toUserDto;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@Data
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @Autowired
    private UserRepository userRepository;
    @Mock
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserController userController;
    @Autowired
    private TestHelper helper;

    private String token;

    @BeforeEach
    void init() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest("client1@example.com", "password1");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(helper.asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        token = helper.getToken(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @Order(1)
    void testChangePassword_Failed_WrongPassword() throws Exception {

        ChangePasswordRequest request = new ChangePasswordRequest(
                "password2", "password3",
                "password3"
        );

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(helper.asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                        "message":"Wrong password"
                        }
                           """)
                );
    }

    @Test
    @Order(2)
    void testChangePassword_Failed_PasswordNotSame() throws Exception {

        ChangePasswordRequest request = new ChangePasswordRequest(
                "password1", "password2",
                "password3"
        );

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(helper.asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                        "message":"Password are not the same"
                        }
                           """)
                );

    }

    @Test
    @Order(3)
    void testGetUserInfo_Success() throws Exception {
        UserDto userDto = toUserDto(userRepository.findByEmail("client1@example.com").get());

        given(userService.getUserByPrincipalName("client2@example.com")).willReturn(ResponseEntity.ok(userDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/user-info")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(helper.asJsonString(userDto)));
    }

    @Test
    @Order(4)
    void testGetUserInfo_Failed() throws Exception {

        given(userService.getUserByPrincipalName("")).willReturn(new ResponseEntity<>(HttpStatus.FORBIDDEN));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/user-info")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @Order(5)
    void testDelete_Filed() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());

    }

    @Test
    @Order(6)
    void testGetAll_Success() throws Exception {
        List<UserDto> userDtos = allToUserDtos(userRepository.findAll());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/users/all")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(helper.asJsonString(userDtos)));
    }

    @Test
    @Order(9)
    void testDelete_Success() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest("client5@example.com", "password1");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(helper.asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        token = helper.getToken(mvcResult.getResponse().getContentAsString());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                        "message":"deleted"
                        }
                        """)
                );
    }

    @Test
    @Order(10)
    void testChangePassword_Success() throws Exception {

        ChangePasswordRequest request = new ChangePasswordRequest(
                "password1", "password2",
                "password2"
        );

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(helper.asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                        "message":"Password changed"
                        }
                        """)
                );
        assertTrue(tokenProvider.validateToken(token));
    }
}