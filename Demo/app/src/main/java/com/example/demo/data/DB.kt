package com.example.demo.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update

// Entity ------------------------------------------------------------------------------------------

@Entity
data class Product(
    @PrimaryKey
    var id: String = "",
    var name: String = "",
    var price: Double = 0.0,
    var photo: ByteArray = ByteArray(0),
)

// DAO (Data Access Object) ------------------------------------------------------------------------

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getLiveData(): LiveData<List<Product>>

    @Query("SELECT * FROM product")
    suspend fun getAll(): List<Product>

    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun get(id: String): Product?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(p: Product)

    @Update
    suspend fun update(p: Product)

    @Delete
    suspend fun delete(p: Product)

    @Query("DELETE FROM product")
    suspend fun deleteAll()
}

// Database ----------------------------------------------------------------------------------------

@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false
)
abstract class DB : RoomDatabase() {
    abstract val productDao: ProductDao

    companion object {
        @Volatile
        private var db: DB? = null

        @Synchronized
        fun get(context: Context): DB {
            db = db ?: Room
                .databaseBuilder(context, DB::class.java, "database.db")
                .build()
            return db!!
        }
    }
}
