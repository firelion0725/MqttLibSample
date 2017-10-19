package com.leo.mqtt.lib.callback;

import org.eclipse.paho.client.mqttv3.IMqttToken;

/**
 * @author : Leo
 * @date : 2017/10/18
 */

public interface ActionCallBackImpl {
    /**
     * This method is invoked when an action has completed successfully.
     *
     * @param asyncActionToken associated with the action that has completed
     */
    public void onSuccess(IMqttToken asyncActionToken);

    /**
     * This method is invoked when an action fails.
     * If a client is disconnected while an action is in progress
     * onFailure will be called. For connections
     * that use cleanSession set to false, any QoS 1 and 2 messages that
     * are in the process of being delivered will be delivered to the requested
     * quality of service next time the client connects.
     *
     * @param asyncActionToken associated with the action that has failed
     * @param exception        thrown by the action that has failed
     */
    public void onFailure(IMqttToken asyncActionToken, Throwable exception);
}
