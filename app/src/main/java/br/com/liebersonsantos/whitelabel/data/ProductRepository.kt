package br.com.liebersonsantos.whitelabel.data

import android.net.Uri
import br.com.liebersonsantos.whitelabel.domain.model.Product
import javax.inject.Inject

/**
 * Created by lieberson on 8/10/21.
 * @author lieberson.xsantos@gmail.com
 */
class ProductRepository @Inject constructor(private val dataSource: ProductDataSource) {
    suspend fun getProducts(): List<Product> = dataSource.getProducts()
    suspend fun uploadProductImage(imageUri: Uri): String = dataSource.uploadProductImage(imageUri)
    suspend fun createProduct(product: Product): Product = dataSource.createProduct(product)
}