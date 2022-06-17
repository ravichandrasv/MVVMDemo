package com.sample.newsdemo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.newsdemo.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frag_container, NewsListFragment()).commit()
    }
}
