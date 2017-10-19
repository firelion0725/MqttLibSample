package com.leo.mqtt.callback;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author : Leo
 * @date : 2017/10/19
 */

public class MqttMessageCallBack implements MqttCallback {
    @Override
    public void connectionLost(Throwable cause) {
        if (cause != null) {
            cause.printStackTrace();
        }
//        Log.i("MainActivity", "connectionLost:" + cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Log.i("MainActivity", "messageArrived:" + message.toString());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        Log.i("MainActivity", "deliveryComplete:" + token.toString());
    }
}
