package com.example.tp1_2419614native
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EcranLanceurDes(modifier = Modifier.padding(innerPadding))
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
        ZoneConfiguration()
        ZoneAction()
        ZoneResultats()
    }
}

@Composable
fun ZoneConfiguration() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Configuration", style = MaterialTheme.typography.titleMedium)
            Text("Nombre de dés")
            ListeDesAvecRecherche()

            val faces = listOf(4, 6, 8, 10, 12, 20)
            var nbFaces by rememberSaveable { mutableStateOf(1f) }
            Text("Nombre de faces : d${faces[nbFaces.toInt()]}")
            Slider(
                value = nbFaces,
                onValueChange = { nbFaces = it },
                valueRange = 0f..(faces.lastIndex).toFloat(),
                steps = faces.size - 2,
                modifier = Modifier.fillMaxWidth(0.7f)
            )
            var tri by rememberSaveable { mutableStateOf("Aucun") }
            Text("Tri des résultats : $tri")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { tri = "Aucun"; Log.d("TRI", "Aucun") }) { Text("Aucun") }
                Button(onClick = { tri = "Croissant"; Log.d("TRI", "Croissant") }) { Text("Croissant") }
                Button(onClick = { tri = "Décroissant"; Log.d("TRI", "Décroissant") }) { Text("Décroissant") }
            }
        }
    }
}

@Composable
fun ZoneAction() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Action", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            Button(onClick = { Log.d("ACTION", "Bouton lancer cliqué") }) {
                Text("Lancer les dés")
            }
        }
    }
}

@Composable
fun ZoneResultats() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Résultats", style = MaterialTheme.typography.titleMedium)
            Text("Valeurs : 3, 5, 2")
            Text("Somme : 10")
        }
    }
}
@Composable
fun ListeDesAvecRecherche(modifier: Modifier = Modifier) {
    var searchCriteria by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.fillMaxWidth()) {
        TextField(
            value = searchCriteria,
            onValueChange = { searchCriteria = it },
            label = { Text("Rechercher un nombre de dés (1–6)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(getDes(searchCriteria)) { nombre ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Nombre de dés : $nombre")
                        Button(onClick = {
                            Log.i("LANCEUR_DES", "Nombre de dés choisi : $nombre")
                        }) {
                            Text("Choisir")
                        }
                    }
                }
            }
        }
    }
}

fun getDes(search: String? = null): List<Int> =
    if (search.isNullOrBlank())
        listOf(1, 2, 3, 4, 5, 6)
    else
        listOf(1, 2, 3, 4, 5, 6).filter { it.toString().contains(search.trim()) }

@Preview(showBackground = true)
@Composable
fun PreviewUI() {
    MaterialTheme {
        EcranLanceurDes()
    }
}