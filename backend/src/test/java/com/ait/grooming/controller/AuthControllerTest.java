package com.ait.grooming.controller;

import com.ait.grooming.dto.pet.PetDto;
import com.ait.grooming.dto.user.UserDto;
import com.ait.grooming.model.Role;
import com.ait.grooming.model.User;
import com.ait.grooming.repository.BreedRepository;
import com.ait.grooming.repository.UserRepository;
import com.ait.grooming.service.UserService;
import com.ait.grooming.service.auth.JwtTokenProvider;
import com.ait.grooming.service.exceptions.IsAlreadyExistException;
import com.ait.grooming.utils.request.auth.RegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.security.Principal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "com.ait.grooming")
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    UserController userController;

    @Test
    void testRegister_Success() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest(
                "John", "Doe",
                "123456789","john.doe@example.com", "password123",
                null);
        PetDto petDto = new PetDto("Lucky", registerRequest.getEmail(), breedRepository.findById(1).get().getName(), "can bite");
        registerRequest.setPet(List.of(petDto));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(registerRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String token = mvcResult.getResponse().getContentAsString()
                .replaceAll("\"", "")
                .replaceFirst("access_token:", "")
                .replaceAll("\\{", "")
                .replaceAll("\\}", "");


        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
        assertTrue(tokenProvider.validateToken(token));
        assertEquals(registerRequest.getEmail(), tokenProvider.getUserEmailFromJWT(token));

        Principal principal = () -> tokenProvider.getUserEmailFromJWT(token);

        ResponseEntity<UserDto> userResponse = userController.getUserInfo(principal);
        assertEquals(HttpStatus.OK, userResponse.getStatusCode());

        UserDto user = userResponse.getBody();

        assertEquals("John", user.getName());

        assertEquals("Doe", user.getLastName());

        assertEquals("john.doe@example.com", user.getEmail());

        assertEquals("123456789", user.getPhone());


        userService.delete(principal);
    }

    @Test
    void testRegister_UserAlreadyExists() throws Exception {

        RegisterRequest registerRequest = new RegisterRequest(
                "John", "Doe",
                "123456789","john.doe@example.com", "password123",
                null);

        User existingUser = new User();
        existingUser.setEmail("john.doe@example.com");
        existingUser.setPassword("encodedPassword");
        existingUser.setName("test");
        existingUser.setRole(Role.CLIENT);
        userRepository.save(existingUser);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(registerRequest)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andReturn();

        assertEquals(HttpStatus.CONFLICT.value(), mvcResult.getResponse().getStatus());

        assertThrows(IsAlreadyExistException.class, () -> userService.register(registerRequest));
        String token = tokenProvider.createToken(existingUser.getEmail())
                .replaceAll("\"", "")
                .replaceFirst("access_token:", "")
                .replaceAll("\\{", "")
                .replaceAll("\\}", "");

        Principal principal = () -> tokenProvider.getUserEmailFromJWT(token);
        userService.delete(principal);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
