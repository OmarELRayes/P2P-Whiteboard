package com.omarelrayes.p2pwhiteboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.nearby.Nearby
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast
import com.google.android.gms.nearby.connection.*


class HostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
    }

    override fun onResume() {
        super.onResume()
        val advertisingOptions = AdvertisingOptions.Builder().setStrategy(Strategy.P2P_POINT_TO_POINT).build()
        Nearby.getConnectionsClient(this).apply{
            startAdvertising(
                "Test", "com.omarelrayes.p2pwhiteboard", object : ConnectionLifecycleCallback() {
                    override fun onConnectionResult(p0: String, p1: ConnectionResolution) {
                        Toast.makeText(this@HostActivity, "onConnectionResult : $p0", Toast.LENGTH_LONG).show()
                        acceptConnection(p0,object : PayloadCallback() {
                            override fun onPayloadReceived(p0: String, p1: Payload) {
                                Toast.makeText(this@HostActivity, "onPayloadReceived : $p0", Toast.LENGTH_LONG).show()
                            }

                            override fun onPayloadTransferUpdate(
                                p0: String,
                                p1: PayloadTransferUpdate
                            ) {
                                Toast.makeText(this@HostActivity, "onPayloadTransferUpdate : $p0", Toast.LENGTH_LONG).show()
                            }
                        })
                    }

                    override fun onDisconnected(p0: String) {
                        Toast.makeText(this@HostActivity, "onDisconnected : $p0", Toast.LENGTH_LONG).show()
                    }

                    override fun onConnectionInitiated(p0: String, p1: ConnectionInfo) {
                        Toast.makeText(this@HostActivity, "onConnectionInitiated : $p0", Toast.LENGTH_LONG).show()
                    }
                }, advertisingOptions
            )
        }

    }
}
