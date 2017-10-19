package com.leo.mqtt.lib.callback;

import com.leo.mqtt.lib.MqttConnection;
import com.leo.mqtt.lib.constants.ConnectStatus;

import org.eclipse.paho.client.mqttv3.IMqttToken;

/**
 * @author : Leo
 * @date : 2017/10/19
 */

public class ConnectionCallBack extends ActionCallBack {

    public ConnectionCallBack(MqttConnection connection) {
        super(connection);
    }

    public ConnectionCallBack(ActionCallBackImpl connectActionCallBack, MqttConnection connection) {
        super(connectActionCallBack, connection);
    }

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        connection.setMqttStatus(ConnectStatus.CONNECTED);
        super.onSuccess(asyncActionToken);
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        connection.setMqttStatus(ConnectStatus.ERROR);
        super.onFailure(asyncActionToken, exception);
    }
}
