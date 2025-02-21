package com.example.lab51

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab51.ui.theme.Lab51Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab51Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting() {
    var cost by remember {
        mutableStateOf("")
    }

    val curenncys = listOf(
        Curenncy("Доллар", 75),
        Curenncy("Евро", 90),
        Curenncy("Фунт", 120)
    )
    val (selectedCur, setSelectedCur) = remember { mutableStateOf(curenncys[0]) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(all = 25.dp),
    ) {
        Column {
            Text(text = "Цена")
            TextField(
                value = cost,
                onValueChange = { text -> cost = text.filter { it.isDigit() || it == '.' } },
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 5.dp, bottom = 30.dp)
            )
        }
        Column (modifier = Modifier.padding(bottom = 10.dp)) {
            Text(
                text = "Выберите валюту:",
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )
            curenncys.forEach { c ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = c == selectedCur, onClick = { setSelectedCur(c) })
                    Text(text = c.text + " - " + c.cost)

                }
            }
        }

        Column(modifier = Modifier.background(color = Color.Gray).padding(all = 10.dp).fillMaxWidth(1f)) {
            Text(
                text = "Итог в рублях :",
                modifier = Modifier.padding(bottom = 10.dp)
            )
            if (cost.toDoubleOrNull() != null) {
                Text(text = (cost.toDouble() * selectedCur.cost).toString())
            } else {
                Text(text = "0")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab51Theme {
        Greeting()
    }
}