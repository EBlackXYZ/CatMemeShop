
package com.example.catmemeshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import com.example.catmemeshop.ui.MenuPrincipal
import com.example.catmemeshop.ui.PantallaRegistro


private val Fondo = Color(0xFF101010)
private val Gris = Color(0xFF252525)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var mostrarRegistro by remember {
                mutableStateOf(false)
            }

            MaterialTheme {
                if (mostrarRegistro) {
                    PantallaRegistro(
                        onVolverLogin = {
                            mostrarRegistro = false
                        }
                    )
                } else {
                    PantallaLogin(
                        onIrRegistro = {
                            mostrarRegistro = true
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun PantallaLogin(
    onIrRegistro: () -> Unit
) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    val auth = remember { FirebaseAuth.getInstance() }
    val context = LocalContext.current
    var cargando by remember { mutableStateOf(false) }
    var sesionIniciada by remember { mutableStateOf(false) }


    if (sesionIniciada) {
        MenuPrincipal(
            onCerrarSesion = {
                FirebaseAuth.getInstance().signOut()
                sesionIniciada = false
            }
        )
        return
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Fondo)
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

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Bienvenido a nuestra tienda",
            fontSize = 15.sp,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(28.dp))

        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "Gato naranja con café",
            modifier = Modifier
                .size(190.dp)
                .border(
                    2.dp,
                    Color.White,
                    RoundedCornerShape(16.dp)
                ),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(30.dp))

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

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = coloresCampos()
        )

        Spacer(modifier = Modifier.height(26.dp))

        Button(
            onClick = {
                if (correo.isBlank() || contrasena.isBlank()) {
                    Toast.makeText(
                        context,
                        "Completa todos los campos",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (!cargando) {
                    cargando = true

                    auth.signInWithEmailAndPassword(
                        correo.trim(),
                        contrasena
                    ).addOnCompleteListener { task ->
                        cargando = false

                        if (task.isSuccessful) {
                            sesionIniciada = true

                            Toast.makeText(
                                context,
                                "Bienvenido a CatMemeShop",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                "Correo o contraseña incorrectos",
                                Toast.LENGTH_LONG
                            ).show()
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
                containerColor = Gris
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "INICIAR SESIÓN",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(18.dp))
        TextButton(
            onClick = onIrRegistro
        ) {
            Text(
                text = "¿No tienes cuenta? Regístrate",
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun coloresCampos() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = Color.White,
    unfocusedTextColor = Color.White,
    focusedBorderColor = Color.White,
    unfocusedBorderColor = Color.Gray,
    focusedLabelColor = Color.White,
    unfocusedLabelColor = Color.LightGray,
    cursorColor = Color.White,
    focusedContainerColor = Gris,
    unfocusedContainerColor = Gris
)
