package com.leo.mqtt.lib.callback;

import com.leo.mqtt.lib.MqttConnection;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

/**
 * @author : Leo
 * @date : 2017/10/18
 */

abstract class BaseCallBack implements IMqttActionListener {

    ActionCallBackImpl connectActionCallBack;

    MqttConnection connection;

    BaseCallBack(MqttConnection connection) {
        this.connection = connection;
    }

    public BaseCallBack(ActionCallBackImpl connectActionCallBack, MqttConnection connection) {
        this.connectActionCallBack = connectActionCallBack;
        this.connection = connection;
    }

    void success(IMqttToken asyncActionToken) {
        if (connectActionCallBack != null) {
            connectActionCallBack.onSuccess(asyncActionToken);
        }
    }

    void failure(IMqttToken asyncActionToken, Throwable exception) {
        if (connectActionCallBack != null) {
            connectActionCallBack.onFailure(asyncActionToken, exception);
        }
    }
}
