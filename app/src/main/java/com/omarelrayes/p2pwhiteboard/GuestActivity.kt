package com.omarelrayes.p2pwhiteboard

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.connection.*


class GuestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)

    }

    override fun onResume() {
        super.onResume()

        startDiscovery()
    }

    private fun startDiscovery() {
        val discoveryOptions =
            DiscoveryOptions.Builder().setStrategy(Strategy.P2P_POINT_TO_POINT).build()
        Nearby.getConnectionsClient(this).apply {
            startDiscovery("com.omarelrayes.p2pwhiteboard", object : EndpointDiscoveryCallback(){
                override fun onEndpointFound(p0: String, p1: DiscoveredEndpointInfo) {
                    Toast.makeText(this@GuestActivity, "onEndpointFound : $p0", Toast.LENGTH_LONG).show()
                    acceptConnection(p0, object : PayloadCallback(){
                        override fun onPayloadReceived(p0: String, p1: Payload) {
                            Toast.makeText(this@GuestActivity, "onPayloadReceived : $p0", Toast.LENGTH_LONG).show()
                        }

                        override fun onPayloadTransferUpdate(
                            p0: String,
                            p1: PayloadTransferUpdate
                        ) {
                            Toast.makeText(this@GuestActivity, "onPayloadTransferUpdate : $p0", Toast.LENGTH_LONG).show()
                        }
                    })
                }

                override fun onEndpointLost(p0: String) {
                    Toast.makeText(this@GuestActivity, "onEndpointLost : $p0", Toast.LENGTH_LONG).show()
                }
            }, discoveryOptions)
        }

    }
}
