package com.example.moveapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.moveapp.Adapter.Adapter
import com.example.moveapp.Cache.Cache
import com.example.moveapp.Models.User
import com.example.moveapp.databinding.ActivityAboutBinding
import com.example.moveapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var userAdapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Cache.init(this)

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddAndEditActivity::class.java)
            startActivity(intent)
        }

        binding.rv.setOnClickListener {
            val intent = Intent(this, ActivityAboutBinding::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        userAdapter = Adapter(Cache.obektString, object : Adapter.RvAction {
            override fun delete(user: User, position: Int) {
                val list = Cache.obektString
                list.removeAt(position)
                Cache.obektString = list
                onResume()
            }

            override fun edt(position: Int) {
                val intent = Intent(this@MainActivity, AddAndEditActivity::class.java)
                intent.putExtra("position", position)
                startActivity(intent)
            }

            override fun about(user: User, position: Int) {
                val intent = Intent(this@MainActivity, About::class.java)
                intent.putExtra("position", position)
                startActivity(intent)
            }
        })

        binding.rv.adapter = userAdapter
    }

}