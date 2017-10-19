package com.leo.mqtt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.leo.mqtt.callback.MqttMessageCallBack
import com.leo.mqtt.lib.MqttConnection
import com.leo.mqtt.lib.callback.ActionCallBack
import com.leo.mqtt.lib.callback.ActionCallBackImpl
import com.leo.mqtt.lib.callback.ConnectionCallBack
import com.leo.mqtt.lib.callback.DisConnectionCallBack
import kotlinx.android.synthetic.main.activity_main.*
import org.eclipse.paho.client.mqttv3.IMqttToken

class MainActivity : AppCompatActivity() {

    private var connection:MqttConnection? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connection = MqttConnection(this, "tcp://192.168.1.137:1884", "TEST")
        connection!!.setCallBack(MqttMessageCallBack())
        obj_button.setOnClickListener({

        })

        connect_button.setOnClickListener {
            connection!!.connect(ConnectionCallBack(object : ActionCallBackImpl {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.i("MainActivity", "onSuccess:" + connection!!.mqttStatus)

                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    exception?.printStackTrace()
                    Log.i("MainActivity", "onFailure:" + connection!!.mqttStatus)
                }

            }, connection))
        }

        subscribe_button.setOnClickListener {
            connection!!.subscribe("CL1712_500", 0, ActionCallBack(object : ActionCallBackImpl {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.i("MainActivity", "onSuccess:" + connection!!.mqttStatus)
                    Log.i("MainActivity", "onSuccess:" + asyncActionToken!!.topics)
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    exception?.printStackTrace()
                    Log.i("MainActivity", "onFailure:" + connection!!.mqttStatus)
                }

            }, connection))
        }

        disconnect_button.setOnClickListener {
            connection!!.disconnect(DisConnectionCallBack(object : ActionCallBackImpl {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.i("MainActivity", "onSuccess:" + connection!!.mqttStatus)
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    exception?.printStackTrace()
                    Log.i("MainActivity", "onFailure:" + connection!!.mqttStatus)
                }

            }, connection))
        }


    }

    override fun onDestroy() {
        connection!!.close()
        super.onDestroy()
    }
}
