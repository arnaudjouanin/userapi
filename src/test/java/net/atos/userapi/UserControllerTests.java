package net.atos.userapi;

import net.atos.userapi.controller.UserController;
import net.atos.userapi.entity.User;
import net.atos.userapi.repository.UserRepository;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    private User user = new User();

    @BeforeEach
    public void setup() {
        objectMapper.registerModule(new JavaTimeModule());
        user.setUsername("Geralt");
        user.setBirthdate(LocalDate.of(1745, 10, 5));
        user.setCountryOfResidence("France");
        user.setPhoneNumber("0654152545");
        user.setGender("male");
    }

    @Test
    public void testRegisterUser() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetUserDetails() throws Exception {
        when(userRepository.findByUsername("Geralt"))
                .thenReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/Geralt"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(user.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(user.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthdate").value(user.getBirthdate().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.countryOfResidence").value(user.getCountryOfResidence()));
    }

}
