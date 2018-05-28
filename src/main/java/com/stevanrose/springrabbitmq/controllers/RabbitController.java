package com.stevanrose.springrabbitmq.controllers;

import com.stevanrose.springrabbitmq.configuration.RabbitConfig;
import com.stevanrose.springrabbitmq.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    private static final Logger logger = LoggerFactory.getLogger(RabbitController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping(value = "/send")
    @ResponseStatus(HttpStatus.OK)
    public void send(@RequestBody Message message) {
        logger.info("Sending message {} to Rabbit queue", message);
        rabbitTemplate.convertAndSend(RabbitConfig.topicExchangeName, "steve-routing", message.toString());
    }

    private void handleMessage(String message) {
        logger.info("Received message {} from Rabbit Queue", message);
    }

}
