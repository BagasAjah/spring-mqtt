package com.iot.mqttclient.router;

import com.iot.mqttclient.handler.MqttHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class MqttRouter {
    @Bean
    public RouterFunction<ServerResponse> mqttsRoute(MqttHandler handler){

        return RouterFunctions
                .route(GET("/mqtt/{message}").and(accept(MediaType.APPLICATION_JSON))
                        ,handler::publish);
//                .andRoute(POST("/product").and(accept(MediaType.APPLICATION_JSON))
//                        ,handler::createProduct)
    }

}
