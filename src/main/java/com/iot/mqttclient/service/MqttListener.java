package com.iot.mqttclient.service;

import com.iot.mqttclient.config.Mqtt;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class MqttListener implements CommandLineRunner {

    public void subscribe(final String topic) throws MqttException, InterruptedException {
        System.out.println("Messages received:");

        Mqtt.getInstance().subscribeWithResponse(topic, (tpic, msg) -> {
            System.out.println(msg.getId() + " -> " + new String(msg.getPayload()));
        });
    }

    @Override
    public void run(String... args) throws Exception {
        subscribe("engine/temperature");
    }
}
