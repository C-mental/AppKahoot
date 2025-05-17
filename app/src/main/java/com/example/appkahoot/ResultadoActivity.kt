package com.example.appkahoot

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val tvFinalMensaje = findViewById<TextView>(R.id.tvFinalMensaje)
        val tvFinalPuntaje = findViewById<TextView>(R.id.tvFinalPuntaje)
        val imgResultado = findViewById<ImageView>(R.id.imgResultado)
        val btnReintentar = findViewById<Button>(R.id.btnReintentar)

        // 🟢 Recibimos nombre, puntaje y total
        val nombreJugador = intent.getStringExtra("nombreJugador") ?: "Jugador"
        val puntaje = intent.getFloatExtra("puntaje", 0f)
        val total = intent.getIntExtra("total", 0)

        // 🎯 Mensaje personalizado + imagen
        when {
            puntaje >= 16 -> {
                tvFinalMensaje.text = "¡Felicidades, $nombreJugador! Excelente trabajo 🎉"
                imgResultado.setImageResource(R.drawable.puntaje_alto)
            }
            puntaje >= 11 -> {
                tvFinalMensaje.text = "¡Muy bien, $nombreJugador! Sigue así 💪"
                imgResultado.setImageResource(R.drawable.puntajo_medio)
            }
            else -> {
                tvFinalMensaje.text = "¡Ánimo $nombreJugador! Vas a mejorar 😊"
                imgResultado.setImageResource(R.drawable.puntaje_bajo)
            }
        }

        tvFinalPuntaje.text = "Tu puntaje fue: $puntaje / $total"

        // 🔁 Reintentar
        btnReintentar.setOnClickListener {
            finishAffinity() // Cierra todas las actividades
            startActivity(Intent(this, InicioActivity::class.java))
        }
    }
}
