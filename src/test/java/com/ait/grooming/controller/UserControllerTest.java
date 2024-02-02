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

import java.util.List;

import static com.ait.grooming.utils.maper.user.UserMapper.allToUserDtos;
import static com.ait.grooming.utils.maper.user.UserMapper.toUserDto;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
@Data
@Sql(scripts = {"/sql/schema_hbt.sql", "/sql/data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("Endpoint /api/v1/users is works:")
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

    @Nested
    @DisplayName("PATCH /api/v1/users:")
    public class ChangePassword {
        @Test
        @Order(1)
        void return_407_for_wrong_password() throws Exception {

            ChangePasswordRequest request = new ChangePasswordRequest(
                    "passwor", "password3",
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
                                          
                                    """
                            )
                    );
        }

        @Test
        @Order(2)
        void return_403_password_not_same(

        ) throws Exception {

            ChangePasswordRequest request = new ChangePasswordRequest(
                    "password1",
                    "password2",
                    "password3"
            );

            mockMvc.perform
                            (MockMvcRequestBuilders
                                    .patch("/api/v1/users")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .header("Authorization", "Bearer " + token)
                                    .content(helper.asJsonString(request)))
                            .
                    andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(
                            MockMvcResultMatchers.content().json(
                                    """
                                            {
                                                  

                                                            "message":"Password are not the same"
                                            }
                                               """)
                    );

        }

        @Test
        @Order(10)
        void return_200_success() throws Exception {
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
                            """));

            Assertions.assertTrue(tokenProvider.validateToken(token));
        }

    }

    @Nested
    @DisplayName("GET /api/v1/users/user-info:")
    public class GetUserInfo {

        @Test
        @Order(3)
        void return_200_for_user_info() throws Exception {
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
        void return_407_for_user_info() throws Exception {

            given(userService.getUserByPrincipalName("")).willReturn(new ResponseEntity<>(HttpStatus.FORBIDDEN));

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/user-info")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
        }
    }

    @Nested
    @DisplayName("DELETE /api/v1/users:")
    public class Delete {
        @Test
        @Order(5)
        void return_403_for_delete_user() throws Exception {


            mockMvc.perform(MockMvcRequestBuilders
                            .delete("/api/v1/users")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
        }

        @Test
        @Order(9)
        void return_200_for_delete_user() throws Exception {
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
    }

    @Nested
    @DisplayName("GET /api/v1/users/all:")
    public class GetAllUsers {

        @Test
        @Order(6)
        void return_200_for_get_all_user() throws Exception {
            List<UserDto> userDtos = allToUserDtos(userRepository.findAll());
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/v1/users/all")
                            .header("Authorization", "Bearer " + token)
                            .contentType(MediaType.APPLICATION_JSON))

                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json(helper.asJsonString(userDtos)));
        }

        @Test
        @Order(11)
        void return_403_for_get_all_user() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/v1/users/all")
                            .contentType(MediaType.APPLICATION_JSON))

                    .andExpect(MockMvcResultMatchers.status().isForbidden());
        }


    }
}