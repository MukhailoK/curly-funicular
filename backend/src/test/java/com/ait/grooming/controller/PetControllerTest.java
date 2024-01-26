package com.ait.grooming.controller;

import com.ait.grooming.TestHelper;
import com.ait.grooming.controller.auth.AuthenticationController;
import com.ait.grooming.dto.pet.PetDto;
import com.ait.grooming.model.Breed;
import com.ait.grooming.repository.BreedRepository;
import com.ait.grooming.service.PetService;
import com.ait.grooming.utils.request.PetRequest;
import com.ait.grooming.utils.request.auth.AuthenticationRequest;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@Data
public class PetControllerTest {

    @Autowired
    private PetController petController;

    @Autowired
    private PetService petService;
    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private TestHelper helper;

    private String token;

    @BeforeEach
    public void setUp() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(
                "client2@example.com", "password1"
        );
        token = Objects.requireNonNull(authenticationController.authenticationManager(authenticationRequest).getBody())
                .getAccessToken();
    }

    @Test
    public void testCreatePet_Success() throws Exception {
        PetRequest request = new PetRequest(
                "TestName", 2, "testNotes");

        PetDto petDto = new PetDto(
                "TestName", "client2@example.com",
                "Golden", "testNotes");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/pets")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(helper.asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(helper.asJsonString(petDto)));
    }

    @Test
    public void testCreatePet_Forbidden() throws Exception {
        PetRequest request = new PetRequest(
                "TestName", 2, "testNotes");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(helper.asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void testGetBreeds() throws Exception {
        List<Breed> breeds = breedRepository.findAll();
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/pets/breeds")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(helper.asJsonString(breeds)));
    }

    @Test
    public void testGetPetByName() throws Exception {
        Principal principal = () -> "client2@example.com";
        PetDto petDto = petService.findByPetName("Max", principal).getBody();
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/pets/Max")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(helper.asJsonString(petDto)));
    }

}
