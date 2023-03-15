package net.codejava;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserLogoutTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testRegistration() throws Exception {
        mockMvc.perform(post("/login")
                        .param("email", "tytyty")
                        .param("password", "2ed3221"))
                .andExpect(status().isOk());
    }

    //Проверка выхода с учетной записи
    @Test
    public void shouldReturnAccessGranted() throws Exception {
        this.mockMvc.perform(post("/login").param("email", "Enje@mail.ru").param("password", "13r3r2")).
                andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Добро пожаловать, 123")));
        this.mockMvc.perform(get("/logout")).
                andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Выход из учетной записи")));
    }

    @Test
    public void shouldReturnAccessDenied() throws Exception {
        this.mockMvc.perform(post("/login").param("email", "nncej@mail.ru").param("password", "e3ue8u38")).
                andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Неправильный пароль, 123")));

    }
}
