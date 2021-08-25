package br.com.liebersonsantos.whitelabel.data

import android.net.Uri
import br.com.liebersonsantos.whitelabel.domain.model.Product

/**
 * Created by lieberson on 8/10/21.
 * @author lieberson.xsantos@gmail.com
 */

interface ProductDataSource {
    suspend fun getProducts(): List<Product>
    suspend fun uploadProductImage(imageUri: Uri): String
    suspend fun createProduct(product: Product): Product
}