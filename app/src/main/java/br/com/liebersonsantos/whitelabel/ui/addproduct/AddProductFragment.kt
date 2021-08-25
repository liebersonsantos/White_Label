package br.com.liebersonsantos.whitelabel.ui.addproduct

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.liebersonsantos.whitelabel.databinding.AddProductFragmentBinding
import br.com.liebersonsantos.whitelabel.util.CurrencyTextWatcher
import br.com.liebersonsantos.whitelabel.util.PRODUCT_KEY
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductFragment : BottomSheetDialogFragment() {
    private var _binding: AddProductFragmentBinding? = null
    private val binding get() = _binding!!

    private var imageUri: Uri? = null
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){ uri ->
        imageUri = uri
        binding.productImage.setImageURI(imageUri)
    }

    private val viewModel: AddProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddProductFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeVMEvents()
        setListeners()
    }

    private fun observeVMEvents(){
        viewModel.imageUriErrorResId.observe(viewLifecycleOwner){
            binding.productImage.setBackgroundResource(it)
        }

        viewModel.descriptionFieldErrorResId.observe(viewLifecycleOwner){
            binding.inputLayoutDescription.setError(it)
        }

        viewModel.priceFieldErrorResId.observe(viewLifecycleOwner){
            binding.inputLayoutPrice.setError(it)
        }

        viewModel.productCreated.observe(viewLifecycleOwner){ product ->
            findNavController().run {
                previousBackStackEntry?.savedStateHandle?.set(PRODUCT_KEY, product)
                popBackStack()
            }
        }
    }

    private  fun TextInputLayout.setError(stringResId: Int?){
        error = if (stringResId != null) {
            getString(stringResId)
        } else null
    }

    private fun setListeners(){
        binding.productImage.setOnClickListener {
            chooseImage()
        }

        binding.btnAddProduct.setOnClickListener {
            val description = binding.inputDescription.text.toString()
            val price = binding.inputPrice.text.toString()

            viewModel.createProduct(description, price, imageUri)
        }

        binding.inputPrice.run {
            addTextChangedListener(CurrencyTextWatcher(this))
        }
    }

    private fun chooseImage() = getContent.launch("image/*")
}