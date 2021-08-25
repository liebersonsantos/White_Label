package br.com.liebersonsantos.whitelabel.domain.usecase.createProduct

import android.net.Uri
import br.com.liebersonsantos.whitelabel.domain.model.Product

/**
 * Created by lieberson on 8/10/21.
 * @author lieberson.xsantos@gmail.com
 */
interface CreateProductUseCase {
    suspend operator fun invoke(description: String, price: Double, imageUri: Uri): Product
}