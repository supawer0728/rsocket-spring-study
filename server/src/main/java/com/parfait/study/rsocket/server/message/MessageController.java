package com.parfait.study.rsocket.server.message;

import java.time.Duration;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.parfait.study.rsocket.server.message.domain.Message;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Controller
public class MessageController {

    @MessageMapping("echo")
    Flux<Message> echo(Message message) {
        log.info("received: {}", message);
        return Flux.just(new Message("Hello, I received " + message.getPayload()),
                         new Message("Hello, I received " + message.getPayload()),
                         new Message("Hello, I received " + message.getPayload()))
                   .delayElements(Duration.ofSeconds(1L));
    }
}
