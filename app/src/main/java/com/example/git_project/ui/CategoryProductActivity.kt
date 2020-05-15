package com.example.git_project.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.git_project.R
import com.example.git_project.data.BasketDaoImpl
import com.example.git_project.domain.model.Product
import com.example.git_project.presenter.CategoryPresenter
import com.example.git_project.presenter.CategoryView
import kotlinx.android.synthetic.main.category_layout.*
import moxy.ktx.moxyPresenter

class CategoryProductActivity: BaseActivity(), CategoryView {
    private val presenter by moxyPresenter {
        CategoryPresenter(intent.extras!!.getLong(CatalogActivity.CATEGORY_ID, -1),
            BasketDaoImpl(sharedPreferences))
    }

    private val adapter = CategoryProductAdapter(
        onAddProductToBasketClick = { product -> addProductToBasket(product)},
        onProductClick = { product -> showProductDetail(product)}
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_layout)

        Log.d(tag, savedInstanceState.toString())
        val savedInt = savedInstanceState?.getInt("SAVE_STATE_INT")
        Log.d(tag, "saved $savedInt")

        btnCategoryGoToToBasket.setOnClickListener{
            val intent = Intent(this, BasketActivity:: class.java)
            startActivity(intent)
        }

        imgArrowCategory.setOnClickListener{
            finish()
        }

        categoryHeader.text = intent.extras!!.getString(CatalogActivity.CATEGORY_NAME, "")

        productRv.layoutManager = LinearLayoutManager(this)
        productRv.adapter = adapter
    }

    override fun onSaveInstanceState(outState : Bundle){
        outState.putInt(CatalogActivity.SAVE_STATE_INT, 89)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode : Int, resultCode: Int, data : Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(CatalogActivity.REQUEST_AUTH == requestCode){
            val isUserAuth = data?.extras?.getBoolean(CatalogActivity.IS_USER_AUTH)
            Log.d(tag, isUserAuth.toString())
        }
    }

    override fun setProducts(list: List<Product>) {
        adapter.setData(list)
    }

    override fun addProductToBasket(product: Product) {
        presenter.addItemToBasket(product)
        Toast.makeText(this, "Блюдо " + product.getName() + " добавлено в корзину", Toast.LENGTH_LONG).show()
    }

    override fun showProductDetail(product: Product) {
        startActivity(Intent(this, DetailedActivity::class.java).apply {
            putExtra(DetailedActivity.PRODUCT_TAG, product)
        })
    }

}

