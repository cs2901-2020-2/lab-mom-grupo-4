package controller;

import org.springframework.web.bind.annotation.*;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/publisher")
@CrossOrigin(origins = "*")
public class PublisherController {
    private final static String QUEUE_NAME = "hello";

    @PostMapping
    public String sendMessage(@RequestBody String message){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }

        StringBuilder response = new StringBuilder();
        response.append("El mensaje \"").append(message).append("\" se envió correctamente.");
        return response.toString();
    }
}
