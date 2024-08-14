package com.example.combusapp.TelaConsumo

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
import androidx.compose.ui.unit.dp
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
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.combusapp.R

@Composable
fun CalculaConsumo(quilometragem: String, litros: String, customFontFamily: FontFamily) {

    val quilometragemInt = quilometragem.toIntOrNull() ?: 0
    val litrosInt = litros.toIntOrNull() ?: 1
    val consumo = if (litrosInt != 0) quilometragemInt / litrosInt else 0

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .size(width = 340.dp, height = 200.dp)
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
                        append("Seu veículo faz em média:\n")
                    }
                    withStyle(style = SpanStyle(
                        fontFamily = customFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )) {
                        append("\n$consumo Km/l")
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
        CalculaConsumo("500", "50", customFontFamily)
    }
}