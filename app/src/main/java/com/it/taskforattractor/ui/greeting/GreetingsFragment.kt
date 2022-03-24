package com.it.taskforattractor.ui.greeting

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.network.result.Status
import com.it.taskforattractor.R
import com.it.taskforattractor.databinding.GreetingsFragmentBinding
import com.it.taskforattractor.databinding.ProgressBarBinding
import com.it.taskforattractor.ui.base.BaseFragment
import com.it.taskforattractor.ui.greeting.adapter.GreetingAdapter
import com.it.taskforattractor.util.loadImage
import com.it.taskforattractor.util.showMessage
import org.koin.android.ext.android.inject

class GreetingsFragment : BaseFragment<GreetingsFragmentBinding>() {

    private val adapter = GreetingAdapter()

    private val viewModel: GreetingsViewModel by inject()

    private lateinit var proDialog: ProgressDialog

    override fun setupView() {
        setupPD()
        viewModel.getUser()
        viewModel.user.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.LOADING -> {
                    proDialog.show()
                }

                Status.ERROR -> {
                    proDialog.hide()
                    context?.showMessage(result.message)
                }

                Status.SUCCESS -> {
                    proDialog.hide()
                    binding.apply {
                        result.data?.user?.let {
                            name.text = it.first_name
                            secondName.text = it.second_name
                            imgUsrPhoto.loadImage(it.photo)
                            education.text = when (it.education) {
                                1 -> getString(R.string.high_school)
                                2 -> getString(R.string.bachelor)
                                3 -> getString(R.string.master)
                                else -> getString(R.string.doctoral)
                            }
                            adapter.list = it.company
                        }
                    }
                }
            }
            binding.btnGallery.setOnClickListener {
                findNavController().navigate(R.id.galleryFragment)
            }
        }
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            object : DividerItemDecoration(requireContext(), VERTICAL) {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    val itemPosition = parent.getChildAdapterPosition(view)
                    // hide divider for the last child
                    if (itemPosition == state.itemCount.minus(1)) {
                        outRect.setEmpty()
                    } else {
                        super.getItemOffsets(outRect, view, parent, state)
                    }
                }
            }.also {
                it.drawable?.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    ), PorterDuff.Mode.SRC_IN
                )
                addItemDecoration(it)
            }
        }
        binding.recycler.adapter = adapter
    }

    private fun setupPD() {
        proDialog = ProgressDialog.show(context, null, null, false, false)
        proDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        proDialog.setContentView(ProgressBarBinding.inflate(layoutInflater).root)
    }
}