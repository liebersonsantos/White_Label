package br.com.liebersonsantos.whitelabel.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.liebersonsantos.whitelabel.databinding.ItemProductBinding
import br.com.liebersonsantos.whitelabel.domain.model.Product
import br.com.liebersonsantos.whitelabel.util.toCurrency
import com.bumptech.glide.Glide

/**
 * Created by lieberson on 8/13/21.
 * @author lieberson.xsantos@gmail.com
 */
class ProductsAdapter : ListAdapter<Product, ProductsAdapter.ProductsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductsViewHolder(private val itemBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {


        fun bind(product: Product) {
            itemBinding.run {
                Glide.with(itemView)
                    .load(product.imageUrl)
                    .fitCenter()
                    .into(productImage)


                textDescription.text = product.description
                textPrice.text = product.price.toCurrency()
            }

        }

        companion object {
            fun create(parent: ViewGroup): ProductsViewHolder {
                val itemBinding =
                    ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ProductsViewHolder(itemBinding)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }

        }
    }

}