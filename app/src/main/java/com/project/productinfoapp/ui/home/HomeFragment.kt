package com.project.productinfoapp.ui.home

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.project.productinfoapp.R
import com.project.productinfoapp.data.model.Products.ProductsHome
import com.project.productinfoapp.databinding.FragmentHomeBinding
import com.project.productinfoapp.ui.login.AuthActivity

import com.project.productinfoapp.utils.NetworkResult
import com.project.productinfoapp.utils.TokenManager
import com.project.productinfoapp.utils.logout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeAdapter.ProductsHomeItemItemListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val noteViewModel by viewModels<NoteViewModel>()
    private lateinit var adapter: HomeAdapter
    @Inject
    lateinit var userPreferences: TokenManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        adapter = HomeAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel.getAllNotes()
        binding.noteList.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.noteList.adapter = adapter

        binding.txtusername.text="Welcome "+userPreferences.getusername()
        val arr = userPreferences.getuserimage()!!.split(",").toTypedArray()
        println("image....."+arr.contentToString())
        //url="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\""
//        Glide.with(binding.root)
//            .load(product.image)
//            .transform(CircleCrop())
//            .into(binding.productimage)

        val decodedString: ByteArray = Base64.decode(arr[1].toString(), Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        binding.imageView1.setImageBitmap(decodedByte)
        binding.logout.setOnClickListener {
          //println("ghhhhhhhhhhhhhhhhhhhh")


            logout()



        }


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

    private fun onNoteClicked(noteResponse: ProductsHome){
//        val bundle = Bundle()
//        bundle.putString("note", Gson().toJson(noteResponse))
//        findNavController().navigate(R.id.action_mainFragment_to_noteFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }
    override fun onClickedProductsHomeItem(ProductsHomeItemId: String) {
        Toast.makeText(context,ProductsHomeItemId, Toast.LENGTH_LONG).show()
        if(ProductsHomeItemId.contentEquals("Products")) {
            findNavController().navigate(
                R.id.action_homeFragment_to_getProductFragment

            )
        }
    }

}



