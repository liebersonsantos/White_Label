package br.com.liebersonsantos.whitelabel.domain.usecase.getProduct

import br.com.liebersonsantos.whitelabel.data.ProductRepository
import br.com.liebersonsantos.whitelabel.domain.model.Product
import javax.inject.Inject

/**
 * Created by lieberson on 8/11/21.
 * @author lieberson.xsantos@gmail.com
 */
class GetProductUseCaseImpl @Inject constructor(private val productRepository: ProductRepository): GetProductsUseCase {
    override suspend fun invoke(): List<Product> =
        productRepository.getProducts()
}