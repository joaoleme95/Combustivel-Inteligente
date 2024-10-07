package com.example.combustivelinteligente.TelaCustoViagem.Apis

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.combustivelinteligente.R

@Composable
fun CalculaCustoViagem(
    consumo: String,
    valorCombustivel: String,
    distancia: String,
    customFontFamily: FontFamily
) {
    val controleDoTeclado = LocalSoftwareKeyboardController.current
    controleDoTeclado?.hide()

    val consumoInt = consumo.toDoubleOrNull() ?: 0.0
    val valorCombustivelInt = valorCombustivel.toDoubleOrNull() ?: 0.0
    val distanciaInt = distancia.toDoubleOrNull() ?: 0.0
    val distanciaKm = distanciaInt / 1000
    val gastoViagem = (distanciaKm / consumoInt) * valorCombustivelInt

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .size(width = 440.dp, height = 300.dp)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (distancia == "0") {
                Text(
                    text = "Erro ao calcular distância",
                )
            } else {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontFamily = customFontFamily,
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append("Quilometragem do trajeto:\n")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontFamily = customFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        ) {
                            append(String.format("\n%.0f Km\n", distanciaKm))
                        }
                        withStyle(
                            style = SpanStyle(
                                fontFamily = customFontFamily,
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append("\nA viagem custará aproximadamente:\n")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontFamily = customFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        ) {
                            append(String.format("\nR$ %.2f", gastoViagem).replace(".", ","))
                        }
                    },
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewConsumoResultado() {
    val customFontFamily = FontFamily(
        Font(R.font.worksans_normal, FontWeight.Normal),
        Font(R.font.worksans_bold, FontWeight.Bold),
        Font(R.font.worksans_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.worksans_medium, FontWeight.Medium)
    )
    Surface {
        CalculaCustoViagem("7.8", "3.40", "104.566", customFontFamily)
    }
}