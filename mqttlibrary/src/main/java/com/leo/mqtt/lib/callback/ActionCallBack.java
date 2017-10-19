package com.leo.mqtt.lib.callback;

import com.leo.mqtt.lib.MqttConnection;

import org.eclipse.paho.client.mqttv3.IMqttToken;

/**
 * @author : Leo
 * @date : 2017/10/18
 */

public class ActionCallBack extends BaseCallBack {

    public ActionCallBack(MqttConnection connection) {
        super(connection);
    }

    public ActionCallBack(ActionCallBackImpl connectActionCallBack, MqttConnection connection) {
        super(connectActionCallBack, connection);
    }

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        success(asyncActionToken);
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        failure(asyncActionToken, exception);
    }
}
