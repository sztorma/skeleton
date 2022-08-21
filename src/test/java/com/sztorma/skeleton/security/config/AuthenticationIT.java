package com.sztorma.skeleton.security.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.sztorma.skeleton.BaseIT;

public class AuthenticationIT extends BaseIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Succesful authentication")
    public void testAuthenticateSuccess() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(
                "/api/authenticate") //
                .contentType(MediaType.APPLICATION_FORM_URLENCODED) //
                .param("username", "Admin") //
                .param("password", "admin") //
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        JSONObject content = new JSONObject(result.getResponse().getContentAsString());
        assertEquals("Admin", getJwtUsername(content.getString("access_token")));
        assertEquals("Admin", getJwtUsername(content.getString("refresh_token")));
    }

    private String getJwtUsername(String token) {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        return username;

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
