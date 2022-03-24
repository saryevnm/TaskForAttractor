package com.it.taskforattractor.ui.gallery

import androidx.recyclerview.widget.LinearLayoutManager
import com.it.taskforattractor.databinding.GalleryFragmentBinding
import com.it.taskforattractor.ui.base.BaseFragment
import com.it.taskforattractor.ui.gallery.adapter.GalleryAdapter
import org.koin.android.ext.android.inject

class GalleryFragment : BaseFragment<GalleryFragmentBinding>() {

    private lateinit var adapter: GalleryAdapter

    private val viewModel: GalleryViewModel by inject()

    override fun setupView() {
        adapter = GalleryAdapter()
        viewModel.setUpLauncher(requireActivity().activityResultRegistry)
        viewModel.getPictures {
            adapter.list = it
        }
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter
    }
}