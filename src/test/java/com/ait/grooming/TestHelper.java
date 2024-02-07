package com.ait.grooming;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@Component
@ActiveProfiles("test")
public class TestHelper {
    @Autowired
    private ObjectMapper objectMapper;

    public String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getToken(String tokenProvider) {
        return tokenProvider
                .replaceAll("\"", "")
                .replaceFirst("access_token:", "")
                .replaceAll("\\{", "")
                .replaceAll("\\}", "");

    }
}
