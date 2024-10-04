package com.example.combustivelinteligente.TelaCombustivelVantajoso

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.combustivelinteligente.R


val customFontFamily = FontFamily(
    Font(R.font.worksans_normal, FontWeight.Normal),
    Font(R.font.worksans_bold, FontWeight.Bold),
    Font(R.font.worksans_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.worksans_medium, FontWeight.Medium)
)

@Composable
fun TelaCombustivelVantajoso(customFontFamily: FontFamily, navController: NavController) {
    var mostrarDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar tela inicial",
                    modifier = Modifier.clickable {
                        navController.navigate("menu") {
                            popUpTo("menu") { inclusive = true }
                        }
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Etanol X Gasolina",
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 50.dp),
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Explicação",
                    modifier = Modifier.clickable {
                        mostrarDialog = true
                    }
                )
                if (mostrarDialog) {
                    DialogExplicacaoCombustivelVantajoso(onDismiss = { mostrarDialog = false })
                }
            }
        }
        PrecoEtanol(customFontFamily)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrecoEtanol(customFontFamily: FontFamily) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Preço do etanol",
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )

        var valorEtanol by rememberSaveable { mutableStateOf("") }
        TextField(
            value = valorEtanol,
            onValueChange = { newText ->
                // Regex para aceitar números com até dois dígitos após o ponto
                val regex = """^\d*\.?\d{0,2}$""".toRegex()
                if (regex.matches(newText)) {
                    valorEtanol = newText
                }
            },
            placeholder = { Text("Ex.: R$ 4,50", fontFamily = customFontFamily) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.bomba_combustivel_cinza),
                    contentDescription = "",
                )
            }
        )
        PrecoGasolina(customFontFamily, valorEtanol)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrecoGasolina(customFontFamily: FontFamily, valorEtanol: String) {
    var chamaCalculo by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Preço da gasolina",
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )

        var valorGasolina by rememberSaveable { mutableStateOf("") }
        TextField(
            value = valorGasolina,
            onValueChange = { newText ->
                // Regex para aceitar números com até dois dígitos após o ponto
                val regex = """^\d*\.?\d{0,2}$""".toRegex()
                if (regex.matches(newText)) {
                    valorGasolina = newText
                }
            },
            placeholder = { Text("Ex.: R$ 6,70", fontFamily = customFontFamily) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.bomba_combustivel_cinza),
                    contentDescription = "",
                )
            }
        )
        Button(
            onClick = { chamaCalculo = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)) {
            Text("Calcular", fontFamily = customFontFamily)
        }
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (chamaCalculo) {
                CalculaCombustivelVantajoso(valorEtanol, valorGasolina, customFontFamily)
            }
        }
    }
}

@Composable
fun DialogExplicacaoCombustivelVantajoso(onDismiss: () -> Unit) {
    AlertDialog(
        title = {
            Text(text = "Como usar a calculadora?",
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Bold)
        },
        text = {
            Text(text = "Para usar esta calculadora basta colocar o valor do etanol" +
                    " e da gasolina nos respectivos lugares. O resultado dará a melhor opção.",
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Medium)
        },
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text("Fechar",
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Bold)
            }
        },
    )
}



@Preview
@Composable
fun PreviewDialog() {
    val customFontFamily = FontFamily(
        Font(R.font.worksans_normal, FontWeight.Normal),
        Font(R.font.worksans_bold, FontWeight.Bold),
        Font(R.font.worksans_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.worksans_medium, FontWeight.Medium)
    )
    Surface {
        DialogExplicacaoCombustivelVantajoso(onDismiss = { /*mostrarDialog = false */})
    }
}

@Preview
@Composable
fun PreviewConsumo() {
    val customFontFamily = FontFamily(
        Font(R.font.worksans_normal, FontWeight.Normal),
        Font(R.font.worksans_bold, FontWeight.Bold),
        Font(R.font.worksans_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.worksans_medium, FontWeight.Medium)
    )
    Surface {
        val navController = rememberNavController()
        TelaCombustivelVantajoso(customFontFamily, navController)
    }
}