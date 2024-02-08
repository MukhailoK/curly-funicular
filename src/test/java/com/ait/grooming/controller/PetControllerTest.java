package com.ait.grooming.controller;

import com.ait.grooming.TestHelper;
import com.ait.grooming.dto.pet.PetDto;
import com.ait.grooming.utils.request.PetRequest;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/sql/schema_hbt.sql", "/sql/data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("Endpoint /api/pets is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestHelper helper;

    @Test
    @WithUserDetails("client2@example.com")
    public void testCreatePet_Success() throws Exception {
        PetRequest request = new PetRequest(
                "TestName", 1, "testNotes");
        PetDto petDto = new PetDto(
                "TestName", "client2@example.com",
                "Golden", "testNotes");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(helper.asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(helper.asJsonString(petDto)));
    }

    @Test
    public void testCreatePet_Forbidden() throws Exception {
        PetRequest request = new PetRequest(
                "TestName", 2, "testNotes");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(helper.asJsonString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testGetBreeds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/pets/breeds")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                                
                        [
                          {
                            "id": 1,
                            "name": "Golden"
                          }
                        ]
                        """));
    }

    @Test
    @WithUserDetails("client2@example.com")
    public void testGetPetByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/pets/Joschy")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "name": "Joschy",
                          "ownerEmail": "client2@example.com",
                          "breed": "Golden",
                          "specialNotes": "Enjoys long walks"
                        }
                        """));
    }
}