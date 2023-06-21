package com.example.moveapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moveapp.Cache.Cache
import com.example.moveapp.databinding.ActivityAboutBinding

class About : AppCompatActivity() {
    private val binding by lazy { ActivityAboutBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Cache.init(this)

        binding.apply {
            val list = Cache.obektString
            val position = intent.getIntExtra("position", 1)
            txtName.text = list[position].name
            txtAbout.text = list[position].about
            txtDate.text = list[position].date
            txtProduser.text = list[position].produser
        }
    }
}