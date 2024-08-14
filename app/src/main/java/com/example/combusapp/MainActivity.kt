package com.example.combusapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.combusapp.TelaCombustivelVantajoso.TelaCombustivelVantajoso
import com.example.combusapp.TelaConsumo.TelaConsumo

class MainActivity : ComponentActivity() {

    val customFontFamily = FontFamily(
        Font(R.font.worksans_normal, FontWeight.Normal),
        Font(R.font.worksans_bold, FontWeight.Bold),
        Font(R.font.worksans_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.worksans_medium, FontWeight.Medium)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CombusAppNavHost(customFontFamily, navController = navController)
        }
    }
}

@Composable
fun CombusAppNavHost(customFontFamily: FontFamily, navController: NavHostController) {
    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") { CombusAppInicial(customFontFamily, navController) }
        composable("consumo") { TelaConsumo(customFontFamily, navController) }
        composable("custo_viagem") { /*CustoViagemScreen()*/ }
        composable("combustivel_vantajoso") { TelaCombustivelVantajoso(customFontFamily,
            navController) }
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
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Configurações"
                )
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Primeira opção
        Button(
            onClick = { navController.navigate("consumo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Text(
                text = "Consumo",
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.bomba_combustivel),
                contentDescription = "",
                modifier = Modifier.size(50.dp)
            )
        }

        // Segunda opção
        Button(
            onClick = { /* Ação para a segunda opção */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Text(
                text = "Custo da viagem",
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.localizacao),
                contentDescription = "",
                modifier = Modifier.size(50.dp)
            )
        }

        // Terceira opção
        Button(
            onClick = { navController.navigate("combustivel_vantajoso") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Text(
                text = "Combustível vantajoso",
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.grafico_dinheiro),
                contentDescription = "",
                modifier = Modifier.size(50.dp)
            )
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