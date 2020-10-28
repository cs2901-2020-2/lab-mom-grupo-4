package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/publish")
@CrossOrigin(origins = "*")
public class PublisherController {

    @PostMapping("/{channelName}")
    public Object sendMessage(@RequestBody String message, @PathVariable String channelName){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(channelName, false, false, false, null);
            channel.basicPublish("", channelName, null, message.getBytes());

        } catch (TimeoutException | IOException e) {
            return new ResponseEntity<>("RabbitMQ communication error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        StringBuilder response = new StringBuilder();
        response.append("El mensaje \"").append(message).append("\" se envió correctamente.");
        return response.toString();
    }
}
