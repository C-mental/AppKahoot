package com.example.appkahoot

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val btnComenzar = findViewById<Button>(R.id.btnComenzar)

        btnComenzar.isEnabled = false

        etNombre.setOnKeyListener { _, _, _ ->
            btnComenzar.isEnabled = etNombre.text.toString().isNotEmpty()
            false
        }

        btnComenzar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("nombreJugador", nombre)
            startActivity(intent)
            finish()
        }
    }
}
