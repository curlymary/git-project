package com.example.git_project.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.git_project.R
import com.example.git_project.domain.model.Product
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_product.view.*

class CategoryProductAdapter (
    private val onAddProductToBasketClick : (product: Product)  -> Unit,
    private val onProductClick : (product: Product) -> Unit) : RecyclerView.Adapter<CategoryProductAdapter.ViewHolder>() {
    private var products : List<Product> = listOf()

    fun setData(categories: List<Product>){
        this.products = categories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryProductAdapter.ViewHolder {
        Log.d("Adapter", "onCreateViewFolder")
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: CategoryProductAdapter.ViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewFolder position $position")
        holder.bind(products[position])
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(product: Product){
            containerView.productTv.text = product.getName() + " " + product.getPrice() + "p. "
            containerView.addToBasketTv.setOnClickListener{
                onAddProductToBasketClick(product)
            }

            containerView.setOnClickListener{
                onProductClick(product)
            }
        }
    }
}