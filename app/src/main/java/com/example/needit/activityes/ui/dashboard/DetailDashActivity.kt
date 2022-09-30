package com.example.needit.activityes.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.needit.R
import kotlinx.android.synthetic.main.activity_detail_dash.*

class DetailDashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_dash)

        val person = intent
        if(person != null) {
            stuff_title.text = person.getStringExtra("name")
            stuff_description.text = person.getStringExtra("description")
            stuff_address.text = person.getStringExtra("address")
        }
    }
}