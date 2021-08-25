package br.com.liebersonsantos.whitelabel.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.fragment.findNavController
import br.com.liebersonsantos.whitelabel.R
import br.com.liebersonsantos.whitelabel.databinding.FragmentProductsBinding
import br.com.liebersonsantos.whitelabel.domain.model.Product
import br.com.liebersonsantos.whitelabel.util.PRODUCT_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductsViewModel by viewModels()
    private val productAdapter = ProductsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        setRecyclerView()
        observeVMEvents()
        observeNavBackStack()

        getProducts()
    }

    private fun observeVMEvents(){
        viewModel.productsData.observe(viewLifecycleOwner){ products ->
            binding.swipeProducts.isRefreshing = false
            productAdapter.submitList(products)
        }

        viewModel.addButtonVisibility.observe(viewLifecycleOwner){ visibility ->
            binding.fabAdd.visibility = visibility
        }

    }

    private fun setRecyclerView(){
        binding.productsRecycler.run {
            setHasFixedSize(true)
            adapter = productAdapter
        }
    }

    private fun setListeners(){
        with(binding){
            swipeProducts.setOnRefreshListener {
                getProducts()
            }
            fabAdd.setOnClickListener{
                findNavController().navigate(R.id.action_productsFragment_to_addProductFragment)
            }
        }

    }

    private fun getProducts() {
        viewModel.getProducts()
    }

    private fun observeNavBackStack(){
        findNavController().run {
            val navBackStackEntry = getBackStackEntry(R.id.productsFragment)
            val savedStateHandle = navBackStackEntry.savedStateHandle
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME && savedStateHandle.contains(PRODUCT_KEY)){
                    val product = savedStateHandle.get<Product>(PRODUCT_KEY)
                    val oldList = productAdapter.currentList
                    val newList = oldList.toMutableList().apply {
                        add(product)
                    }
                    productAdapter.submitList(newList)
                    binding.productsRecycler.smoothScrollToPosition(newList.size - 1)
                    savedStateHandle.remove<Product>(PRODUCT_KEY)
                }
            }

            navBackStackEntry.lifecycle.addObserver(observer)

            viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_DESTROY) {
                    navBackStackEntry.lifecycle.removeObserver(observer)
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}