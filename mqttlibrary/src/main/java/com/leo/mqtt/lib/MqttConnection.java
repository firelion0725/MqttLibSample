package com.leo.mqtt.lib;

import android.content.Context;

import com.leo.mqtt.lib.callback.ActionCallBack;
import com.leo.mqtt.lib.callback.ConnectionCallBack;
import com.leo.mqtt.lib.callback.DisConnectionCallBack;
import com.leo.mqtt.lib.constants.ConnectStatus;
import com.leo.mqtt.lib.constants.QosLevel;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author : Leo
 * @date : 2017/10/18
 */

public class MqttConnection {

    public MqttConnection(Context context, String url, String clientId) {
        initClient(context, url, clientId);
    }

    private static final String USER_CONTEXT = "user_context";

    private static final int TIME_OUT = 30;

    private int mqttStatus = ConnectStatus.NONE;
    private String topic;

    private MqttAndroidClient client = null;
    private MqttConnectOptions mqttConnectOptions;

    public MqttConnectOptions getMqttConnectOptions() {
        return mqttConnectOptions;
    }

    public void setMqttConnectOptions(MqttConnectOptions mqttConnectOptions) {
        this.mqttConnectOptions = mqttConnectOptions;
    }

    private MqttConnectOptions getAvaiMqttConnectOptions() {
        return mqttConnectOptions != null ? mqttConnectOptions
                : initMqttConnectOptions();
    }

    public int getMqttStatus() {
        return mqttStatus;
    }

    public void setMqttStatus(int mqttStatus) {
        this.mqttStatus = mqttStatus;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * 如果没有发现外部set的MqttConnectOptions对象（@code mqttConnectOptions == null）
     * 则初始化创建一个默认通用对象
     *
     * @return MqttConnectOptions
     */
    private MqttConnectOptions initMqttConnectOptions() {
        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setCleanSession(true);
        connectOptions.setConnectionTimeout(TIME_OUT);
        connectOptions.setKeepAliveInterval(TIME_OUT);
        return connectOptions;
    }

    private void initClient(Context context, String url, String clientId) {
        if (client == null) {
            client = new MqttAndroidClient(context, url, clientId);
            client.registerResources(context);
        }
    }

    public boolean isConnected() {
        return client != null && client.isConnected();
    }

    public void connect() throws Exception {
        connect(new ConnectionCallBack(this));
    }

    public void connect(ConnectionCallBack listener) throws Exception {
        setMqttStatus(ConnectStatus.CONNECTING);
        client.connect(getAvaiMqttConnectOptions(), USER_CONTEXT, listener);
    }

    public void disconnect() throws Exception {
        disconnect(new DisConnectionCallBack(this));
    }

    public void disconnect(DisConnectionCallBack listener) throws Exception {
        setMqttStatus(ConnectStatus.DISCONNECTING);
        client.disconnect(USER_CONTEXT, listener);
    }

    public void close() {
        client.unregisterResources();
        client.close();
        setMqttStatus(ConnectStatus.NONE);
    }

    public void publish(String topic, MqttMessage message) throws Exception {
        client.publish(topic, message);
    }

    public void publish(MqttMessage message) throws Exception {
        publish(topic, message);
    }

    public void publish(String topic, byte[] payload, ActionCallBack listener) throws Exception {
        publish(topic, payload, false, listener);
    }

    public void publish(String topic, byte[] payload, boolean retained, ActionCallBack listener) throws Exception {
        publish(topic, payload, QosLevel.VERY_IMPORTANT, retained, listener);
    }

    public void publish(String topic, byte[] payload, int qos, boolean retained, ActionCallBack listener) throws Exception {
        client.publish(topic, payload, qos, retained, USER_CONTEXT, listener);
    }

    public void subscribe() throws Exception {
        subscribe(topic, QosLevel.IMPORTANT);
    }

    public void subscribe(String topic, int qos) throws Exception {
        client.subscribe(topic, qos);
    }

    public void subscribe(String[] topics, int[] qos) throws Exception {
        client.subscribe(topics, qos);
    }

    public void subscribe(String topic, int qos, ActionCallBack listener) throws Exception {
        client.subscribe(topic, qos, USER_CONTEXT, listener);
    }

    public void subscribe(String[] topics, int[] qos, ActionCallBack listener) throws Exception {
        client.subscribe(topics, qos, USER_CONTEXT, listener);
    }

    public void subscribe(String topic, int qos, ActionCallBack listener, IMqttMessageListener messageListener) throws Exception {
        client.subscribe(topic, qos, USER_CONTEXT, listener, messageListener);
    }

    public void subscribe(String[] topics, int[] qos, ActionCallBack listener, IMqttMessageListener[] messageListeners) throws Exception {
        client.subscribe(topics, qos, USER_CONTEXT, listener, messageListeners);
    }

    public void unSubscribe() throws Exception {
        unSubscribe(topic);
    }

    public void unSubscribe(String topic) throws Exception {
        client.unsubscribe(topic);
    }

    public void unSubscribe(String[] topics) throws Exception {
        client.unsubscribe(topics);
    }

    public void unSubscribe(String topic, ActionCallBack listener) throws Exception {
        client.unsubscribe(topic, USER_CONTEXT, listener);
    }

    public void unSubscribe(String[] topics, ActionCallBack listener) throws Exception {
        client.unsubscribe(topics, USER_CONTEXT, listener);
    }

    public void setCallBack(MqttCallback callBack) {
        client.setCallback(callBack);
    }

    public void registerResources(Context context) {
        client.registerResources(context);
    }

    public void unregisterResources() {
        client.unregisterResources();
    }
}
