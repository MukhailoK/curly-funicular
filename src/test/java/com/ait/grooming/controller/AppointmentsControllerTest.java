package com.ait.grooming.controller;

import com.ait.grooming.TestHelper;
import com.ait.grooming.service.AppointmentService;
import com.ait.grooming.utils.request.AppointmentRequest;
import com.ait.grooming.utils.request.auth.AuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@Data
@Sql(scripts = {"/sql/schema_hbt.sql", "/sql/data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("Endpoint /api/v1/appointments is works:")
public class AppointmentsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private TestHelper helper;

    private String token;

    MvcResult mvcResult1;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest("client2@example.com", "password1");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(helper.asJsonString(request)))
                .andReturn();
        token = helper.getToken(mvcResult.getResponse().getContentAsString());

    }

    @Test
    void testGetAppointmentbyID() throws Exception {
        LocalDateTime start = LocalDateTime.of(2024, 2, 01, 12, 0, 0);
        LocalDateTime end = LocalDateTime.of(2024, 2, 28, 12, 0, 0);
        AppointmentRequest request1 = new AppointmentRequest(1, start);
        mvcResult1 = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(helper.asJsonString(request1)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    void testPositiveGetAllAppointments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/appointments")
                        .header("Authorization", "Bearer " + token)
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
    void testGetAppointmentById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/appointments/{id}", 1)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAppointmentsByUserEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/appointments/user")
                        //"Bearer YOUR_ACCESS_TOKEN"
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteAppointment() throws Exception {
        LocalDateTime start = LocalDateTime.of(2024, 2, 01, 12, 0, 0);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new AppointmentRequest(1, start)))
                        .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }
}
