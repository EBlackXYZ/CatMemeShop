package com.example.catmemeshop.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.catmemeshop.data.local.CatMemeDatabase
import com.example.catmemeshop.data.local.repository.CarritoRepository
import com.example.catmemeshop.data.model.ProductoCarrito
import com.example.catmemeshop.data.model.ProductoCarritoEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CarritoViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val database =
        CatMemeDatabase.obtenerBaseDatos(application)

    private val repository =
        CarritoRepository(database.productoCarritoDao())

    val productos = repository.productos.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )


    fun agregarProducto(producto: ProductoCarrito) {
        viewModelScope.launch {
            val existente = repository.obtenerProductoPorNombreYTipo(
                producto.nombre,
                producto.tipo
            )

            if (existente != null) {
                repository.actualizarProducto(
                    existente.copy(
                        cantidad = existente.cantidad + producto.cantidad
                    )
                )
            } else {
                repository.agregarProducto(
                    ProductoCarritoEntity(
                        nombre = producto.nombre,
                        precio = producto.precio,
                        imagen = producto.imagen,
                        cantidad = producto.cantidad,
                        tipo = producto.tipo
                    )
                )
            }
        }
    }


    fun vaciarCarrito() {
        viewModelScope.launch {
            repository.vaciarCarrito()
        }
    }
}