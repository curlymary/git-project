package com.example.git_project.ui

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.git_project.R
import com.example.git_project.data.BasketDaoImpl
import com.example.git_project.domain.model.Category
import com.example.git_project.domain.model.Product
import com.example.git_project.presenter.CatalogPresenter
import com.example.git_project.presenter.CatalogView
import kotlinx.android.synthetic.main.catalog_layout.*
import moxy.ktx.moxyPresenter

class CatalogActivity : BaseActivity(), CatalogView {
    private val presenter by moxyPresenter {
        CatalogPresenter(BasketDaoImpl(sharedPreferences))
    }
    private val adapter = CatalogAdapter  (
        onCategoryClick = { category -> showCategoryProducts(category)}    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.catalog_layout)

        Log.d(tag, savedInstanceState.toString())
        val savedInt = savedInstanceState?.getInt("SAVE_STATE_INT")
        Log.d(tag, "saved $savedInt")

        btnMakeAnOrder.setOnClickListener{
            val intent = Intent(this, CheckoutActivity:: class.java)
            startActivity(intent)
        }

        btnCatalogGoToBasket.setOnClickListener{
            val intent = Intent(this, BasketActivity:: class.java)
            startActivity(intent)
        }

        categoryRv.layoutManager = LinearLayoutManager(this)
        categoryRv.adapter = adapter
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
        const val CATEGORY_ID = "CATEGORY_ID"
        const val CATEGORY_NAME = "CATEGORY_NAME"
        const val REQUEST_AUTH : Int = 10
        const val IS_USER_AUTH = "IS_USER_AUTH"
        const val SAVE_STATE_INT = "SAVE_STATE_INT"
    }

    override fun setCategories(list: List<Category>) {
        adapter.setData(list)
    }

    override fun showProductIds(productIds: List<Long>) {
        //Toast.makeText(this, productIds.joinToString(","), Toast.LENGTH_LONG).show()
    }

    override fun showCategoryProducts(category: Category) {
        startActivity(Intent(this, CategoryProductActivity::class.java).apply {
            putExtra(CATEGORY_ID, category.getId())
            putExtra(CATEGORY_NAME, category.getName())
        })
    }
}

val AppCompatActivity.sharedPreferences: SharedPreferences
    get() = getSharedPreferences("data", MODE_PRIVATE)