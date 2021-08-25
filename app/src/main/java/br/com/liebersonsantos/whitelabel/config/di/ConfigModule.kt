package br.com.liebersonsantos.whitelabel.config.di

import br.com.liebersonsantos.whitelabel.config.Config
import br.com.liebersonsantos.whitelabel.config.ConfigImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by lieberson on 8/13/21.
 * @author lieberson.xsantos@gmail.com
 */
@Module
@InstallIn(ViewModelComponent::class)
interface ConfigModule {

    @Binds
    fun bindConfigImpl(config: ConfigImpl): Config
}