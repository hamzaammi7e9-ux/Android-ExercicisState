package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Button
import androidx.compose.material3.Card

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PropinaApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PropinaApp(modifier: Modifier = Modifier) {
    var preu by rememberSaveable { mutableStateOf("") }
    var percentatge by rememberSaveable { mutableStateOf("") }
    var resultat by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = preu,
            onValueChange = { preu = it },
            label = { Text("Preu menú (€)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        TextField(
            value = percentatge,
            onValueChange = { percentatge = it },
            label = { Text("Propina (%)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )


        Button(onClick = {
            val preuNum = preu.toFloatOrNull() ?: 0f
            val percNum = percentatge.toFloatOrNull() ?: 0f
            val propina = preuNum * (percNum / 100)
            resultat = "Propina: €${"%.2f".format(propina)}\nTotal: €${"%.2f".format(preuNum + propina)}"
        }) {
            Text("Calcular", fontSize = 18.sp)
        }


        if (resultat.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text(
                    text = resultat,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
@Preview
fun AppPreview() {
    MyApplicationTheme {
        PropinaApp()
    }
}

