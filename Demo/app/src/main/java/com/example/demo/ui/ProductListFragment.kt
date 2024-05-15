package com.example.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.demo.R
import com.example.demo.data.Product
import com.example.demo.data.ProductVM
import com.example.demo.databinding.FragmentProductListBinding
import com.example.demo.util.ProductAdapter
import com.example.demo.util.infoDialog
import kotlinx.coroutines.launch

class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding
    private val nav by lazy { findNavController() }

    private val vm: ProductVM by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProductListBinding.inflate(inflater, container, false)

        // -----------------------------------------------------------------------------------------

        binding.btnRestore.setOnClickListener { vm.restore() }

        // -----------------------------------------------------------------------------------------

        val adapter = ProductAdapter { h, p ->
            h.binding.root.setOnClickListener { detail(p.id) }
        }
        binding.rv.adapter = adapter
        binding.rv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        vm.getLiveData().observe(viewLifecycleOwner) {
            binding.txtCount.text = "${it.size} Record(s)"
            adapter.submitList(it)
        }

        // -----------------------------------------------------------------------------------------

        return binding.root
    }

    private fun detail(productId: String) {
        nav.navigate(R.id.productDetailFragment, bundleOf(
            "productId" to productId
        ))
    }

}