package com.example.jetpackcomposetraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposetraining.ui.theme.JetpackComposeTrainingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier
                .background(Color.Gray)
                .fillMaxWidth(.5f)
                .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
                ) {
                Row(modifier = Modifier
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

                Row(modifier = Modifier
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

        }
    }
}


