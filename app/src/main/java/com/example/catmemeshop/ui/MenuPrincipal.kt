package com.example.catmemeshop.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import com.example.catmemeshop.data.model.ProductoCarrito
import com.example.catmemeshop.viewmodel.CarritoViewModel


@Composable

fun MenuPrincipal(
    onCerrarSesion: () -> Unit
) {

    var pantalla by remember { mutableIntStateOf(0) }
    var mostrarCarrito by remember { mutableStateOf(false) }
    var pantallaCompra by remember { mutableStateOf("carrito") }



    val carritoViewModel: CarritoViewModel = viewModel()

    val productosRoom by carritoViewModel.productos.collectAsState()

    val carrito = productosRoom.map { producto ->
        ProductoCarrito(
            id = producto.id,
            nombre = producto.nombre,
            precio = producto.precio,
            imagen = producto.imagen,
            cantidad = producto.cantidad,
            tipo = producto.tipo
        )
    }



    val agregarProducto: (ProductoCarrito) -> Unit = { producto ->
        carritoViewModel.agregarProducto(producto)
    }


    val opciones = listOf(
        "Inicio", "Tienda", "Accesorios", "Mapa"
    )

    val iconos = listOf(
        Icons.Default.Home,
        Icons.Default.ShoppingCart,
        Icons.Default.List,
        Icons.Default.LocationOn
    )

    Scaffold(
        topBar = {
            if (!mostrarCarrito) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF252525))
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.End
                ) {

                    TextButton(
                        onClick = {
                            onCerrarSesion()
                        }
                    ) {
                        Text(
                            text = "CERRAR SESIÓN",
                            color = Color.White
                        )
                    }

                    TextButton(

                        onClick = {
                            pantallaCompra = "carrito"
                            mostrarCarrito = true
                        }

                    ) {
                        Icon(
                            Icons.Default.ShoppingCart,
                            contentDescription = "Carrito",
                            tint = Color.White
                        )

                        Spacer(Modifier.width(6.dp))

                        Text(
                            "Carrito (${carrito.sumOf { it.cantidad }})",
                            color = Color.White
                        )
                    }
                }
            }
        },
        bottomBar = {
            if (!mostrarCarrito) {
                NavigationBar(
                    containerColor = Color(0xFF252525)
                ) {
                    opciones.forEachIndexed { index, nombre ->
                        NavigationBarItem(
                            selected = pantalla == index,
                            onClick = { pantalla = index },
                            icon = {
                                Icon(
                                    imageVector = iconos[index],
                                    contentDescription = nombre
                                )
                            },
                            label = { Text(nombre) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                selectedTextColor = Color.White,
                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.LightGray,
                                indicatorColor = Color.DarkGray
                            )
                        )
                    }
                }
            }
        },
        containerColor = Color(0xFF101010)
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {

            if (mostrarCarrito) {
                when (pantallaCompra) {

                    "carrito" -> {
                        PantallaCarrito(
                            productos = carrito,
                            onPagar = {
                                if (carrito.isNotEmpty()) {
                                    pantallaCompra = "pago"
                                }
                            },
                            onVolver = {
                                mostrarCarrito = false
                                pantalla = 1
                            }
                        )
                    }

                    "pago" -> {
                        PantallaPago(
                            total = carrito.sumOf {
                                it.precio.toLong() * it.cantidad
                            },

                            onConfirmarPago = {
                                carritoViewModel.vaciarCarrito()
                                pantallaCompra = "confirmacion"
                            },
                            onCancelar = {
                                pantallaCompra = "carrito"
                            }
                        )
                    }

                    "confirmacion" -> {
                        PantallaCompraRealizada(
                            onVolverInicio = {
                                mostrarCarrito = false
                                pantallaCompra = "carrito"
                                pantalla = 0
                            }
                        )
                    }
                }
            }
            else {
                when (pantalla) {
                    0 -> PantallaInicio(
                        onEntrarTienda = { pantalla = 1 }
                    )

                    1 -> PantallaTienda(
                        onAgregarCarrito = agregarProducto
                    )

                    2 -> PantallaAccesorios(
                        onAgregarCarrito = agregarProducto
                    )

                    3 -> PantallaMapa()
                }
            }
        }
    }
}



@Composable
fun PantallaTemporal(titulo: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = titulo,
            color = Color.White,
            fontSize = 26.sp
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Próximamente",
            color = Color.LightGray
        )
    }
}
