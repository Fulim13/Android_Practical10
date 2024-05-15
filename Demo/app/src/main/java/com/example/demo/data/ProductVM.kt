package com.example.demo.data

import android.app.Application
import android.graphics.BitmapFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo.R
import com.example.demo.util.toByteArray
import kotlinx.coroutines.launch

class ProductVM (val app: Application) : AndroidViewModel(app) {
    private val db = DB.get(app)

    fun getLiveData() = db.productDao.getLiveData()

    suspend fun getAll() = db.productDao.getAll()
    suspend fun get(id: String) = db.productDao.get(id)

    fun insert(p: Product) = viewModelScope.launch { db.productDao.insert(p) }
    fun update(p: Product) = viewModelScope.launch { db.productDao.update(p) }
    fun delete(p: Product) = viewModelScope.launch { db.productDao.delete(p) }
    fun deleteAll()        = viewModelScope.launch { db.productDao.deleteAll() }

    // ---------------------------------------------------------------------------------------------

    fun restore() {
        // NOTE: Extension function --> Bitmap.toByteArray()
        fun getByteArray(res: Int) = BitmapFactory.decodeResource(app.resources, res).toByteArray()

        insert(Product("P001", "Apple"   , 1.00, getByteArray(R.raw.apple)))
        insert(Product("P002", "Banana"  , 2.00, getByteArray(R.raw.banana)))
        insert(Product("P003", "Cherries", 3.00, getByteArray(R.raw.cherries)))
    }

}