package com.example.catmemeshop.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

@Composable
fun PantallaPago(
    total: Long,
    onConfirmarPago: () -> Unit,
    onCancelar: () -> Unit
) {
    var metodoPago by remember {
        mutableStateOf("Tarjeta (simulación)")
    }

    val formato = NumberFormat.getCurrencyInstance(
        Locale.forLanguageTag("es-MX")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "CatMemeShop",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Resumen de pago",
            color = Color.LightGray,
            fontSize = 20.sp
        )

        Spacer(Modifier.height(30.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp,
                    Color.White,
                    RoundedCornerShape(14.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF252525)
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Total a pagar",
                    color = Color.LightGray,
                    fontSize = 17.sp
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = formato.format(total),
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(Modifier.height(28.dp))

        Text(
            text = "Método de pago",
            color = Color.White,
            fontSize = 18.sp
        )

        Spacer(Modifier.height(12.dp))

        listOf(
            "Tarjeta (simulación)",
            "Efectivo (simulación)"
        ).forEach { metodo ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = metodoPago == metodo,
                    onClick = { metodoPago = metodo }
                )

                Text(
                    text = metodo,
                    color = Color.White
                )
            }
        }

        Spacer(Modifier.height(30.dp))

        Button(
            onClick = onConfirmarPago,
            enabled = total > 0,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray
            )
        ) {
            Text("CONFIRMAR PAGO")
        }

        Spacer(Modifier.height(12.dp))

        OutlinedButton(
            onClick = onCancelar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "CANCELAR",
                color = Color.White
            )
        }

        Spacer(Modifier.height(18.dp))

        Text(
            text = "Pago de demostración. No se realizará ningún cobro.",
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}
