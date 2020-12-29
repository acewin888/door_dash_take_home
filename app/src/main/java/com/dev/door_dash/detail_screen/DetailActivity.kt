package com.dev.door_dash.detail_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dev.door_dash.R
import com.dev.door_dash.data.DashDetail
import com.dev.door_dash.summary_screen.SummaryActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var dashDetail: DashDetail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        dashDetail = intent?.extras?.get(SummaryActivity.bundleKey) as DashDetail

        dashDetail?.let {
            detail_phone_number.text = it.phone_number
        } ?: Log.d("doordash", "something went wrong in parsing")
    }
}