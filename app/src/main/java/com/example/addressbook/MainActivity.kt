package com.example.addressbook

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    private lateinit var editTextName: EditText
    private lateinit var editTextSurname: EditText
    private lateinit var editTextAddress: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var listView: ListView
    private lateinit var buttonSave: Button

    private val users = mutableListOf<Person>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        editTextName = findViewById(R.id.editTextName)
        editTextSurname = findViewById(R.id.editTextSurname)
        editTextAddress = findViewById(R.id.editTextAddress)
        editTextPhone = findViewById(R.id.editTextPhone)
        listView = findViewById(R.id.listView)
        buttonSave = findViewById(R.id.buttonSave)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listView.adapter = adapter

        buttonSave.setOnClickListener {
            saveUser(it)
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val user = users[position]
            val intent = Intent(this, Info::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }

    private fun saveUser(view: View) {
        val name = editTextName.text.toString()
        val surname = editTextSurname.text.toString()
        val address = editTextAddress.text.toString()
        val phone = editTextPhone.text.toString()

        if (name.isNotBlank() && surname.isNotBlank() && address.isNotBlank() && phone.isNotBlank()) {
            val user = Person(name, surname, address, phone)
            users.add(user)
            adapter.add("$name $surname")
            editTextName.text.clear()
            editTextSurname.text.clear()
            editTextAddress.text.clear()
            editTextPhone.text.clear()
        } else {
            Snackbar.make(view, "Поля не могут быть пустыми", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        /*for (i in 0 until (menu?.size() ?: 0)) {
            val menuItem = menu?.getItem(i)
            val spanString = SpannableString(menuItem?.title.toString())
            spanString.setSpan(
                ForegroundColorSpan(Color.BLACK),
                0,
                spanString.length,
                0
            )
            menuItem?.title = spanString
        }*/
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                finishAffinity()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}