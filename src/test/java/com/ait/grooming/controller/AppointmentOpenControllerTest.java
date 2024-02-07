package com.ait.grooming.controller;

import com.ait.grooming.TestHelper;
import com.ait.grooming.utils.request.NewUserAppointmentRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/sql/schema_hbt.sql", "/sql/data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("Endpoint /openapi/v1/appointments is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class AppointmentOpenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestHelper helper;

    @Nested
    @DisplayName("POST /openapi/v1/appointments:")
    public class Create {
        @Test
        void return_201_create_new_appointment() throws Exception {
            LocalDateTime start = LocalDateTime.of(2025, 2, 20, 12, 0, 0);

            NewUserAppointmentRequest request = new NewUserAppointmentRequest(
                    "test", "test", "test@mail.com", "1111", "testDog", "Golden", "can bite");
            request.setGroomingId(1);
            request.setDateTimeStart(start);
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/openapi/v1/appointments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(helper.asJsonString(request)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                                "dateTimeStart":[2025,2,20,12,0],
                                "groomingDto":{
                                    "name":"SmallCare: XS",
                                    "size":"up to 2.5 kg (small breed)",
                                    "description":"Dogs up to 2.5 kg with short coat, such as pugs.",
                                    "price":69.0,
                                    "durationProcedure":[1,30],"id":1},
                                    "userDto":{
                                        "name":"test",
                                        "lastName":"test",
                                        "userName":null,
                                        "email":"test@mail.com",
                                        "phone":"1111",
                                        "registrationDate":[2024,2,7],
                                        "role":"GUEST",
                                            "pets":[
                                                {
                                                "name":"testDog",
                                                "ownerEmail":"test@mail.com",
                                                "breed":"Golden",
                                                "specialNotes":"can bite"
                                                }]
                                        },
                                        "petDto":{
                                            "name":"testDog",
                                            "ownerEmail":"test@mail.com",
                                            "breed":"Golden",
                                            "specialNotes":"can bite"
                                            },
                                    "status":"scheduled"
                            }
                            """))
                    .andReturn();
        }

        @ParameterizedTest
        @CsvSource({
                ", name is require",
                "null, name is require",

        })
        void return_400_null_name(String name, String expectedErrorMessage) throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/openapi/v1/appointments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "groomingId": 1,
                                      "dateTimeStart": "2024-02-05T10:03:18.874Z",
                                      "name": %s,
                                      "lastName": "guestLastName",
                                      "email": "guest@mail.com",
                                      "phone": "123456789",
                                      "nameDog": "Pufi",
                                      "breed": "Golden",
                                      "specialNotes": "can bite"
                                    }
                                    """.formatted(name)))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                            "name": "%s"
                            }
                            """.formatted(expectedErrorMessage)))
                    .andReturn();
        }

        @ParameterizedTest
        @CsvSource({
                ",Dog name is require",
                "null,Dog name is require",

        })
        void return_400_null_dog_name(String name, String expectedErrorMessage) throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/openapi/v1/appointments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "groomingId": 1,
                                      "dateTimeStart": "2024-02-05T10:03:18.874Z",
                                      "name": "testName",
                                      "lastName": "guestLastName",
                                      "email": "guest@mail.com",
                                      "phone": "123456789",
                                      "nameDog": %s,
                                      "breed": "Golden",
                                      "specialNotes": "can bite"
                                    }
                                    """.formatted(name)))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                            "nameDog":"%s"
                            }
                            """.formatted(expectedErrorMessage)))
                    .andReturn();
        }

        @ParameterizedTest
        @CsvSource({
                ",breed is require",
                "null,breed is require",

        })
        void return_400_null_breed(String breed, String expectedErrorMessage) throws Exception {

            mockMvc.perform(MockMvcRequestBuilders
                            .post("/openapi/v1/appointments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "groomingId": 1,
                                      "dateTimeStart": "2024-02-05T10:03:18.874Z",
                                      "name": "test",
                                      "lastName": "guestLastName",
                                      "email": "gues2t@mail.com",
                                      "phone": "123456789",
                                      "nameDog": "Pufi",
                                      "breed": %s,
                                      "specialNotes": "can bite"
                                    }
                                    """.formatted(breed)))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                            "breed":"%s"
                            }""".formatted(expectedErrorMessage)))
                    .andReturn();
        }

        @ParameterizedTest
        @CsvSource({
                ",groomingId can't be null",

        })
        void return_400_null_grooming_id(Integer id, String expectedErrorMessage) throws Exception {
            LocalDateTime start = LocalDateTime.of(2024, 2, 20, 12, 0, 0);

            NewUserAppointmentRequest request = new NewUserAppointmentRequest(
                    "test", "test", "test@mail.com", "1111", "testDog", "Golden", "can bite");
            request.setGroomingId(id);
            request.setDateTimeStart(start);
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/openapi/v1/appointments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(helper.asJsonString(request)))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                             "groomingId":"%s"
                            }
                            """.formatted(expectedErrorMessage)))
                    .andReturn();
        }

        @ParameterizedTest
        @CsvSource({
                "-1,grooming not found",
                "0,grooming not found",
                "9999,grooming not found",

        })
        void return_404_grooming_not_found_by_id(Integer id, String expectedErrorMessage) throws Exception {
            LocalDateTime start = LocalDateTime.of(2024, 2, 20, 12, 0, 0);

            NewUserAppointmentRequest request = new NewUserAppointmentRequest(
                    "test", "test", "test@mail.com", "1111", "testDog", "Golden", "can bite");
            request.setGroomingId(id);
            request.setDateTimeStart(start);
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/openapi/v1/appointments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(helper.asJsonString(request)))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                             "message":"%s"
                            }
                            """.formatted(expectedErrorMessage)))
                    .andReturn();
        }

        @ParameterizedTest
        @CsvSource({
                ",dateTimeStart can't be null",
                "null,dateTimeStart can't be null",
        })
        void return_400_null_date_time(String dateTime, String expectedErrorMessage) throws Exception {

            mockMvc.perform(MockMvcRequestBuilders
                            .post("/openapi/v1/appointments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "groomingId": 1,
                                      "dateTimeStart": %s,
                                      "name": "test",
                                      "lastName": "guestLastName",
                                      "email": "guest@mail.com",
                                      "phone": "123456789",
                                      "nameDog": "Pufi",
                                      "breed": "Golden",
                                      "specialNotes": "can bite"
                                    }
                                    """.formatted(dateTime)))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                             "dateTimeStart":"%s"
                            }
                            """.formatted(expectedErrorMessage)))
                    .andReturn();
        }

        @Test
        void return_409_conflict_date_time() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders
                            .post("/openapi/v1/appointments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "groomingId": 1,
                                      "dateTimeStart": "2025-02-02T10:00:00",
                                      "name": "test",
                                      "lastName": "guestLastName",
                                      "email": "guest@mail.com",
                                      "phone": "123456789",
                                      "nameDog": "Pufi",
                                      "breed": "Golden",
                                      "specialNotes": "can bite"
                                    }
                                    """))
                    .andExpect(MockMvcResultMatchers.status().isConflict())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                             "message":"Appointment for the time: 2025-02-02T10:00 already exists"
                            }
                            """))
                    .andReturn();
        }
    }
}
