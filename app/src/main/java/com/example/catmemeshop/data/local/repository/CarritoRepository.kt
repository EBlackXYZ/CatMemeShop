package com.example.catmemeshop.data.local.repository

import com.example.catmemeshop.data.local.ProductoCarritoDao
import com.example.catmemeshop.data.model.ProductoCarritoEntity
import kotlinx.coroutines.flow.Flow

class CarritoRepository(
    private val dao: ProductoCarritoDao
) {

    suspend fun obtenerProductoPorNombreYTipo(
        nombre: String,
        tipo: String
    ): ProductoCarritoEntity? {
        return dao.obtenerProductoPorNombreYTipo(
            nombre,
            tipo
        )
    }

    val productos: Flow<List<ProductoCarritoEntity>> =
        dao.obtenerProductos()

    suspend fun agregarProducto(
        producto: ProductoCarritoEntity
    ) {
        dao.insertarProducto(producto)
    }

    suspend fun actualizarProducto(
        producto: ProductoCarritoEntity
    ) {
        dao.actualizarProducto(producto)
    }

    suspend fun eliminarProducto(
        producto: ProductoCarritoEntity
    ) {
        dao.eliminarProducto(producto)
    }

    suspend fun vaciarCarrito() {
        dao.vaciarCarrito()
    }
}
