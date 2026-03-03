package com.example.weatherapp

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.WeatherAppTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegisterPage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPage(modifier: Modifier = Modifier) {
    var nome by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }
    var repetirSenha by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current
    val activity = context as? Activity

    val habilitarRegistro =
        nome.isNotEmpty() &&
                email.isNotEmpty() &&
                senha.isNotEmpty() &&
                repetirSenha.isNotEmpty() &&
                senha == repetirSenha

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Cadastro",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.size(24.dp))

        OutlinedTextField(
            value = nome,
            label = { Text("Nome do usuário") },
            modifier = Modifier.fillMaxWidth(0.9f),
            onValueChange = { nome = it }
        )

        Spacer(modifier = Modifier.size(16.dp))

        OutlinedTextField(
            value = email,
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth(0.9f),
            onValueChange = { email = it }
        )

        Spacer(modifier = Modifier.size(16.dp))

        OutlinedTextField(
            value = senha,
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth(0.9f),
            onValueChange = { senha = it },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.size(16.dp))

        OutlinedTextField(
            value = repetirSenha,
            label = { Text("Repetir senha") },
            modifier = Modifier.fillMaxWidth(0.9f),
            onValueChange = { repetirSenha = it },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.size(24.dp))

        Row {

            Button(
                onClick = {
                    Toast.makeText(context, "Registro OK!", Toast.LENGTH_LONG).show()
                    activity?.finish()
                },
                enabled = habilitarRegistro
            ) {
                Text("Registrar")
            }

            Spacer(modifier = Modifier.size(16.dp))

            Button(
                onClick = {
                    nome = ""
                    email = ""
                    senha = ""
                    repetirSenha = ""
                }
            ) {
                Text("Limpar")
            }
        }
    }
}
