package com.project.productinfoapp.ui.productdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.productinfoapp.repository.ProductDetailsRepository
import com.project.productinfoapp.repository.ProductListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(private val productdetailsRepository: ProductDetailsRepository) : ViewModel() {

    val notesLiveData get() = productdetailsRepository.productsLiveData
    val statusLiveData get() = productdetailsRepository.statusLiveData


    fun getproductdetails(id:String) {
        viewModelScope.launch {
            productdetailsRepository.getproductdetails(id)
        }
    }
}