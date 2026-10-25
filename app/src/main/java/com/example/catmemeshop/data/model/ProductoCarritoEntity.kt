package com.example.catmemeshop.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos_carrito")
data class ProductoCarritoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val precio: Int,
    val imagen: Int?,
    val cantidad: Int,
    val tipo: String
)
