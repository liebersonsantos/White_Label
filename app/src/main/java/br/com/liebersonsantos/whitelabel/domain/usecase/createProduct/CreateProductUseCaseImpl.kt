package br.com.liebersonsantos.whitelabel.domain.usecase.createProduct

import android.net.Uri
import br.com.liebersonsantos.whitelabel.data.ProductRepository
import br.com.liebersonsantos.whitelabel.domain.model.Product
import br.com.liebersonsantos.whitelabel.domain.usecase.uploadProductImage.UploadProductImageUseCase
import java.lang.Exception
import java.util.*
import javax.inject.Inject

/**
 * Created by lieberson on 8/11/21.
 * @author lieberson.xsantos@gmail.com
 */

class CreateProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
    private val uploadProductImageUseCase: UploadProductImageUseCase
) : CreateProductUseCase {

    override suspend fun invoke(description: String, price: Double, imageUri: Uri): Product =
        try {
            val imageUrl = uploadProductImageUseCase(imageUri)
            val product = Product(UUID.randomUUID().toString(), description, price, imageUrl)
            productRepository.createProduct(product)
        } catch (ex: Exception) {
            throw ex
        }
}
