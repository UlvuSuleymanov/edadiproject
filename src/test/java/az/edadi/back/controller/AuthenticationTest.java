package az.edadi.back.controller;

import az.edadi.back.model.request.SignUpRequestModel;
import az.edadi.back.utility.factory.PatternFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mifmif.common.regex.Generex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AuthenticationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addUser() throws Exception {
        Generex generex = new Generex(PatternFactory.getUsernamePattern().pattern());
        SignUpRequestModel signUpRequestModel = new SignUpRequestModel();
        signUpRequestModel.setUsername(generex.random());
        signUpRequestModel.setPassword("sdfsdfsd");
        signUpRequestModel.setEmail("adsas@ads");
        signUpRequestModel.setName("adsasads");
        mockMvc.perform(
                post("/api/auth/signup").content(objectMapper.writeValueAsString(signUpRequestModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        mockMvc.perform(
                post("/api/auth/signup").content(objectMapper.writeValueAsString(signUpRequestModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity()).andReturn();

        signUpRequestModel.setUsername(generex.random());
        signUpRequestModel.setEmail("example@gmail.com");
        signUpRequestModel.setPassword("");
        mockMvc.perform(
                post("/api/auth/signup").content(objectMapper.writeValueAsString(signUpRequestModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity()).andReturn();


        signUpRequestModel.setPassword("password");
        mockMvc.perform(
                post("/api/auth/signup").content(objectMapper.writeValueAsString(signUpRequestModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

    }


}
