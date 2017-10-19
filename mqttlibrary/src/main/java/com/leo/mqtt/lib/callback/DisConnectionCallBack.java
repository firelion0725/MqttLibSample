package com.leo.mqtt.lib.callback;

import com.leo.mqtt.lib.MqttConnection;
import com.leo.mqtt.lib.constants.ConnectStatus;

import org.eclipse.paho.client.mqttv3.IMqttToken;

/**
 * @author : Leo
 * @date : 2017/10/19
 */

public class DisConnectionCallBack extends ActionCallBack {

    public DisConnectionCallBack(MqttConnection connection) {
        super(connection);
    }

    public DisConnectionCallBack(ActionCallBackImpl connectActionCallBack, MqttConnection connection) {
        super(connectActionCallBack, connection);
    }

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        connection.setMqttStatus(ConnectStatus.DISCONNECTED);
        super.onSuccess(asyncActionToken);
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        connection.setMqttStatus(ConnectStatus.ERROR);
        super.onFailure(asyncActionToken, exception);
    }
}
