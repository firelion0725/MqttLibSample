package com.leo.mqtt.lib.constants;

/**
 * @author : Leo
 * @date : 2017/10/18
 */

public class ConnectStatus {
    public static final int NONE = -1;
    public static final int CONNECTING = 0;
    public static final int CONNECTED = 1;
    public static final int DISCONNECTING = 2;
    public static final int DISCONNECTED = 3;
    public static final int ERROR = 4;
}
