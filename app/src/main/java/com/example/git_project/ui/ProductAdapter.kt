package com.example.git_project.ui

import com.example.git_project.Product
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.git_project.R
import kotlinx.android.synthetic.main.item_basket.view.*

class ProductAdapter(
    private val onDeleteClick : (product: Product) -> Unit) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var products : List<Product> = listOf()

    fun setData(products: List<Product>){
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        Log.d("Adapter", "onCreateViewFolder")
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_basket, parent, false))
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewFolder position $position")
        holder.bind(products[position])
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bind(product: Product){
            itemView.basketTv.text = product.getName() + " " + product.getPrice() + "p. " + "(" + product.getSalePercent() + "%)"
            itemView.deleteBasketTv.setOnClickListener{
                onDeleteClick(product)
            }
        }
    }
}