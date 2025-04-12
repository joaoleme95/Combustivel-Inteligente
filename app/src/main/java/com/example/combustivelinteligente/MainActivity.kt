package com.example.combustivelinteligente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.combustivelinteligente.R
import com.example.combustivelinteligente.TelaCombustivelVantajoso.TelaCombustivelVantajoso
import com.example.combustivelinteligente.TelaConsumo.TelaConsumo
import com.example.combustivelinteligente.TelaCustoViagem.Apis.TelaCustoViagem
import com.example.combustivelinteligente.ui.theme.CombusAppTheme
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient

class MainActivity : ComponentActivity() {

    val customFontFamily = FontFamily(
        Font(R.font.worksans_normal, FontWeight.Normal),
        Font(R.font.worksans_bold, FontWeight.Bold),
        Font(R.font.worksans_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.worksans_medium, FontWeight.Medium)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Places.initialize(applicationContext, BuildConfig.GOOGLE_API_KEY)
        val placesClient = Places.createClient(this)
        setContent {
            CombusAppTheme {
                val navController = rememberNavController()
                CombusAppNavHost(customFontFamily, navController = navController, placesClient)
            }
        }
    }
}

@Composable
fun CombusAppNavHost(
    customFontFamily: FontFamily,
    navController: NavHostController,
    placesClient: PlacesClient
) {
    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") { CombusAppInicial(customFontFamily, navController) }
        composable("consumo") { TelaConsumo(customFontFamily, navController) }
        composable("custo_viagem") {
            TelaCustoViagem(
                customFontFamily,
                navController,
                placesClient
            )
        }
        composable("combustivel_vantajoso") {
            TelaCombustivelVantajoso(
                customFontFamily,
                navController
            )
        }
    }
}

@Composable
fun CombusAppInicial(customFontFamily: FontFamily, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        // Parte centralizada
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                /*Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Configurações"
                )*/
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Combustível Inteligente",
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 50.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Text(
                text = "Bem-vindo!",
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 18.dp)
            )
            Text(
                text = "Como podemos te ajudar hoje?",
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        OpcoesMenu(customFontFamily, navController)
    }
}

@Composable
fun OpcoesMenu(customFontFamily: FontFamily, navController: NavController) {
    val isDarkTheme = isSystemInDarkTheme()
    val contentColor = if (isDarkTheme) Color.White else Color.Black

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Lista de opções
        val opcoes = listOf(
            Triple("Consumo", R.drawable.bomba_combustivel, "consumo"),
            Triple("Custo da viagem", R.drawable.localizacao, "custo_viagem"),
            Triple("Combustível vantajoso", R.drawable.grafico_dinheiro, "combustivel_vantajoso")
        )

        opcoes.forEach { (titulo, imagem, rota) ->
            Button(
                onClick = { navController.navigate(rota) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Text(
                    text = titulo,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = contentColor
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = imagem),
                    contentDescription = "",
                    modifier = Modifier.size(50.dp),
                    colorFilter = ColorFilter.tint(contentColor) // Aplica cor branca no dark
                )
            }
        }
    }
}



@Preview
@Composable
fun PreviewMain() {
    val customFontFamily = FontFamily(
        Font(R.font.worksans_normal, FontWeight.Normal),
        Font(R.font.worksans_bold, FontWeight.Bold),
        Font(R.font.worksans_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.worksans_medium, FontWeight.Medium)
    )
    Surface {
        val navController = rememberNavController()
        CombusAppInicial(customFontFamily, navController)
    }
}