package com.example.jetpackcomposetraining

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.jetpackcomposetraining.adapters.CardModel
import com.example.jetpackcomposetraining.ui.theme.JetpackComposeTrainingTheme
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxWidth(.5f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .fillMaxWidth()
                        .fillMaxHeight(.5f),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Hello!")
                    Text(text = "Hello!", Modifier, fontSize = 30.sp, color = Color.Red)
                    Text(text = "Hello!")
                }

                Row(
                    modifier = Modifier
                        .background(Color.Blue)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Bye!")
                    Text(text = "Bye!", Modifier, fontSize = 30.sp, color = Color.Green)
                    Text(text = "Bye!")
                }

            }
            requestCardData("45717360")

        }
    }

    private fun requestCardData(bin: String) {
        val url = "https://lookup.binlist.net/" +
                bin
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(
            Request.Method.GET,
            url,
            { result ->
                parseCardData(result)
            },
            { error ->
                Log.d("MyLog", "Error: $error")
            }
        )
        queue.add(request)
    }

    private fun parseCardData(result: String) {
        val mainObject = JSONObject(result)
        val item = CardModel(
            getCardData(mainObject, "number", "length", true),
            getCardData(mainObject, "number", "luhn", true),
            getCardData(mainObject, "", "scheme", false),
            getCardData(mainObject, "", "type", false),
            getCardData(mainObject, "", "brand", false),
            getCardData(mainObject, "", "prepaid", false),
            getCardData(mainObject, "country", "numeric", true),
            getCardData(mainObject, "country", "alpha2", true),
            getCardData(mainObject, "country", "name", true),
            getCardData(mainObject, "country", "emoji", true),
            getCardData(mainObject, "country", "currency", true),
            getCardData(mainObject, "country", "latitude", true),
            getCardData(mainObject, "country", "longitude", true),
            getCardData(mainObject, "bank", "name", true),
            getCardData(mainObject, "bank", "url", true),
            getCardData(mainObject, "bank", "phone", true),
            getCardData(mainObject, "bank", "city", true),
        )

        setContent {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(1.dp, Color.Green)),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .border(BorderStroke(1.dp, Color.Red)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "Bank: ${item.bankName}", fontSize = 18.sp)
                    Text(text = "City: ${item.bankCity}", fontSize = 18.sp)
                    Text(text = "County: ${item.countryName}", fontSize = 18.sp)
                    Text(text = "Scheme: ${item.scheme}", fontSize = 18.sp)
                    Text(text = "Type: ${item.type}", fontSize = 18.sp)
                    Text(text = "Brand: ${item.brand}", fontSize = 18.sp)
                    Text(text = "Currency: ${item.countryCurrency}", fontSize = 18.sp)
                    Text(text = "Url: ${item.bankUrl}", fontSize = 18.sp)
                }
            }
        }

    }

    private fun getCardData(
        mainObject: JSONObject,
        jsonObject: String,
        jsonString: String,
        getJSONobj: Boolean
    ): String {
        val validatedData: String
        when (getJSONobj) {
            true -> {
                validatedData = when {
                    mainObject.has(jsonObject) -> {
                        when {
                            mainObject.getJSONObject(jsonObject).has(jsonString) -> {
                                mainObject.getJSONObject(jsonObject).getString(jsonString)
                            }
                            else -> {
                                ""
                            }
                        }
                    }
                    else -> {
                        ""
                    }
                }
            }
            false -> {
                validatedData = when {
                    mainObject.has(jsonString) -> {
                        mainObject.getString(jsonString)
                    }
                    else -> {
                        ""
                    }
                }
            }
        }

        return validatedData
    }

}


