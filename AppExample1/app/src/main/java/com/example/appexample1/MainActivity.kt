package com.example.appexample1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {
    Scaffold(
        topBar = { AppBar() },
        content = { paddingValues -> Content(paddingValues) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    TopAppBar(
        title = { Text("Practicando") },
        Modifier.background(colorResource(id = R.color.bacgrou))
    )
}

@Composable
fun Content(paddingValues: PaddingValues) {
    var counter by rememberSaveable { mutableStateOf(0) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        item {
            HeaderImage()
            CounterRow(counter) { counter++ }
            InfoText()
            ExperienceText()
            HorizontalTextRow()
        }
    }
}

@Composable
fun HeaderImage() {
    Image(
        modifier = Modifier.fillMaxWidth(),
        painter = painterResource(id = R.drawable.img),
        contentDescription = "logo android"
    )
}

@Composable
fun CounterRow(counter: Int, onClick: () -> Unit) {
    Row(modifier = Modifier.padding(top = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.baseline_10k_24),
            contentDescription = null,
            Modifier
                .background(Color.Red)
                .clickable { onClick() }
        )
        Text(
            text = counter.toString(),
            color = Color.White,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun InfoText() {
    Text(
        text = "Irvin Dev.",
        fontSize = 32.sp,
        color = Color.White,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun ExperienceText() {
    Text(
        text = "7 Años de Experiencia",
        fontSize = 18.sp,
        color = Color.White,
        textAlign = TextAlign.Left
    )
}

@Composable
fun HorizontalTextRow() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "Hola 3",
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Left
            )
        }
        item {
            Text(
                text = "Hola 4",
                fontSize = 20.sp,
                color = Color.White,
            )
        }
        item {
            Text(
                text = "Hola 5",
                fontSize = 20.sp,
                color = Color.White,
            )
        }
    }
}

@Preview
@Composable
fun PreviewAppContent() {
    AppContent()
}
