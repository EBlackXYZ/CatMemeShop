package com.example.catmemeshop.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catmemeshop.data.model.ProductoCarrito
import java.text.NumberFormat
import java.util.Locale

@Composable
fun PantallaCarrito(
    productos: List<ProductoCarrito>,
    onPagar: () -> Unit,
    onVolver: () -> Unit
) {
    val total = productos.sumOf {
        it.precio.toLong() * it.cantidad
    }

    val formato = NumberFormat.getCurrencyInstance(
        Locale.forLanguageTag("es-MX")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
            .padding(16.dp)
    ) {
        Text(
            text = "Mi carrito",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(Modifier.height(20.dp))

        if (productos.isEmpty()) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tu carrito está vacío",
                    color = Color.LightGray,
                    fontSize = 18.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(productos) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                1.dp,
                                Color.White,
                                RoundedCornerShape(12.dp)
                            ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF252525)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = producto.nombre,
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(Modifier.height(8.dp))

                            Text(
                                text = "Cantidad: ${producto.cantidad}",
                                color = Color.LightGray
                            )

                            Text(
                                text = formato.format(
                                    producto.precio.toLong() *
                                            producto.cantidad
                                ),
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }

        HorizontalDivider(color = Color.Gray)

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Total: ${formato.format(total)}",
            color = Color.White,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onPagar,
            enabled = productos.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray
            )
        ) {
            Text("CONTINUAR AL PAGO")
        }

        Spacer(Modifier.height(10.dp))

        OutlinedButton(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "VOLVER A LA TIENDA",
                color = Color.White
            )
        }
    }
}
