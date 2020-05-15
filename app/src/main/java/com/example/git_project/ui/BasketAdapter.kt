package com.example.git_project.ui

import com.example.git_project.domain.model.Product
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.git_project.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_basket.view.*

class BasketAdapter(
    private val onDeleteClick : (product: Product) -> Unit,
    private val onProductClick : (product: Product) -> Unit
) : RecyclerView.Adapter<BasketAdapter.ViewHolder>() {

    private var products : List<Product> = listOf()

    fun setData(products: List<Product>){
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketAdapter.ViewHolder {
        Log.d("Adapter", "onCreateViewFolder")
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_basket, parent, false))
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewFolder position $position")
        holder.bind(products[position])
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(product: Product){
            containerView.basketTv.text = product.getName() + " " + product.getPrice() + "p. " + "(" + product.getSalePercent() + "%)"
            containerView.deleteBasketTv.setOnClickListener{
                onDeleteClick(product)
            }

            containerView.setOnClickListener{
                onProductClick(product)
            }
        }
    }
}