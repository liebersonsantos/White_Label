package br.com.liebersonsantos.whitelabel.domain.usecase.uploadProductImage

import android.net.Uri
import br.com.liebersonsantos.whitelabel.data.ProductRepository
import javax.inject.Inject

/**
 * Created by lieberson on 8/11/21.
 * @author lieberson.xsantos@gmail.com
 */
class UploadProductImageUseCaseImpl @Inject constructor(private val productRepository: ProductRepository):
    UploadProductImageUseCase {
    override suspend fun invoke(imageUri: Uri): String =
        productRepository.uploadProductImage(imageUri)
}