package com.example.catmemeshop.data.model

data class ProductoCarrito(
    val id: Int,
    val nombre: String,
    val precio: Int,
    val imagen: Int? = null,
    val cantidad: Int = 1,
    val tipo: String
)
