package com.example.combusapp.TelaCombustivelVantajoso

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
import com.example.combusapp.R
import java.util.Locale


@Composable
fun CalculaCombustivelVantajoso(valorEtanol: String, valorGasolina: String, customFontFamily: FontFamily) {

    val valorEtanolDouble = valorEtanol.toDoubleOrNull() ?: 0.0
    val valorGasolinaDouble = valorGasolina.toDoubleOrNull() ?: 1.0

    val resultado = if (valorGasolinaDouble != 0.0) {
        valorEtanolDouble / valorGasolinaDouble
    } else {
        0.0
    }

    val resultadoFormatado = String.format(Locale.US, "%.2f", resultado)
    var combusivelVantajoso = "Etanol"
    var textoResultado = "abaixo"

    if (resultado > 0.73) {
        combusivelVantajoso = "Gasolina"
        textoResultado = "acima"
    }

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
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        fontFamily = customFontFamily,
                        fontWeight = FontWeight.Medium
                    )
                    ) {
                        append("O resultado deu $resultadoFormatado, que é $textoResultado de 0,73, " +
                                "portanto o combustível vantajoso é:\n")
                    }
                    withStyle(style = SpanStyle(
                        fontFamily = customFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    ) {
                        append("\n$combusivelVantajoso")
                    }
                },
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
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
        CalculaCombustivelVantajoso("5.60", "6.55", customFontFamily)
    }
}