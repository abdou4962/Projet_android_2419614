package com.example.tp1_2419614native

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EcranLanceurDes(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun EcranLanceurDes(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Lanceur de Dés", style = MaterialTheme.typography.headlineMedium)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Configuration", style = MaterialTheme.typography.titleMedium)

                var nbDes by remember { mutableStateOf(1) }
                var menuOuvert by remember { mutableStateOf(false) }
                Box {
                    Button(onClick = { menuOuvert = true }) {
                        Text("Nombre de dés : $nbDes")
                    }
                    DropdownMenu(
                        expanded = menuOuvert,
                        onDismissRequest = { menuOuvert = false }
                    ) {
                        (1..6).forEach { nombre ->
                            DropdownMenuItem(
                                text = { Text("$nombre") },
                                onClick = {
                                    nbDes = nombre
                                    menuOuvert = false
                                    Log.i("LANCEUR_DES", "Nombre de dés : $nombre")
                                }
                            )
                        }
                    }
                }

                val faces = listOf(4, 6, 8, 10, 12, 20)
                var nbFaces by remember { mutableStateOf(1f) }
                Text("Nombre de faces : d${faces[nbFaces.toInt()]}")
                Slider(
                    value = nbFaces,
                    onValueChange = { nbFaces = it },
                    valueRange = 0f..(faces.lastIndex).toFloat(),
                    steps = faces.size - 2,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

                var tri by remember { mutableStateOf("Aucun") }
                Text("Tri des résultats : $tri")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = { tri = "Aucun" }) { Text("Aucun") }
                    Button(onClick = { tri = "Croissant" }) { Text("Croissant") }
                    Button(onClick = { tri = "Décroissant" }) { Text("Décroissant") }
                }
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { Log.d("ACTION", "Lancer cliqué") }) { Text("Lancer les dés") }
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Résultats", style = MaterialTheme.typography.titleMedium)
                Text("Valeurs : 3, 5, 2")
                Text("Somme : 10")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUI() {
    MaterialTheme { EcranLanceurDes() }
}
