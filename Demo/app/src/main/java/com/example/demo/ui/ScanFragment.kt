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
import com.example.demo.R
import com.example.demo.data.ProductVM
import com.example.demo.databinding.FragmentScanBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.launch

class ScanFragment : Fragment() {

    private lateinit var binding: FragmentScanBinding
    private val nav by lazy { findNavController() }

    private val vm: ProductVM by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentScanBinding.inflate(inflater, container, false)

        // -----------------------------------------------------------------------------------------

        binding.btnScan.setOnClickListener { scanQR() }

        // -----------------------------------------------------------------------------------------

        return binding.root
    }

    private fun scanQR() {
        // TODO(3): Launch embedded activity to scan QR
        val options = ScanOptions()
            .setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            .setPrompt("Scan QR Code\n")
            .setBeepEnabled(true)
        getResult.launch(options)
    }

    // TODO(4): Handle scan QR result
    private val getResult = registerForActivityResult(ScanContract()) {
        if (it.contents == null) {
            binding.txtContent.text = ""
        } else {
            binding.txtContent.text = it.contents
            // TODO
            detail(it.contents)
        }
    }

    private fun detail(productId: String) {
        // TODO(5): Navigate to product detail if it is a valid product id
        lifecycleScope.launch{
            val p = vm.get(productId)
            if (p != null){
                nav.navigate(R.id.productDetailFragment, bundleOf(
                    "productId" to productId
                ))
            }
        }
    }

}








