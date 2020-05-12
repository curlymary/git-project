package com.example.git_project.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.git_project.domain.model.Product
import com.example.git_project.R
import com.example.git_project.presenter.BasketPresenter
import com.example.git_project.presenter.BasketView
import com.example.git_project.ui.DetailedActivity.Companion.PRODUCT_TAG
import kotlinx.android.synthetic.main.basket_layout.*

class BasketActivity : BaseActivity(),
    BasketView {

    private val presenter = BasketPresenter()
    private val adapter = ProductAdapter(
        onDeleteClick = { product ->  presenter.removeItem(product)},
        onProductClick = { product -> showProductDetail(product)}
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.basket_layout)

        //получили id продукта для добавления его в корзину
        val productID = intent.extras?.getInt(CatalogActivity.PRODUCT_ID, -1)
        Log.d(tag, productID.toString())

        btnMakeAnOrder.setOnClickListener{
            val intent = Intent(this, CheckoutActivity:: class.java).apply{
                //передали id продукта для просмотра деталей
                putExtra(CatalogActivity.PRODUCT_ID, 1000)
            }
            startActivity(intent)
        }

        btnBasketToCatalog.setOnClickListener{
            val intent = Intent(this, CatalogActivity:: class.java)
            startActivity(intent)
        }

        imgArrowBasket.setOnClickListener{
            finish()
        }

        btnAddProduct.setOnClickListener{
            presenter.addItem()
        }

        basketRv.layoutManager = LinearLayoutManager(this)
        basketRv.adapter = adapter
        presenter.attachView(this)
        presenter.setData()
    }

    override fun setProducts(list: List<Product>) {
        adapter.setData(list)
    }

    override fun addItem(position: Int) {
        adapter.notifyItemRemoved(position)
    }

    override fun removeItem(position: Int) {
        adapter.notifyItemRemoved(position)
    }

    override fun showProductDetail(product: Product) {
        startActivity(Intent(this, DetailedActivity::class.java).apply {
            putExtra(PRODUCT_TAG, product)
        })
    }
}
