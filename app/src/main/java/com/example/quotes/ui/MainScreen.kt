package com.example.quotes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotes.R
import com.example.quotes.ui.theme.LightCoral
import com.example.quotes.viewmodel.QuoteViewModel
import coil.compose.AsyncImage

@Composable
fun MainScreen(viewModel: QuoteViewModel = viewModel(), onNavigateToInfo: () -> Unit) {
    val quote by viewModel.quote.observeAsState() //hakee lainauksen ViewModelista
    var isError by remember { mutableStateOf(false) } // Seuraa, tapahtuuko virhe

    Column(
        modifier = Modifier
            .fillMaxSize() //täyttää kokonäytön
            .padding(16.dp) //sisäisen marginaalin lisäys
            .background(MaterialTheme.colorScheme.background) //taustaväri
    ) {
        // Boxi ja sen asetukset
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp), //boxin korkeus
            contentAlignment = Alignment.TopEnd // kohdistaa sisällön yläkulmaan
        ) {
            Image(
                painter = painterResource(id = R.drawable.info_logo), //infokuvan lataaminen resussista
                contentDescription = "Info Icon",
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp)
                    .clickable(onClick = onNavigateToInfo) //siirtyminen info-näyttöön
            )
        }

        // "Tervetulotekstin" asetukset
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // Varmistaa, että tämä osa vie ylimääräistä tilaa
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Siirrä ylöspäin
        ) {
            Text(
                text = "Elämä on täynnä viisaita sanoja ja inspiroivia ajatuksia. \nLöydä jokapäiväistä voimaa ja inspiraatiota lainauksista.",
                style = MaterialTheme.typography.titleLarge,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )

            // Kukka (coil-kirjaston käyttö)
            if (isError) {
                // Näytä virheilmoitus, jos kuvassa on virhe
                Text(
                    text = "Kuvan lataaminen epäonnistui.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                AsyncImage(
                    model = "https://openclipart.org/image/800px/250824", // Linkki Lotus Flower -clip-art-kuvaan
                    contentDescription = "Lotus Flower clip-art kuva",
                    modifier = Modifier
                        .size(100.dp) // Voit muuttaa kokoa tarpeen mukaan
                        .padding(vertical = 16.dp), // Lisää tilaa ylös ja alas
                    placeholder = painterResource(id = R.drawable.placeholder_vector),  // Vaihtoehtoinen kuva latauksen aikana
                    onError = {
                        isError = true // Päivitä tilaksi virhetilanne
                    }
                )
            }
        }


        // Kehys lainaukselle
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .border(width = 2.dp, color = LightCoral) // sama väri kuin nappulalla
                .padding(16.dp) // Kehyksen sisätila
        ) {
            quote?.let { //tarkistaa, onko lainaus saatavilla
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "\"${it.quote}\"", //näyttää lainauksen
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "- ${it.author}", //näyttää lainauksen kirjoittajan
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            } ?: run { //jos lainausta ei saa
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        }

        // painikkeen keskitys
        Spacer(modifier = Modifier.height(16.dp)) // Lisää tilaa lainauksen ja painikkeen väliin
        Button(
            onClick = { viewModel.fetchQuote() },
            colors = ButtonDefaults.buttonColors(containerColor = LightCoral), // Nappulan väri
            modifier = Modifier.align(Alignment.CenterHorizontally) // Keskittää painikkeen
        ) {
            Text("Hae uusi lainaus", color = Color.White)
        }
    }
}