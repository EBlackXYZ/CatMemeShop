package com.example.catmemeshop.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catmemeshop.R
import com.example.catmemeshop.data.model.ProductoCarrito

data class GatoProducto(
    val nombre: String,
    val precio: Int,
    val imagen: Int
)

@Composable

fun PantallaTienda(
    onAgregarCarrito: (ProductoCarrito) -> Unit
) {


    val gatos = listOf(
        GatoProducto(
            "Gato Empresario",
            150000,
            R.drawable.gato_empresario
        ),
        GatoProducto(
            "Gato Ejecutivo",
            125000,
            R.drawable.gato_ejecutivo
        ),
        GatoProducto(
            "Gato Electrico",
            250000,
            R.drawable.gato_electrico
        ),
        GatoProducto(
            "Gato chill",
            100000,
            R.drawable.gato_chill
        ),
        GatoProducto(
            "Gato TontoPalo",
            300000,
            R.drawable.gato_tontopalo
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Text(
                text = "Gatos exclusivos",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Colección especial CatMemeShop",
                fontSize = 15.sp,
                color = Color.LightGray
            )
        }

        items(gatos) { gato ->
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
                ),
                shape = RoundedCornerShape(14.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(gato.imagen),
                        contentDescription = gato.nombre,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(Modifier.height(14.dp))

                    Text(
                        text = gato.nombre,
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "$${gato.precio} MXN",
                        color = Color.LightGray,
                        fontSize = 19.sp
                    )

                    Spacer(Modifier.height(14.dp))


                    Button(
                        onClick = {
                            onAgregarCarrito(
                                ProductoCarrito(
                                    id = gatos.indexOf(gato) + 1,
                                    nombre = gato.nombre,
                                    precio = gato.precio,
                                    imagen = gato.imagen,
                                    cantidad = 1,
                                    tipo = "Gato"
                                )
                            )
                        },

                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.DarkGray
                        )
                    ) {
                        Text(
                            text = "AGREGAR AL CARRITO",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
