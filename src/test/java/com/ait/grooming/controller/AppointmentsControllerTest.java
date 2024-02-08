package com.ait.grooming.controller;

import com.ait.grooming.TestHelper;
import com.ait.grooming.utils.request.AppointmentRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
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

    @Nested
    @DisplayName("POST /api/v1/appointments:")
    public class CreateAppointment {
        @Test
        @WithUserDetails("client1@example.com")
        void return_200_create_appointment_for_client() throws Exception {
            LocalDateTime start = LocalDateTime.of(2024, 2, 1, 12, 0, 0);
            AppointmentRequest request1 = new AppointmentRequest(1, start);
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/api/v1/appointments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(helper.asJsonString(request1)))
                    .andExpect(status().isCreated())
                    .andReturn();
        }

        @Test
        void return_403_create_appointment_for_client() throws Exception {
            LocalDateTime start = LocalDateTime.of(2024, 2, 1, 12, 0, 0);
            AppointmentRequest request1 = new AppointmentRequest(1, start);
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/api/v1/appointments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(helper.asJsonString(request1)))
                    .andExpect(status().isForbidden())
                    .andReturn();
        }

        @ParameterizedTest
        @CsvSource({
                "0",
                "-1",

        })
        @WithUserDetails("client1@example.com")
        void return_400_create_appointment_for_client(Integer id) throws Exception {
            LocalDateTime start = LocalDateTime.of(2024, 2, 1, 12, 0, 0);
            AppointmentRequest request1 = new AppointmentRequest(id, start);
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/api/v1/appointments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(helper.asJsonString(request1)))
                    .andExpect(status().isBadRequest())
                    .andReturn();
        }

        @Test
        @WithUserDetails("client1@example.com")
        void return_400_create_appointment_for_client_() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/api/v1/appointments")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();
        }

    }

    @Nested
    @DisplayName("DELETE /api/v1/appointments/{id}:")
    public class DeleteAppointment {
        @Test
        @WithUserDetails("client1@example.com")
        void return_200_delete_appointment_by_id() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .delete("/api/v1/appointments/2")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();
        }

        @Test
        @WithUserDetails("client1@example.com")
        void return_404_delete_appointment_by_id() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .delete("/api/v1/appointments/-1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andReturn();
        }

        @Test
        @WithUserDetails("client1@example.com")
        void return_400_delete_appointment_by_id() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .delete("/api/v1/appointments")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();
        }

        @Test
        void return_403_delete_appointment_by_id() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .delete("/api/v1/appointments/2")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isForbidden())
                    .andReturn();
        }
    }

    @Nested
    @DisplayName("GET /api/v1/appointments:")
    public class GetAppointmentById {
        @Test
        @WithUserDetails("client1@example.com")
        void return_200_getAppointment_by_id() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/v1/appointments/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

        @Test
        void return_403_getAppointment_by_id() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/v1/appointments")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isForbidden());
        }
    }

    @Nested
    @DisplayName("GET /api/v1/appointments/user:")
    public class GetAppointmentByUser {
        @Test
        @WithUserDetails("client1@example.com")
        void return_200_get_all_appointments_for_user() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/v1/appointments/user")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

        @Test
        void return_403_get_all_appointments_for_user() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/v1/appointments/user")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isForbidden());
        }
    }
}
