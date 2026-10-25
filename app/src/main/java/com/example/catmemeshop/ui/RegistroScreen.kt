package com.example.catmemeshop.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import com.example.catmemeshop.R
import com.example.catmemeshop.coloresCampos


@Composable
fun PantallaRegistro(
    onVolverLogin: () -> Unit
) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmar by remember { mutableStateOf("") }
    val auth = remember { FirebaseAuth.getInstance() }
    val context = LocalContext.current
    var cargando by remember { mutableStateOf(false) }


    val fondo = Color(0xFF101010)
    val gris = Color(0xFF252525)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "CatMemeShop",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Crea tu cuenta",
            fontSize = 16.sp,
            color = Color.LightGray
        )

        Spacer(Modifier.height(24.dp))

        Image(
            painter = painterResource(R.drawable.register),
            contentDescription = "Gato pensativo",
            modifier = Modifier
                .size(170.dp)
                .border(
                    2.dp,
                    Color.White,
                    RoundedCornerShape(16.dp)
                ),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier.fillMaxWidth(),
            colors = coloresCampos()
        )

        Spacer(Modifier.height(14.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = coloresCampos()
        )

        Spacer(Modifier.height(14.dp))

        OutlinedTextField(
            value = confirmar,
            onValueChange = { confirmar = it },
            label = { Text("Confirmar contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = coloresCampos()
        )

        Spacer(Modifier.height(24.dp))


        Button(
            onClick = {
                when {
                    correo.isBlank() || contrasena.isBlank() ||
                            confirmar.isBlank() -> {
                        Toast.makeText(
                            context,
                            "Completa todos los campos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    contrasena != confirmar -> {
                        Toast.makeText(
                            context,
                            "Las contraseñas no coinciden",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    contrasena.length < 6 -> {
                        Toast.makeText(
                            context,
                            "La contraseña debe tener mínimo 6 caracteres",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    !cargando -> {
                        cargando = true

                        auth.createUserWithEmailAndPassword(
                            correo.trim(),
                            contrasena
                        ).addOnCompleteListener { task ->
                            cargando = false

                            if (task.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Cuenta creada correctamente",
                                    Toast.LENGTH_SHORT
                                ).show()

                                auth.signOut()
                                onVolverLogin()
                            } else {
                                Toast.makeText(
                                    context,
                                    task.exception?.localizedMessage
                                        ?: "Error al crear la cuenta",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .border(
                    1.dp,
                    Color.White,
                    RoundedCornerShape(12.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = gris
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "REGISTRARSE",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(12.dp))

        TextButton(onClick = onVolverLogin) {
            Text(
                text = "¿Ya tienes cuenta? Inicia sesión",
                color = Color.White
            )
        }
    }
}
