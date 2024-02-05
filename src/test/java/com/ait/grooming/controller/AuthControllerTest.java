package com.ait.grooming.controller;

import com.ait.grooming.TestHelper;
import com.ait.grooming.utils.request.auth.AuthenticationRequest;
import com.ait.grooming.utils.request.auth.RegisterRequest;
import lombok.Data;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@Data
@Sql(scripts = {"/sql/schema_hbt.sql", "/sql/data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("Endpoint /api/v1/auth is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestHelper helper;


    @Nested
    @DisplayName("POST /api/v1/auth/login:")
    public class Login {
        @Test
        void return_200_login() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/api/v1/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                     "email": "client1@example.com",
                                     "password": "password1"
                                    }
                                    """))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();

        }

        @Test
        void return_401_login_wrong_email() throws Exception {
            AuthenticationRequest request = new AuthenticationRequest(
                    "client1@example.com", "password2"
            );
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/api/v1/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(helper.asJsonString(request)))
                    .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                    .andExpect(MockMvcResultMatchers.content().json(
                            """
                                    {
                                        "message": "Email or Password is wrong"
                                    }
                                    """
                    ));
        }


        @Test
        void return_401_login_wrong_password() throws Exception {
            AuthenticationRequest request = new
                    AuthenticationRequest(
                    "clients1@example.com", "password12"
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
                                            }
                                            """
                            ));
        }
    }

    @Nested
    @DisplayName("POST /api/v1/auth/register:")
    public class Register {
        @Test
        void return_201_register_success() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/api/v1/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "name": "test",
                                      "lastName": "test",
                                      "phone": "015217185893",
                                      "email": "test1@example.com",
                                      "password": "password1"
                                    }
                                    """))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn();

        }

        @Test
        void return_201_register_with_pet() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/api/v1/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "name": "test",
                                      "lastName": "test",
                                      "phone": "015217185893",
                                      "email": "test2@example.com",
                                      "password": "password1",
                                      "pet": [
                                        {
                                          "name": "string",
                                          "breed": "string",
                                          "specialNotes": "string"
                                        }
                                      ]
                                    }
                                    """))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn();

        }

        @Test
        void return_409_register_exist_user() throws Exception {

            RegisterRequest registerRequest = new RegisterRequest(
                    "John", "Doe",
                    "123456789", "client1@example.com", "password123",
                    null);

            mockMvc.perform(MockMvcRequestBuilders
                            .post("/api/v1/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(helper.asJsonString(registerRequest)))
                    .andExpect(MockMvcResultMatchers.status().isConflict())
                    .andReturn();
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