@file:Suppress("DEPRECATION")

package com.example.addressbook

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Info : AppCompatActivity() {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    private lateinit var textViewName: TextView
    private lateinit var textViewSurname: TextView
    private lateinit var textViewAddress: TextView
    private lateinit var textViewPhone: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        textViewName = findViewById(R.id.textViewName)
        textViewSurname = findViewById(R.id.textViewSurname)
        textViewAddress = findViewById(R.id.textViewAddress)
        textViewPhone = findViewById(R.id.textViewPhone)

        val user = intent.getParcelableExtra<Person>("user")

        user?.let {
            textViewName.text = "Имя:\r\n${it.name}"
            textViewSurname.text = "Фамилия:\r\n${it.surname}"
            textViewAddress.text = "Адрес:\r\n${it.address}"
            textViewPhone.text = "Телефон:\r\n${it.phone}"
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}