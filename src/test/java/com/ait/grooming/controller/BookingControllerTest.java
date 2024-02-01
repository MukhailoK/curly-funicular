package com.ait.grooming.controller;

import lombok.Data;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@AutoConfigureMockMvc
@Data
@Sql(scripts = {"/sql/schema_hbt.sql", "/sql/data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
public class BookingControllerTest {


}
