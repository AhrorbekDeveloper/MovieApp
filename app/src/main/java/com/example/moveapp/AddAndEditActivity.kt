package com.example.moveapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.moveapp.Cache.Cache
import com.example.moveapp.Models.User
import com.example.moveapp.R
import com.example.moveapp.databinding.ActivityAddAndEditBinding

class AddAndEditActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddAndEditBinding.inflate(layoutInflater) }
    var position = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Cache.init(this)

        position = intent.getIntExtra("position", -1)

        if (position!=-1){
            edt()
        }else{
            save()
        }

    }

    fun save(){
        binding.btnSave.setOnClickListener {
            val user = User(binding.edtName.text.toString(), binding.edtAbout.text.toString(), binding.edtDate.text.toString(), binding.edtProduser.text.toString())
            val list = Cache.obektString
            list.add(user)
            Cache.obektString = list
            Toast.makeText(this, "Saqlandi", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    fun edt(){
        val list = Cache.obektString
        binding.apply {
            edtName.setText(list[position].name)
            edtAbout.setText(list[position].about)
            edtDate.setText(list[position].date)
            edtProduser.setText(list[position].produser)

            btnSave.setOnClickListener {
                val user = User(edtName.text.toString(), edtAbout.text.toString(), edtDate.text.toString(), edtProduser.text.toString())
                list[position]=user
                Cache.obektString = list
                Toast.makeText(this@AddAndEditActivity, "Taxrirlandi", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}