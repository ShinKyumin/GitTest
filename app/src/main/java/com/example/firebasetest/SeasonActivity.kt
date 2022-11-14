package com.example.firebasetest

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.firebase.storage.ktx.storage

class SeasonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season)

        val rootref = Firebase.storage.reference

        val ref = rootref.child("fall.PNG")
        ref.getBytes(Long.MAX_VALUE).addOnCompleteListener {
            if(it.isSuccessful) {
                val bmp = BitmapFactory.decodeByteArray(it.result,0,it.result!!.size)
                val img = findViewById<ImageView>(R.id.seasonImage)
                img.setImageBitmap(bmp)
            }
        }

        val remoteConfig = Firebase.remoteConfig

        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        val settings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 1
        }
        remoteConfig.setConfigSettingsAsync(settings)

        remoteConfig.fetchAndActivate().addOnSuccessListener {

        }
    }
}