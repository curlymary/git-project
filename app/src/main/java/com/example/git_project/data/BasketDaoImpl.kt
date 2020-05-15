package com.example.git_project.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.git_project.domain.BasketDao

class BasketDaoImpl(
    private val sharedPreferences: SharedPreferences
) : BasketDao {

    private var savedProductIds: List<Long>
        get() = sharedPreferences.getString(PRODUCT_TAG, null)
            ?.split(",")
            ?.mapNotNull { it.toLongOrNull() } ?: emptyList()
        set(value) {
            sharedPreferences.edit {
                putString(PRODUCT_TAG, value.joinToString(","))
            }
        }

    override fun addProduct(productId: Long) {
        val productIds: List<Long> = savedProductIds
        val newProductIds = mutableListOf<Long>().apply {
            add(productId)
            addAll(productIds.filter { it != productId })
        }
        savedProductIds = newProductIds
    }

    override fun getAllProducts(): List<Long> {
        return savedProductIds
    }

    override fun deleteProduct(productId: Long) {
        val productIds: List<Long> = savedProductIds
        val newProductIds = mutableListOf<Long>().apply {
            remove(productId)
            addAll(productIds.filter { it != productId })
        }
        savedProductIds = newProductIds
    }

    companion object {
        private const val PRODUCT_TAG = "PRODUCT_TAG"
    }
}