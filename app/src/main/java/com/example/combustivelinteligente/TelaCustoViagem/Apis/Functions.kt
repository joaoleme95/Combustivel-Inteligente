package com.example.combustivelinteligente.TelaCustoViagem.Apis

import android.util.Log
import com.example.combustivelinteligente.DirectionsResponse
import com.example.combustivelinteligente.RetrofitClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getDistanciaEntreEnderecos(origin: String, destination: String, apiKey: String) {
    val call = RetrofitClient.instance.getDistancia(origin, destination, apiKey)

    call.enqueue(object : Callback<DirectionsResponse> {
        override fun onResponse(call: Call<DirectionsResponse>, response: Response<DirectionsResponse>) {
            if (response.isSuccessful) {
                val directions = response.body()
                val distance = directions?.routes?.get(0)?.legs?.get(0)?.distance?.text
                Log.i("testeApi", "Distância: $distance")
            } else {
                Log.e("testeApi", "Erro na resposta da API: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
            Log.e("testeApi", "Erro na requisição: ${t.message}")
        }
    })
}



fun extractDistance(jsonResponse: String): String {
    val jsonObject = JSONObject(jsonResponse)
    val routes = jsonObject.getJSONArray("routes")
    val legs = routes.getJSONObject(0).getJSONArray("legs")
    val distance = legs.getJSONObject(0).getJSONObject("distance")
    return distance.getString("text")
}