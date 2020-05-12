package com.example.git_project.ui

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.git_project.R
import com.example.git_project.data.ViewedProductDaoImpl
import com.example.git_project.domain.model.Product
import com.example.git_project.presenter.CatalogPresenter
import com.example.git_project.presenter.CatalogView
import kotlinx.android.synthetic.main.catalog_layout.*
import moxy.ktx.moxyPresenter

class CatalogActivity : BaseActivity(), CatalogView {

    private val presenter by moxyPresenter {
        CatalogPresenter(ViewedProductDaoImpl(sharedPreferences))
    }
    private val adapter = CategoryAdapter { category ->
        presenter.removeItem(category)
    }

    private val viewedAdapter = ViewedProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.catalog_layout)

        Log.d(tag, savedInstanceState.toString())
        val savedInt = savedInstanceState?.getInt("SAVE_STATE_INT")
        Log.d(tag, "saved $savedInt")

        btnMakeAnOrder.setOnClickListener{
            val intent = Intent(this, CheckoutActivity:: class.java).apply{
                putExtra(PRODUCT_ID, 1000)
            }
            startActivity(intent)
        }

        btnProductDetails.setOnClickListener{
            /*val intent = Intent(this, ProductActivity:: class.java).apply{
                //передали id продукта для просмотра деталей
                putExtra(PRODUCT_ID, 1000)
            }*/
            val intent = Intent(this, BasketActivity:: class.java).apply{
                //передали id продукта для просмотра деталей
                putExtra(PRODUCT_ID, 1000)
            }
            startActivity(intent)
        }

        categoryRv.layoutManager = LinearLayoutManager(this)
        categoryRv.adapter = adapter

        viewedProductsRv.layoutManager = LinearLayoutManager(this)
        viewedProductsRv.adapter = viewedAdapter
    }

    override fun onSaveInstanceState(outState : Bundle){
        outState.putInt(SAVE_STATE_INT, 89)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode : Int, resultCode: Int, data : Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(REQUEST_AUTH == requestCode){
            val isUserAuth = data?.extras?.getBoolean(IS_USER_AUTH)
            Log.d(tag, isUserAuth.toString())
        }
    }

    companion object {
        const val PRODUCT_ID = "PRODUCT_ID"
        const val REQUEST_AUTH : Int = 10
        const val IS_USER_AUTH = "IS_USER_AUTH"
        const val SAVE_STATE_INT = "SAVE_STATE_INT"
    }

    override fun setCategories(list: List<String>) {
        adapter.setData(list)
    }

    override fun removeItem(position: Int) {
        adapter.notifyItemRemoved(position)
    }

    override fun showProductIds(productIds: List<Long>) {
        Toast.makeText(this, productIds.joinToString(","), Toast.LENGTH_LONG).show()
    }

    override fun setViewedProducts(productList: List<Product>) {
        viewedAdapter.setData(productList)
    }
}

val AppCompatActivity.sharedPreferences: SharedPreferences
    get() = getSharedPreferences("data", MODE_PRIVATE)