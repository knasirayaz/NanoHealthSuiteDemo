package com.knasirayaz.nanohealthsuitedemo.presentation.allproducts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.knasirayaz.nanohealthsuitedemo.databinding.RowProductBinding
import com.knasirayaz.nanohealthsuitedemo.domain.common.BindingAdapters
import com.knasirayaz.nanohealthsuitedemo.domain.common.BindingAdapters.loadImage
import com.knasirayaz.nanohealthsuitedemo.domain.model.ProductsResponse
import com.knasirayaz.nanohealthsuitedemo.R
import com.knasirayaz.nanohealthsuitedemo.domain.model.ProductsResponseItem

class ProductsAdapter (var mList: ArrayList<ProductsResponseItem> = ArrayList(), var callback : (id : Int) -> Unit) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            RowProductBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return mList.size
    }

    //the class is holding the list view
    inner class ViewHolder(private val binding: RowProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val model: ProductsResponseItem = mList[position]
            binding.ivProductImage.loadImage(model.image, R.mipmap.ic_launcher_foreground)
            binding.tvProductTitle.text = model.title
            binding.tvProductDesc.text = model.description
            binding.tvPrice.text = "${model.price} AED"
            binding.ratingbar.rating = model.rating.rate.toFloat()

            binding.root.setOnClickListener {
                callback.invoke(model.id)
            }
            binding.executePendingBindings()
        }
    }
}



