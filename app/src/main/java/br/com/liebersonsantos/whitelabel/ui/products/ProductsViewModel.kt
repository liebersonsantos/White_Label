package br.com.liebersonsantos.whitelabel.ui.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.liebersonsantos.whitelabel.config.Config
import br.com.liebersonsantos.whitelabel.domain.model.Product
import br.com.liebersonsantos.whitelabel.domain.usecase.getProduct.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by lieberson on 8/13/21.
 * @author lieberson.xsantos@gmail.com
 */
@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    config: Config
) : ViewModel() {

    private val _productsData = MutableLiveData<List<Product>>()
    val productsData: LiveData<List<Product>>
        get() = _productsData

    private val _addButtonVisibility = MutableLiveData(config.addButtonVisibility)
    val addButtonVisibility: LiveData<Int>
        get() = _addButtonVisibility

    fun getProducts() = viewModelScope.launch {
        try {
            val products = getProductsUseCase()
            _productsData.value = products

        } catch (ex: Exception) {
            Log.d("ProductsViewModel", ex.toString())
        }
    }

}