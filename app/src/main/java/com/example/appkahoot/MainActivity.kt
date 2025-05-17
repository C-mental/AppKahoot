package com.example.appkahoot

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    data class Pregunta(val texto: String, val opciones: List<String>, val correcta: Int)

    private val preguntas = listOf(
        Pregunta("¿Qué lenguaje se usa principalmente en Android moderno?", listOf("Java", "Kotlin", "Swift", "Python"), 1),
        Pregunta("¿Qué archivo define la interfaz de usuario en Android?", listOf("AndroidManifest.xml", "MainActivity.kt", "build.gradle", "activity_main.xml"), 3),
        Pregunta("¿Qué IDE se recomienda para desarrollar apps Android?", listOf("Xcode", "Eclipse", "Android Studio", "VS Code"), 2),
        Pregunta("¿Qué es un Intent en Android?", listOf("Una imagen", "Un evento", "Una clase para mover entre pantallas", "Un botón"), 2),
        Pregunta("¿Qué función ejecuta el código cuando una app inicia?", listOf("start()", "onCreate()", "main()", "init()"), 1),
        Pregunta("¿Qué archivo contiene los permisos de una app Android?", listOf("build.gradle", "MainActivity.kt", "strings.xml", "AndroidManifest.xml"), 3),
        Pregunta("¿Qué extensión tienen los archivos de diseño en Android?", listOf(".xml", ".kt", ".java", ".json"), 0),
        Pregunta("¿Qué plataforma es solo para iOS?", listOf("Android Studio", "Flutter", "React Native", "Xcode"), 3),
        Pregunta("¿Qué es un RecyclerView?", listOf("Un tipo de botón", "Una lista eficiente de datos", "Una imagen animada", "Una actividad"), 1),
        Pregunta("¿Para qué sirve ViewModel en Android?", listOf("Guardar datos de la app", "Dibujar texto", "Pintar botones", "Soportar rotación sin perder datos"), 3),
        Pregunta("¿Qué palabra clave define una función en Kotlin?", listOf("function", "fun", "def", "method"), 1),
        Pregunta("¿Qué hace setContentView() en Android?", listOf("Cambia de actividad", "Muestra una alerta", "Asocia la vista XML con el código", "Cierra la app"), 2),
        Pregunta("¿Qué componente permite desplazamiento vertical?", listOf("TextView", "RecyclerView", "ScrollView", "LinearLayout"), 2),
        Pregunta("¿Qué clase se usa para botones?", listOf("EditText", "TextView", "Button", "View"), 2),
        Pregunta("¿Qué estructura de datos usa Firebase Realtime Database?", listOf("SQL", "Documentos", "Árbol JSON", "CSV"), 2),
        Pregunta("¿Qué símbolo se usa para templates en Kotlin?", listOf("\$", "%", "#", "&"), 0),
        Pregunta("¿Qué es una Activity?", listOf("Un servicio de red", "Una pantalla o interfaz", "Un botón oculto", "Un menú"), 1),
        Pregunta("¿Qué es un Fragment?", listOf("Una imagen", "Una porción de UI reutilizable", "Una clase de animación", "Un plugin"), 1),
        Pregunta("¿Qué significa dp en Android?", listOf("Datos por segundo", "Densidad independiente de píxeles", "Diseño preciso", "Desempeño del procesador"), 1),
        Pregunta("¿Cuál es la arquitectura recomendada por Google para apps modernas?", listOf("MVC", "MVP", "MVVM", "DAO"), 2)
    )

    private var indice = 0
    private var puntaje = 0
    private var respondido = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 💬 Nuevo: obtenemos el nombre del jugador desde el intent
        val nombreJugador = intent.getStringExtra("nombreJugador") ?: "Jugador"

        // 🎯 Mostramos el nombre en el título
        val tvTitulo = findViewById<TextView>(R.id.tvTitulo)
        tvTitulo.text = "Hola, $nombreJugador 👋"

        val tvPregunta = findViewById<TextView>(R.id.tvPregunta)
        val tvPuntaje = findViewById<TextView>(R.id.tvPuntaje)
        val tvPreguntaNum = findViewById<TextView>(R.id.tvPreguntaNum)
        val btnSiguiente = findViewById<Button>(R.id.btnSiguiente)

        val botones = listOf(
            findViewById<Button>(R.id.btnOpcion1),
            findViewById<Button>(R.id.btnOpcion2),
            findViewById<Button>(R.id.btnOpcion3),
            findViewById<Button>(R.id.btnOpcion4)
        )

        fun actualizarContadores() {
            tvPuntaje.text = "Puntaje: ${puntaje.toFloat()} / ${preguntas.size}"
            tvPreguntaNum.text = "Pregunta: ${indice + 1}/${preguntas.size}"
        }

        fun mostrarPregunta() {
            if (indice >= preguntas.size) {
                val intent = Intent(this, ResultadoActivity::class.java)
                intent.putExtra("puntaje", puntaje.toFloat())
                intent.putExtra("total", preguntas.size)
                intent.putExtra("nombreJugador", nombreJugador) // 🟢 También lo pasamos al final si lo quieres
                startActivity(intent)
                finish()
                return
            }

            val p = preguntas[indice]
            tvPregunta.text = p.texto
            respondido = false

            for (i in botones.indices) {
                botones[i].text = p.opciones[i]
                botones[i].setBackgroundColor(Color.LTGRAY)
                botones[i].isEnabled = true
            }

            for (i in botones.indices) {
                botones[i].setOnClickListener {
                    if (!respondido) {
                        respondido = true
                        if (i == p.correcta) {
                            botones[i].setBackgroundColor(Color.GREEN)
                            puntaje++
                        } else {
                            botones[i].setBackgroundColor(Color.RED)
                            botones[p.correcta].setBackgroundColor(Color.GREEN)
                        }
                        for (b in botones) b.isEnabled = false
                        actualizarContadores()
                    }
                }
            }

            actualizarContadores()
        }

        btnSiguiente.setOnClickListener {
            indice++
            mostrarPregunta()
        }

        mostrarPregunta()
    }
}
