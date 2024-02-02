package com.ait.grooming.controller;

import com.ait.grooming.TestHelper;
import com.ait.grooming.utils.request.AppointmentRequest;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
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

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@Data
@Sql(scripts = {"/sql/schema_hbt.sql", "/sql/data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("Endpoint /api/v1/appointments is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class AppointmentsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestHelper helper;


    @Test
    @WithUserDetails("client1@example.com")
    void testGetAppointmentByID() throws Exception {
        LocalDateTime start = LocalDateTime.of(2024, 2, 01, 12, 0, 0);
        LocalDateTime end = LocalDateTime.of(2024, 2, 28, 12, 0, 0);
        AppointmentRequest request1 = new AppointmentRequest(1, start);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(helper.asJsonString(request1)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    @WithUserDetails("client1@example.com")
    void testPositiveGetAllAppointments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/appointments")

                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void testNegativeGetAllAppointments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/appointments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithUserDetails("client1@example.com")
    void testGetAppointmentById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/appointments/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithUserDetails("client1@example.com")
    void testGetAppointmentsByUserEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/appointments/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithUserDetails("client1@example.com")
    void testDeleteAppointment() throws Exception {
        LocalDateTime start = LocalDateTime.of(2024, 2, 01, 12, 0, 0);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(helper.asJsonString(new AppointmentRequest(1, start))))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }
}
