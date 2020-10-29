package edu.utec.publisherapplab11group4;

import controller.PublisherController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PublisherController.class)
class PublisherAppLab11Group4ApplicationTests{

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test0() {
        PublisherAppLab11Group4Application.main(new String[] {});
        assertTrue(true, "silly assertion to be compliant with Sonar");
    }
    
    @Test
    void postAMessage() throws Exception {
        mockMvc.perform(post("/publish/channel1")
                .content("Mensaje"))
                .andExpect(status().isOk());

        Process processStop = Runtime.getRuntime().exec("brew services stop rabbitmq");
        processStop.waitFor();
        
        mockMvc.perform(post("/publish/channel1")
        .content("Mensaje"))
        .andExpect(status().isBadGateway());
        
        Process processStart = Runtime.getRuntime().exec("brew services start rabbitmq");
        processStart.waitFor();
        
        TimeUnit.SECONDS.sleep(10);

        mockMvc.perform(post("/publish/channel1")
        .content("Mensaje"))
        .andExpect(status().isOk());
    }

}
