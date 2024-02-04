package com.ait.grooming.controller;

import com.ait.grooming.TestHelper;
import com.ait.grooming.utils.request.NewUserAppointmentRequest;
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

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@Data
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
            LocalDateTime start = LocalDateTime.of(2024, 2, 20, 12, 0, 0);

            NewUserAppointmentRequest request = new NewUserAppointmentRequest(
                    "test", "test", "test@mail.com", "1111", "testDog", "Golden", "can bite");
            request.setGroomingId(1);
            request.setDateTimeStart(start);
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/openapi/v1/appointments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(helper.asJsonString(request)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn();
        }
    }

}
