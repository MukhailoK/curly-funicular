package com.ait.grooming.controller;

import lombok.Data;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureMockMvc
@Data
@TestPropertySource(locations = "classpath:application-test.properties")
public class BookingControllerTest {


}
