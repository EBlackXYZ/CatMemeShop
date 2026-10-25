package com.example.catmemeshop.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.catmemeshop.data.model.ProductoCarritoEntity

@Database(
    entities = [ProductoCarritoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CatMemeDatabase : RoomDatabase() {

    abstract fun productoCarritoDao(): ProductoCarritoDao

    companion object {

        @Volatile
        private var INSTANCE: CatMemeDatabase? = null

        fun obtenerBaseDatos(context: Context): CatMemeDatabase {
            return INSTANCE ?: synchronized(this) {

                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    CatMemeDatabase::class.java,
                    "catmeme_database"
                ).build()

                INSTANCE = instancia
                instancia
            }
        }
    }
}
