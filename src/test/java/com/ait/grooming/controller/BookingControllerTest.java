package com.ait.grooming.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/sql/schema_hbt.sql", "/sql/data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class BookingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Nested
    @DisplayName("GET /api/booking:")

    public class GetAllDates {
        @Test
        void return_200_get_available_time_slot_for_guest() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/booking"))
                    .andExpect(status().isOk())
                    .andExpect(content().json("""
                            [
                             {
                                "date":"2025-02-02",
                                "timeSlots":{
                                    "10":1,
                                    "12":2,
                                    "14":null,
                                    "16":null},
                                "free":true
                             }
                            ]
                            """));
        }
        @Test
        @WithUserDetails("client2@example.com")
        void return_200_get_available_time_slot_for_client() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/booking"))
                    .andExpect(status().isOk())
                    .andExpect(content().json("""
                            [
                             {
                                "date":"2025-02-02",
                                "timeSlots":{
                                    "10":1,
                                    "12":2,
                                    "14":null,
                                    "16":null},
                                "free":true
                             }
                            ]
                            """));
        }
    }
}
