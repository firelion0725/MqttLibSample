package com.leo.mqtt.lib.constants;

/**
 * @author : Leo
 * @date : 2017/10/18
 */

public class QosLevel {

    /**
     * 至多发送一次，发送即丢弃。没有确认消息，也不知道对方是否收到
     */
    public static final int NO_IMPORTANT = 0;

    /**
     * 针对消息的发布，Qos level 1，意味着消息至少被传输一次。
     * SUBSCRIBE和UNSUBSCRIBE消息使用QoS level 1。
     */
    public static final int IMPORTANT = 1;

    /**
     * 确保了仅仅传输接收一次。
     * 仅仅在PUBLISH类型消息中出现，要求在可变头部中要附加消息ID。
     */
    public static final int VERY_IMPORTANT = 2;

}
