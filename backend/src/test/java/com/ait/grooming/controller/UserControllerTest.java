package com.ait.grooming.controller;

import com.ait.grooming.config.ApplicationConfig;
import com.ait.grooming.dto.user.UserDto;
import com.ait.grooming.repository.BreedRepository;
import com.ait.grooming.repository.UserRepository;
import com.ait.grooming.service.UserService;
import com.ait.grooming.service.auth.JwtTokenProvider;
import com.ait.grooming.utils.request.ChangePasswordRequest;
import com.ait.grooming.utils.request.auth.AuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.ait.grooming.TestHelper.asJsonString;
import static com.ait.grooming.utils.maper.user.UserMapper.toUserDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "com.ait.grooming")
@AutoConfigureMockMvc
@Data
@Import(ApplicationConfig.class)
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
    private UserController userController;

    private String token;

    @BeforeEach
    void init() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest("client1@example.com", "password1");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        token = mvcResult.getResponse().getContentAsString()
                .replaceAll("\"", "")
                .replaceFirst("access_token:", "")
                .replaceAll("\\{", "")
                .replaceAll("\\}", "");
    }

    @Test
    void testChangePassword_Success() throws Exception {

        ChangePasswordRequest request = new ChangePasswordRequest(
                "password1", "password2",
                "password2"
        );

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        String token = mvcResult.andReturn().getResponse().getContentAsString()
                .replaceAll("\"", "")
                .replaceFirst("access_token:", "")
                .replaceAll("\\{", "")
                .replaceAll("\\}", "");
        assertTrue(tokenProvider.validateToken(token));
    }

    @Test
    void testGetUserInfo_Success() throws Exception {
        // Предполагаемый объект пользователя
        UserDto userDto = toUserDto(userRepository.findByEmail("client1@example.com").get());

        // Поддельный ответ сервиса при вызове getUserByPrincipalName
        given(userService.getUserByPrincipalName("client2@example.com")).willReturn(ResponseEntity.ok(userDto));

        // Выполняем запрос и проверяем результат
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/user-info")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        UserDto result = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), UserDto.class);
        assertEquals(userDto, result);
    }
}
