package com.sztorma.skeleton.security.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Succesful authentication")
    public void testAuthenticateSuccess() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(
                "/api/authenticate") //
                .contentType(MediaType.APPLICATION_FORM_URLENCODED) //
                .param("username", "Admin") //
                .param("password", "admin") //
        )
                .andExpect(MockMvcResultMatchers.status().isOk());
        // TODO token validation
    }

    @Test
    @DisplayName("No such user authentication attempt")
    public void testNoSuchUserAuthentication() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(
                "/api/authenticate") //
                .contentType(MediaType.APPLICATION_FORM_URLENCODED) //
                .param("username", "invalidUsername") //
                .param("password", "admin") //
        )
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @DisplayName("Invalid password authentication attempt")
    public void testInvalidPasswordAuthentication() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(
                "/api/authenticate") //
                .contentType(MediaType.APPLICATION_FORM_URLENCODED) //
                .param("username", "Admin") //
                .param("password", "invalidPassword") //
        )
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
