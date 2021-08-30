package com.iot.mqttclient.service;

import com.iot.mqttclient.config.Mqtt;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Callable;

@Service
public class EngineTemperatureSensor implements Callable<Void> {

    private static final Logger log = LoggerFactory.getLogger(EngineTemperatureSensor.class);
    public static final String TOPIC = "engine/temperature";

    private Random rnd = new Random();

    @Override
    public Void call() throws Exception {

        MqttMessage msg = readEngineTemp();
        msg.setQos(0);
        msg.setRetained(true);
        Mqtt.getInstance().publish(TOPIC,msg);

        return null;
    }

    /**
     * This method simulates reading the engine temperature
     * @return
     */
    private MqttMessage readEngineTemp() {
        double temp =  80 + rnd.nextDouble() * 20.0;
        byte[] payload = String.format("T:%04.2f",temp).getBytes();
        MqttMessage msg = new MqttMessage(payload);
        return msg;
    }
}