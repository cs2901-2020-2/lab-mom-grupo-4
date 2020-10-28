package edu.utec.publisherapplab11group4;

import controller.PublisherController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.match.ContentRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PublisherController.class)
class PublisherAppLab11Group4ApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void postAMessage() throws Exception {
        mockMvc.perform(post("/publish/channel1")
                .content("Mensaje"))
                .andExpect(status().isOk());
    }

}
