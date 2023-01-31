package com.project.productinfoapp.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.project.productinfoapp.R
import com.project.productinfoapp.databinding.FragmentGetProductBinding

import com.project.productinfoapp.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetProductFragment : Fragment() , ProductAdapter.ProductsListItemItemListener {

    private var _binding: FragmentGetProductBinding? = null
    private val binding get() = _binding!!
    private val noteViewModel by viewModels<ProductViewModel>()
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGetProductBinding.inflate(inflater, container, false)
        adapter = ProductAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel.getallproducts()
        binding.noteList.layoutManager =
            GridLayoutManager(context, GridLayoutManager.VERTICAL)
        binding.noteList.adapter = adapter

        bindObservers()
    }

    private fun bindObservers() {
        noteViewModel.notesLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    adapter.setItems(ArrayList(it.data))
//                    )

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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickedProductsListItem(ProductsListItemId: String) {
        findNavController().navigate(
            R.id.action_getProductFragment_to_productDetailsFragment2,
            bundleOf("id" to ProductsListItemId)
        )
    }
}