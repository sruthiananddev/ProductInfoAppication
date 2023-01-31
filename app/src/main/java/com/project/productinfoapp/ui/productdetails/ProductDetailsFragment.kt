package com.project.productinfoapp.ui.productdetails

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.project.productinfoapp.data.model.ProductDetails.ProductDetails
import com.project.productinfoapp.databinding.FragmentProductDetailsBinding
import com.project.productinfoapp.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductDetailsFragment : Fragment(){
private var url:String?=null
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ProductDetailsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("id")?.let { viewModel.getproductdetails(it) }


        bindObservers()
    }

    private fun bindObservers() {
        viewModel.notesLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    bindCharacter(it.data!!)


                    println("it.....${it.data!!.toString()}")
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        })
    }

    private fun bindCharacter(product: ProductDetails) {
        binding.txtprodname.text = product.name
        binding.txtprodid.text = product.id
        binding.proddesc.text = product.description
        binding.prodheadername.text = product.name
        url=product.image;
        val arr = url!!.split(",").toTypedArray()
        println("image....."+arr.contentToString())

        val decodedString: ByteArray = Base64.decode(arr[1].toString(), Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        binding.productimage.setImageBitmap(decodedByte)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}









