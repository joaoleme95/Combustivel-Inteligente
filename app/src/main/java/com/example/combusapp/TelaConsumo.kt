package com.example.combusapp

import android.app.Dialog
import android.sax.TextElementListener
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


val customFontFamily = FontFamily(
        Font(R.font.worksans_normal, FontWeight.Normal),
        Font(R.font.worksans_bold, FontWeight.Bold),
        Font(R.font.worksans_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.worksans_medium, FontWeight.Medium)
    )

    @Composable
    fun TelaConsumo(customFontFamily: FontFamily, navController: NavController) {
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
                        text = "Cálculo de consumo",
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
                        DialogExplicacao(onDismiss = { mostrarDialog = false })
                    }
                }
            }
            QuilometragemRodada(customFontFamily)
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuilometragemRodada(customFontFamily: FontFamily) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Quilometragem rodada",
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )

        var text by rememberSaveable { mutableStateOf("") }
        TextField(
            value = text,
            onValueChange = { newText ->
                // Filtrar apenas números
                if (newText.all { it.isDigit() }) {
                    text = newText
                }
            },
            placeholder = { Text("Ex.: 500", fontFamily = customFontFamily) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.regua_cinza),
                    contentDescription = "",
                )
            }
        )
        LitrosGastos(customFontFamily)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LitrosGastos(customFontFamily: FontFamily) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Litros gastos",
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )

        var text by rememberSaveable { mutableStateOf("") }
        TextField(
            value = text,
            onValueChange = { newText ->
                // Filtrar apenas números
                if (newText.all { it.isDigit() }) {
                    text = newText
                }
            },
            placeholder = { Text("Ex.: 30", fontFamily = customFontFamily) },
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
            onClick = { /*onClick()*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)) {
            Text("Calcular", fontFamily = customFontFamily)
        }
    }
}

@Composable
fun DialogExplicacao(onDismiss: () -> Unit) {
    AlertDialog(
        title = {
            Text(text = "Como usar a calculadora?",
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Bold)
        },
        text = {
            Text(text = "Para usar esta calculadora basta colocar a " +
                    "quantidade de quilomêtros percorridos e os litros" +
                    " gastos. Para isso quando completar o tanque zere" +
                    " a quilometragem no painel. Ande por algum tempo" +
                    " e complete o tanque novamente. Coloque na calculadora" +
                    " os valores de quilomêtros na hora do segundo abastecimento" +
                    " e a quantidade de combustível abastecido na segunda vez.",
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
        DialogExplicacao(onDismiss = { /*mostrarDialog = false */})
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
        TelaConsumo(customFontFamily, navController)
    }
}