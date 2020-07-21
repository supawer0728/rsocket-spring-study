package com.parfait.study.rsocket.client.message;

import java.util.Map;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parfait.study.rsocket.client.message.domain.Message;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("/message")
public class MessageController {

    private final RSocketRequester requester;

    @GetMapping
    public Flux<Map<String, Message>> hello(@RequestParam(defaultValue = "Guest") String name) {
        Message message = new Message("My name is " + name);
        return requester.route("echo")
                        .data(message)
                        .retrieveFlux(Message.class)
                        .map(received -> Map.of("send", message, "received", received));
    }
}
