package com.iot.mqttclient.handler;

import com.iot.mqttclient.service.EngineTemperatureSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class MqttHandler {

    @Autowired
    private EngineTemperatureSensor sensor;

    public Mono<ServerResponse> publish(ServerRequest serverRequest) {
//        Mono<String> message = Mono.just(serverRequest.pathVariable("message"));
        Mono<Void> message = Mono.fromCallable(() -> sensor.call());

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(message, String.class);

    }
}
