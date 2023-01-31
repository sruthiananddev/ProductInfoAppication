package com.project.productinfoapp.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.project.productinfoapp.repository.ProductListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductListRepository) : ViewModel() {

    val notesLiveData get() = productRepository.productsLiveData
    //val statusLiveData get() = productRepository.statusLiveData



    fun getallproducts() {
        viewModelScope.launch {
            productRepository.getproducts()
        }
    }






}