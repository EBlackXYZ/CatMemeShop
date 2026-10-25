package com.example.catmemeshop.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.catmemeshop.data.model.ProductoCarritoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoCarritoDao {

    @Query("""
    SELECT * FROM productos_carrito
    WHERE nombre = :nombre AND tipo = :tipo
    LIMIT 1
""")
    suspend fun obtenerProductoPorNombreYTipo(
        nombre: String,
        tipo: String
    ): ProductoCarritoEntity?

    @Query("SELECT * FROM productos_carrito")
    fun obtenerProductos(): Flow<List<ProductoCarritoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarProducto(
        producto: ProductoCarritoEntity
    )

    @Update
    suspend fun actualizarProducto(
        producto: ProductoCarritoEntity
    )

    @Delete
    suspend fun eliminarProducto(
        producto: ProductoCarritoEntity
    )

    @Query("DELETE FROM productos_carrito")
    suspend fun vaciarCarrito()
}
