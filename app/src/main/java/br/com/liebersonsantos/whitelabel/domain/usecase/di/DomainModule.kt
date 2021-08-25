package br.com.liebersonsantos.whitelabel.domain.usecase.di

import br.com.liebersonsantos.whitelabel.domain.usecase.createProduct.CreateProductUseCase
import br.com.liebersonsantos.whitelabel.domain.usecase.createProduct.CreateProductUseCaseImpl
import br.com.liebersonsantos.whitelabel.domain.usecase.getProduct.GetProductUseCaseImpl
import br.com.liebersonsantos.whitelabel.domain.usecase.getProduct.GetProductsUseCase
import br.com.liebersonsantos.whitelabel.domain.usecase.uploadProductImage.UploadProductImageUseCase
import br.com.liebersonsantos.whitelabel.domain.usecase.uploadProductImage.UploadProductImageUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by lieberson on 8/12/21.
 * @author lieberson.xsantos@gmail.com
 */
@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {

    @Binds
    fun bindCreateProductUseCase(useCase: CreateProductUseCaseImpl): CreateProductUseCase

    @Binds
    fun bindGetProductsUseCase(useCase: GetProductUseCaseImpl): GetProductsUseCase

    @Binds
    fun bindUploadProductImageUseCase(useCase: UploadProductImageUseCaseImpl): UploadProductImageUseCase
}