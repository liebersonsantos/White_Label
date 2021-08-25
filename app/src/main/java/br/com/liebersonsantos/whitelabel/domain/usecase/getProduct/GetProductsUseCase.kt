package br.com.liebersonsantos.whitelabel.domain.usecase.getProduct

import br.com.liebersonsantos.whitelabel.domain.model.Product

/**
 * Created by lieberson on 8/10/21.
 * @author lieberson.xsantos@gmail.com
 */
interface GetProductsUseCase {
    suspend operator fun invoke(): List<Product>
}