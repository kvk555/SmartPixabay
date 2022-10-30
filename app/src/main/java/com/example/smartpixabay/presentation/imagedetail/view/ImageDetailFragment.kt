package com.example.smartpixabay.presentation.imagedetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.smartpixabay.R
import com.example.smartpixabay.databinding.FragmentImageDetailBinding
import com.example.smartpixabay.presentation.home.view.adapter.setImageUrl
import com.example.smartpixabay.presentation.imagedetail.viewmodel.ImageDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageDetailFragment : Fragment() {

    private lateinit var binding: FragmentImageDetailBinding
    private val args: ImageDetailFragmentArgs by navArgs()

    private val viewModel: ImageDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentImageDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = getString(R.string.image_details)

        viewModel.image.observe(viewLifecycleOwner, Observer {
            setImageUrl(binding.image, it.webformatURL)
            binding.imageSize.text = resources.getString(R.string.size, it.imageSize)
            binding.imageType.text = resources.getString(R.string.type, it.type)
            binding.imageTags.text = resources.getString(R.string.tags, it.tags)
            binding.userName.text = resources.getString(R.string.user_name, it.user)
            binding.views.text = resources.getString(R.string.views, it.views)
            binding.likes.text = resources.getString(R.string.likes, it.likes)
            binding.comments.text = resources.getString(R.string.comments, it.comments)
            binding.downloads.text = resources.getString(R.string.downloads, it.downloads)
        })

        viewModel.getImageById(args.imageId)
    }
}
