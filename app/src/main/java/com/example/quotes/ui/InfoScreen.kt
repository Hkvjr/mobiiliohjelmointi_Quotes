package com.example.quotes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quotes.ui.theme.LightCoral

@Composable
fun InfoScreen(onNavigateBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally // Keskittää sisällön vaakasuunnassa
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tietoa Quotes-sovelluksesta",
            style = MaterialTheme.typography.titleLarge, //isofontti
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp)) //lisää tilaa otsikon ja seuraavan tekstin välille

        Text(
            text = "Tämä sovellus tuo päivittäin inspiraatiota ja iloa hakemalla lainauksia API Ninjas Quotes API:sta",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateBack, //takaisin päänäyttöön
            colors = ButtonDefaults.buttonColors(containerColor = LightCoral)
        ) {
            Text("Takaisin")
        }
    }
}
