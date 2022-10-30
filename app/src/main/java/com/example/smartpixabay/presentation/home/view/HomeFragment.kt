package com.example.smartpixabay.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.smartpixabay.R
import com.example.smartpixabay.databinding.FragmentHomeBinding
import com.example.smartpixabay.presentation.home.view.adapter.HomeImageAdapter
import com.example.smartpixabay.presentation.home.view.adapter.ImageClick
import com.example.smartpixabay.presentation.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var imageAdapter: HomeImageAdapter? = null

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        imageAdapter = HomeImageAdapter(ImageClick {
            val action = HomeFragmentDirections.goToDetails(it.id)
            findNavController().navigate(action)
        })

        binding.imageRecycler.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = imageAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = getString(R.string.home)

        viewModel.imagesList.observe(
            viewLifecycleOwner, Observer { images ->
                images?.apply {
                    binding.imageRecycler.visibility = View.VISIBLE
                    imageAdapter?.setItems(images)
                }
            })

        viewModel.eventNetworkError.observe(
            viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
                if (isNetworkError) onNetworkError()
            })
    }

    private fun onNetworkError() {
        Toast.makeText(activity, getString(R.string.network_error), Toast.LENGTH_LONG).show()
    }
}
