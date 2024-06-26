package com.example.appexample1.ui

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appexample1.R
import com.example.appexample1.ui.theme.AppExample1Theme

// Define una actividad de Android llamada CalculadoraActivity
class CalculadoraActivity : ComponentActivity() {
    // Método onCreate que se llama cuando la actividad se está creando
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita el diseño de borde a borde
        setContent {
            // Establece el contenido de la actividad utilizando la función composable CalculadoraApp
            CalculadoraApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculadoraApp() {
    // Variables de estado para la entrada de texto y la lógica de la calculadora
    val input = rememberSaveable { mutableStateOf("") }
    val operador = rememberSaveable { mutableStateOf<String?>(null) }
    val operandoAnterior = rememberSaveable { mutableStateOf<Double?>(null) }
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    // Define el título de la barra superior
                    title = {
                        Text(
                            text = "\uD83D\uDC4B CALCULADORA! \uD83D\uDCAA", // Título del TopAppBar
                            modifier = Modifier
                                .fillMaxSize() // Ocupa todo el tamaño disponible
                                .wrapContentSize(Alignment.Center) // Centra el texto tanto vertical como horizontalmente
                        )
                    },
                    // Establece el color de fondo del TopAppBar
                    modifier = Modifier.background(Color.Black)
                )
            }
        ) { innerPadding ->
            // Añade el resto del contenido de la app, usando innerPadding para evitar superposición con la barra de la app
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.Black)
            ) {
                // Caja de texto para mostrar y actualizar la entrada del usuario
                TextField(
                    value = input.value,
                    textStyle = TextStyle(
                        textAlign = TextAlign.Right,
                        fontSize = 25.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold
                    ),
                    onValueChange = { newValue -> input.value = newValue },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )

                // Llama a la función que muestra una cuadrícula de botones
                EjemploDeCuadricula { boton ->
                    // Procesa la operación al hacer clic en un botón
                    manejarBoton(boton, input, operador, operandoAnterior)
                }
            }
        }
    }
}

@Composable
fun EjemploDeCuadricula(onButtonClick: (String) -> Unit) {
    // Lista de los textos que se mostrarán en los botones de la calculadora
    val botones = listOf(
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        ".", "0", "X", "+",
        "C", "", "", "="
    )

    // Crea una cuadrícula vertical con desplazamiento (LazyVerticalGrid)
    LazyVerticalGrid(
        columns = GridCells.Fixed(4), // Define 4 columnas en la cuadrícula
        verticalArrangement = Arrangement.spacedBy(4.dp), // Espaciado vertical de 4dp entre elementos
        horizontalArrangement = Arrangement.spacedBy(4.dp), // Espaciado horizontal de 4dp entre elementos
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // La cuadrícula ocupa todo el espacio disponible

    ) {
        // Itera sobre la lista de botones y crea un elemento de cuadrícula para cada uno
        items(botones) { boton ->
            CalculatorButton(boton, onButtonClick) // Llama a la función que crea un botón
        }
    }
}

// Función Composable que crea un botón de la calculadora
@Composable
fun CalculatorButton(buttonText: String, onButtonClick: (String) -> Unit) {
    // Define los colores del botón según el texto que contiene
    val buttonColors = when (buttonText) {
        "/", "*", "-", "+", "=", ".", "C" -> ButtonDefaults.buttonColors( // Botones de operación (cyan)
            containerColor = colorResource(id = R.color.orange),
            contentColor = Color.White
        )

        "X" -> ButtonDefaults.buttonColors( // Botón de multiplicación (rojo)
            containerColor = Color.Yellow,
            contentColor = Color.Black
        )

        "" -> ButtonDefaults.buttonColors( // Botón vacío (blanco)
            containerColor = Color.Black,
            contentColor = Color.Black
        )

        else -> ButtonDefaults.buttonColors( // Botones numéricos (gris oscuro)
            containerColor = colorResource(id = R.color.botongris),
            contentColor = Color.White
        )
    }

    // Crea un botón con los colores definidos y el texto correspondiente
    Button(
        onClick = { onButtonClick(buttonText) }, // Llama a la función onButtonClick cuando se hace clic
        colors = buttonColors, // Aplica los colores definidos
        modifier = Modifier.aspectRatio(1f) // Asegura que el botón sea cuadrado
    ) {
        Text(text = buttonText, fontSize = 25.sp) // Muestra el texto del botón
    }
}


// Función para manejar los clics de los botones
fun manejarBoton(
    boton: String,
    input: MutableState<String>,
    operador: MutableState<String?>,
    operandoAnterior: MutableState<Double?>
) {
    when (boton) {
        in "0".."9" -> input.value += boton
        "+", "-", "*", "/" -> {
            // Si ya hay una operación anterior y un operador seleccionado, calcula el resultado antes de continuar
            if (operandoAnterior.value != null && operador.value != null) {
                val operandoActual = input.value.toDoubleOrNull()
                val resultado = when (operador.value) {
                    "+" -> operandoAnterior.value!! + (operandoActual ?: 0.0)
                    "-" -> operandoAnterior.value!! - (operandoActual ?: 0.0)
                    "*" -> operandoAnterior.value!! * (operandoActual ?: 1.0)
                    "/" -> operandoAnterior.value!! / (operandoActual ?: 1.0)
                    else -> null
                }
                input.value = resultado?.toString() ?: "Error"
                operador.value = boton // Actualiza el operador para la nueva operación
                operandoAnterior.value = resultado // Establece el resultado como operando anterior
            } else {
                // Si no hay operaciones anteriores, establece el operador y el operando actual
                operador.value = boton
                operandoAnterior.value = input.value.toDoubleOrNull()
                input.value = ""
            }
        }

        "." -> if (!input.value.contains(".")) input.value += "."
        "=" -> {
            // Calcula el resultado final si hay un operador y un operando actual
            if (operandoAnterior.value != null && operador.value != null) {
                val operandoActual = input.value.toDoubleOrNull()
                val resultado = when (operador.value) {
                    "+" -> operandoAnterior.value!! + (operandoActual ?: 0.0)
                    "-" -> operandoAnterior.value!! - (operandoActual ?: 0.0)
                    "*" -> operandoAnterior.value!! * (operandoActual ?: 1.0)
                    "/" -> operandoAnterior.value!! / (operandoActual ?: 1.0)
                    else -> null
                }
                input.value = resultado?.toString() ?: "Error"
                operador.value = null // Restablece el operador
                operandoAnterior.value = null // Restablece el operando anterior
            }
        }

        "C" -> {
            // Limpiar la entrada y restablecer operador y operando anterior
            input.value = ""
            operador.value = null
            operandoAnterior.value = null
        }
    }
}


// Función de vista previa para mostrar cómo se verá la UI en el editor
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    // Aplica el tema de la aplicación
    AppExample1Theme {
        // Llama a la función que configura la UI principal
        CalculadoraApp()
    }
}
