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

data class AccesorioProducto(
    val nombre: String,
    val precio: Int,
    val descripcion: String
)

@Composable

fun PantallaAccesorios(
    onAgregarCarrito: (ProductoCarrito) -> Unit
) {


    val accesorios = listOf(
        AccesorioProducto(
            "Collar de lujo",
            15000,
            "Collar exclusivo para gatos elegantes."
        ),
        AccesorioProducto(
            "Cama premium",
            35000,
            "Cama cómoda de edición especial."
        ),
        AccesorioProducto(
            "Traje ejecutivo",
            25000,
            "Traje formal para gatos empresarios."
        ),
        AccesorioProducto(
            "Lentes exclusivos",
            12000,
            "Lentes de colección para gatos."
        ),
        AccesorioProducto(
            "Corona de colección",
            50000,
            "Corona especial para gatos legendarios."
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
                text = "Accesorios exclusivos",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Complementos para tus gatos",
                color = Color.LightGray,
                fontSize = 15.sp
            )
        }

        items(accesorios) { accesorio ->
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
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = accesorio.nombre,
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(10.dp))

                    Text(
                        text = accesorio.descripcion,
                        color = Color.LightGray,
                        fontSize = 15.sp
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = "$${accesorio.precio} MXN",
                        color = Color.White,
                        fontSize = 19.sp
                    )

                    Spacer(Modifier.height(16.dp))


                    Button(
                        onClick = {
                            onAgregarCarrito(
                                ProductoCarrito(
                                    id = accesorios.indexOf(accesorio) + 101,
                                    nombre = accesorio.nombre,
                                    precio = accesorio.precio,
                                    imagen = null,
                                    cantidad = 1,
                                    tipo = "Accesorio"
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
