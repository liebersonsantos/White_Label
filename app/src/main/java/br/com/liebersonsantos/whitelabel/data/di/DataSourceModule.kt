package br.com.liebersonsantos.whitelabel.data.di

import br.com.liebersonsantos.whitelabel.data.FirebaseProductDataSource
import br.com.liebersonsantos.whitelabel.data.ProductDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by lieberson on 8/12/21.
 * @author lieberson.xsantos@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Singleton
    @Binds
    fun bindProductDataSource(dataSource: FirebaseProductDataSource): ProductDataSource
}