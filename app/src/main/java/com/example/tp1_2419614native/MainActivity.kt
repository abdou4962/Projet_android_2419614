package com.example.tp1_2419614native
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CarteEcran(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun CarteEcran(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(16),
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        EcranLanceurDes(modifier = Modifier.padding(16.dp))
    }
}


@Composable
fun EcranLanceurDes(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Lanceur de Dés")
        Spacer(modifier = Modifier.height(20.dp))


        Button(onClick = { Log.d("Action", "Bouton lancer cliqué") }) {
            Text("Lancer les dés")
        }
        Spacer(modifier = Modifier.height(20.dp))


        Text("Nombre de dés: 3")
        Spacer(modifier = Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { Log.d("Info", "Choix 1 dé") }) { Text("1") }
            Button(onClick = { Log.d("Info", "Choix 2 dés") }) { Text("2") }
            Button(onClick = { Log.d("Info", "Choix 3 dés") }) { Text("3") }
        }
        Spacer(modifier = Modifier.height(20.dp))


        Text("Nombre de faces: d6")
        Spacer(modifier = Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { Log.d("Info", "Choix 4") }) { Text("4") }
            Button(onClick = { Log.d("Info", "Choix 6") }) { Text("6") }
            Button(onClick = { Log.d("Info", "Choix 8") }) { Text("8") }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text("Tri des résultats: Aucun")
        Spacer(modifier = Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { Log.d("Info", "Tri Croissant") }) { Text("Croissant") }
            Button(onClick = { Log.d("Info", "Tri Décroissant") }) { Text("Décroissant") }
        }
        Spacer(modifier = Modifier.height(20.dp))


        Text("Résultats: 3, 5, 2")
        Spacer(modifier = Modifier.height(10.dp))
        Text("Somme: 10")
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewUI() {
    MaterialTheme {
        CarteEcran()
    }
}