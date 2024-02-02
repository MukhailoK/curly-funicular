package com.ait.grooming.controller;

import com.ait.grooming.TestHelper;
import com.ait.grooming.dto.pet.PetRequest;
import com.ait.grooming.dto.user.UserDto;
import com.ait.grooming.model.Role;
import com.ait.grooming.model.User;
import com.ait.grooming.repository.BreedRepository;
import com.ait.grooming.repository.UserRepository;
import com.ait.grooming.service.UserService;
import com.ait.grooming.service.auth.JwtTokenProvider;
import com.ait.grooming.service.exceptions.IsAlreadyExistException;
import com.ait.grooming.utils.request.auth.AuthenticationRequest;
import com.ait.grooming.utils.request.auth.RegisterRequest;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.security.Principal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Data
@Sql(scripts = {"/sql/schema_hbt.sql", "/sql/data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("Endpoint /api/v1/auth is works:")
public class AuthControllerTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private BreedRepository breedRepository;
    @Autowired
    private UserController userController;
    @Autowired
    private TestHelper helper;


    @Nested
    @DisplayName("POST /api/v1/auth/login:")
    public class Login {
        @Test
        void return_200_login() throws Exception {
            AuthenticationRequest request = new AuthenticationRequest(
                    "client3@example.com", "password1"
            );

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                            .post("/api/v1/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(helper.asJsonString(request)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
            String token = helper.getToken(mvcResult.getResponse().getContentAsString());

            assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
            assertTrue(tokenProvider.validateToken(token));
            assertEquals(request.getEmail(), tokenProvider.getUserEmailFromJWT(token));
        }

        @Test
        void return_401_login_wrong_email() throws Exception {
            AuthenticationRequest request = new AuthenticationRequest(
                    "client3@example.com", "password2"
            );
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/api/v1/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(helper.asJsonString(request)))
                    .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                    .andExpect(MockMvcResultMatchers.content().json("""
                                
                                    {
                                "message": "Email or Password is wrong"
                                  

                            }"""))
            ;

        }


        @Test
        void return_401_login_wrong_password() throws Exception {
            AuthenticationRequest request = new
                    AuthenticationRequest(
                    "clients3@example.com", "password1"
            );
            mockMvc.perform(
                            MockMvcRequestBuilders
                                    .post("/api/v1/auth/login")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(helper.asJsonString(request)))
                            .
                    andExpect(MockMvcResultMatchers.status().isUnauthorized())
                    .andExpect(
                            MockMvcResultMatchers.content().json(
                                    """
                                            {
                                            "message": "Email or Password is wrong"
                                            }"""));

        }
    }

    @Nested
    @DisplayName("POST /api/v1/auth/register:")
    public class Register {
        @Test
        void return_201_register_success() throws Exception {
            RegisterRequest registerRequest = new RegisterRequest(
                    "John", "Doe",
                    "123456789", "john.doe@example.com", "password123",
                    null);

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                            .post("/api/v1/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(helper.asJsonString(registerRequest)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn();

            String token = helper.getToken(mvcResult.getResponse().getContentAsString());


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
        void return_201_register_with_pet() throws Exception {
            RegisterRequest registerRequest = new RegisterRequest(
                    "John", "Doe",
                    "123456789", "john.doe@example.com", "password123",
                    null);
            PetRequest petRequests = new PetRequest("Lucky", breedRepository.findById(1).get().getName(), "can bite");
            registerRequest.setPet(List.of(petRequests));

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                            .post("/api/v1/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(helper.asJsonString(registerRequest)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn();

            String token = helper.getToken(mvcResult.getResponse().getContentAsString());


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
        void return_409_register_exist_user() throws Exception {

            RegisterRequest registerRequest = new RegisterRequest(
                    "John", "Doe",
                    "123456789", "john.doe@example.com", "password123",
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
                            .content(helper.asJsonString(registerRequest)))
                    .andExpect(MockMvcResultMatchers.status().isConflict())
                    .andReturn();

            assertEquals(HttpStatus.CONFLICT.value(), mvcResult.getResponse().getStatus());

            assertThrows(IsAlreadyExistException.class, () -> userService.register(registerRequest));
            String token = helper.getToken(tokenProvider.createToken(existingUser.getEmail()));

            Principal principal = () -> tokenProvider.getUserEmailFromJWT(token);
            userService.delete(principal);
        }

        @Test
        void return_400_register_wrong_email() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                     {
                                     "email": "invalid_email",
                                     "name": "name",
                                     "password":"Password123!"
                                     }
                                    """))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                             "email": "Invalid email format"
                            }""")
                    );

        }

        @Test
        void return_400_register_empty_email() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                     {
                                     "email": "",
                                     "name": "name",
                                     "password":"Password123!"
                                     }
                                    """))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                             "email": "Email is required"
                            }""")
                    );

        }

        @Test
        void return_400_register_empty_name() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                     {\s
                                     "email": "valid@email.com",
                                     "name": "",
                                     "password":"Password123!"
                                     }
                                    """))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                             "name": "Name is required"
                            }""")
                    );
        }

        @Test
        void return_400_register_empty_password() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                     {\s
                                     "email": "valid@email.com",
                                     "name": "name",
                                     "password":""
                                     }
                                    """))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                             "password": "Password is required"
                            }""")
                    );
        }
    }
}