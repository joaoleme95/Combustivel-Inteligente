package com.example.combustivelinteligente.TelaCustoViagem.Apis

import android.util.Log
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
import com.example.combustivelinteligente.DirectionsResponse
import com.example.combustivelinteligente.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


val customFontFamily = FontFamily(
    Font(R.font.worksans_normal, FontWeight.Normal),
    Font(R.font.worksans_bold, FontWeight.Bold),
    Font(R.font.worksans_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.worksans_medium, FontWeight.Medium)
)
var resetarDados = false


@Composable
fun TelaCustoViagem(customFontFamily: FontFamily, navController: NavController) {
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
                    text = "Custo de viagem",
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
                    DialogExplicacaoCustoViagem(onDismiss = { mostrarDialog = false })
                }
            }
        }
        EnderecoSaida(customFontFamily)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnderecoSaida(customFontFamily: FontFamily) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Endereço de saída",
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )

        var enderecoSaida by rememberSaveable { mutableStateOf("") }
        TextField(
            value = enderecoSaida,
            onValueChange = { newText ->
                enderecoSaida = newText
            },
            placeholder = { Text("", fontFamily = customFontFamily) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            singleLine = true,
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.regua_cinza),
                    contentDescription = "",
                )
            }
        )
        if (resetarDados) enderecoSaida = ""
        EnderecoDestino(customFontFamily, enderecoSaida)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnderecoDestino(customFontFamily: FontFamily, enderecoSaida: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Destino",
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )

        var enderecoDestino by rememberSaveable { mutableStateOf("") }
        TextField(
            value = enderecoDestino,
            onValueChange = { newText ->
                enderecoDestino = newText
            },
            placeholder = { Text("", fontFamily = customFontFamily) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            singleLine = true,
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.bomba_combustivel_cinza),
                    contentDescription = "",
                )
            }
        )
        if (resetarDados) enderecoDestino = ""
        ConsumoCarro(customFontFamily, enderecoSaida, enderecoDestino)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsumoCarro(customFontFamily: FontFamily, enderecoSaida: String, enderecoDestino: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Consumo do veículo em Km/L",
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )

        var consumoAutomovel by rememberSaveable { mutableStateOf("") }
        TextField(
            value = consumoAutomovel,
            onValueChange = { newText ->
                val regex = """^\d*\.?\d{0,2}$""".toRegex()
                if (regex.matches(newText)) {
                    consumoAutomovel = newText
                }
            },
            placeholder = { Text("Ex.: 12,5", fontFamily = customFontFamily) },
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
        if (resetarDados) consumoAutomovel = ""
        ValorCombustivel(customFontFamily, consumoAutomovel, enderecoSaida, enderecoDestino)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValorCombustivel(
    customFontFamily: FontFamily,
    consumoAutomovel: String,
    enderecoSaida: String,
    enderecoDestino: String
) {
    var telaCusto by remember { mutableStateOf(false) } // Controla qual tela exibir
    var chamaCalculo by remember { mutableStateOf(false) }
    var distancia by remember { mutableStateOf("") } // Para armazenar a distância
    var valorCombustivel by rememberSaveable { mutableStateOf("") }

    if (telaCusto) {
        // Tela de resultado de cálculo
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CalculaCustoViagem(consumoAutomovel, valorCombustivel, distancia, customFontFamily)

            // Botão para refazer o cálculo
            Button(
                onClick = {
                    telaCusto = false // Volta para a tela inicial
                    chamaCalculo = false
                    resetarDados = true
                    distancia = "" // Resetando a distância para nova tentativa
                    valorCombustivel = "" // Limpar valor do combustível
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Text("Refazer Cálculo", fontFamily = customFontFamily)
            }
        }
    } else {
        // Tela inicial para inserir dados
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Valor pago no combustível",
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )

            TextField(
                value = valorCombustivel,
                onValueChange = { newText ->
                    val regex = """^\d*\.?\d{0,2}$""".toRegex() // Limita a 2 casas decimais
                    if (regex.matches(newText)) {
                        valorCombustivel = newText
                    }
                },
                placeholder = { Text("Ex.: 6,70", fontFamily = customFontFamily) },
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
                    .padding(vertical = 12.dp)
            ) {
                Text("Calcular", fontFamily = customFontFamily)
            }

            // Apenas chamar a API se o botão foi pressionado
            if (chamaCalculo) {
                Log.i("testeApi", "Entrou no bloco 'chamaCalculo'")

                // Chamando a função para obter a distância
                RetrofitClient.instance.getDistancia(enderecoSaida, enderecoDestino, "AIzaSyDjXjLFnIMapGpUjNlUgL3qRu59UujLWGM")
                    .enqueue(object : Callback<DirectionsResponse> {
                        override fun onResponse(call: Call<DirectionsResponse>, response: Response<DirectionsResponse>) {
                            if (response.isSuccessful) {
                                val directions = response.body()
                                distancia = directions?.routes?.get(0)?.legs?.get(0)?.distance?.value.toString()

                                if (distancia.isNotEmpty()) {
                                    Log.i("testeApi", "Distância recebida da API: $distancia")
                                    telaCusto = true // Muda para a tela de custo
                                } else {
                                    Log.e("testeApi", "A distância não pôde ser extraída")
                                }
                            } else {
                                Log.e("testeApi", "Erro na resposta da API: ${response.code()}")
                            }
                        }

                        override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
                            Log.e("testeApi", "Erro na requisição: ${t.message}")
                        }
                    })

                // Resetando o flag
                chamaCalculo = false
            }
        }
    }
}

@Composable
fun DialogExplicacaoCustoViagem(onDismiss: () -> Unit) {
    AlertDialog(
        title = {
            Text(text = "Como usar a calculadora?",
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Bold)
        },
        text = {
            Text(text = "Para saber o custo total de combustível de uma viagem," +
                    " basta colocar os endereços de origem e destino," +
                    " o rendimento do carro na estrada em km/l e o valor do combustível em questão.",
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
        DialogExplicacaoCustoViagem(onDismiss = { /*mostrarDialog = false */})
    }
}




@Preview
@Composable
fun PreviewCustoViagem() {
    val customFontFamily = FontFamily(
        Font(R.font.worksans_normal, FontWeight.Normal),
        Font(R.font.worksans_bold, FontWeight.Bold),
        Font(R.font.worksans_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.worksans_medium, FontWeight.Medium)
    )
    Surface {
        val navController = rememberNavController()
        TelaCustoViagem(customFontFamily, navController)
    }
}
/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustoViagemScreen(customFontFamily: androidx.compose.ui.text.font.FontFamily, navController: NavHostController) {
    var origin by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column {
        TextField(
            value = origin,
            onValueChange = { origin = it },
            label = { Text("Origem") }
        )
        TextField(
            value = destination,
            onValueChange = { destination = it },
            label = { Text("Destino") }
        )
        Button(onClick = {
            // Chama a função de cálculo de distância
            val response = getDistanceBetweenLocations(origin, destination, "AIzaSyDjXjLFnIMapGpUjNlUgL3qRu59UujLWGM")
            if (response != null) {
                result = extractDistance(response)
            }
        }) {
            Text("Calcular distância")
        }
        if (result.isNotEmpty()) {
            Text("Distância: $result")
        }
    }
}*/
