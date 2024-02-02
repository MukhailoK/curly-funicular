package com.ait.grooming.controller;

import com.ait.grooming.TestHelper;
import com.ait.grooming.utils.request.ChangePasswordRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/sql/schema_hbt.sql", "/sql/data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("Endpoint /api/v1/users is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestHelper helper;

    @Nested
    @DisplayName("PATCH /api/v1/users:")
    @WithUserDetails("client1@example.com")
    public class ChangePassword {
        @Test
        @Order(1)
        void return_401_for_wrong_password() throws Exception {

            ChangePasswordRequest request = new ChangePasswordRequest(
                    "passwor", "password3",
                    "password3"
            );

            mockMvc.perform(MockMvcRequestBuilders
                            .patch("/api/v1/users")
                            .contentType(MediaType.APPLICATION_JSON)
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
        void return_400_password_not_same(

        ) throws Exception {
            mockMvc.perform
                            (MockMvcRequestBuilders
                                    .patch("/api/v1/users")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("""
                                            {
                                              "currentPassword": "password1",
                                              "newPassword": "password72",
                                              "confirmationPassword": "password2"
                                            }
                                            """))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
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
        @WithUserDetails("client2@example.com")
        void return_200_password_changed() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .patch("/api/v1/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "currentPassword": "password1",
                                      "newPassword": "password2",
                                      "confirmationPassword": "password2"
                                    }
                                    """))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                            "message":"Password changed"
                            }
                            """));
        }
    }

    @Nested
    @DisplayName("GET /api/v1/users/user-info:")

    public class GetUserInfo {

        @Test
        @Order(3)
        @WithUserDetails("client1@example.com")
        void return_200_for_user_info() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/user-info")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                              "name": "Name1",
                              "lastName": "LastName1",
                              "userName": "username1",
                              "email": "client1@example.com",
                              "phone": "123456789",
                              "registrationDate": [
                                2023,
                                4,
                                12
                              ],
                              "role": "CLIENT",
                              "pets": [
                                {
                                  "name": "Joy",
                                  "ownerEmail": "client1@example.com",
                                  "breed": "Golden",
                                  "specialNotes": "Likes to play with toys"
                                }
                              ]
                            }
                            """));
        }


        @Test
        @Order(4)
        void return_403_for_user_info() throws Exception {

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
        @WithUserDetails("client1@example.com")
        void return_200_for_delete_user() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .delete("/api/v1/users")
                            .contentType(MediaType.APPLICATION_JSON))
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
        @WithUserDetails("client1@example.com")
        void return_200_for_get_all_user() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/v1/users/all")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("""
                             [
                            {
                               "name": "Name1",
                               "lastName": "LastName1",
                               "userName": "username1",
                               "email": "client1@example.com",
                               "phone": "123456789",
                               "registrationDate": [
                                 2023,
                                 4,
                                 12
                               ],
                               "role": "CLIENT",
                               "pets": [
                                 {
                                   "name": "Joy",
                                   "ownerEmail": "client1@example.com",
                                   "breed": "Golden",
                                   "specialNotes": "Likes to play with toys"
                                 }
                               ]
                             },
                              {
                               "name": "Name2",
                               "lastName": "LastName2",
                               "userName": "username2",
                               "email": "client2@example.com",
                               "phone": "987654321",
                               "registrationDate": [
                                 2023,
                                 4,
                                 10
                               ],
                               "role": "ADMIN",
                               "pets": [
                                 {
                                   "name": "Joschy",
                                   "ownerEmail": "client2@example.com",
                                   "breed": "Golden",
                                   "specialNotes": "Enjoys long walks"
                                 }
                               ]
                             }]
                            """));
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