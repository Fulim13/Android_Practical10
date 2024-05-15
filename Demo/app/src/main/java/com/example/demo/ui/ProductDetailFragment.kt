package com.example.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.demo.data.ProductVM
import com.example.demo.databinding.FragmentProductDetailBinding
import com.example.demo.util.setImageByteArray
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.coroutines.launch

class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private val nav by lazy { findNavController() }

    private val vm: ProductVM by activityViewModels()

    private val productId by lazy { arguments!!.getString("productId") ?: "" }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        // -----------------------------------------------------------------------------------------

        lifecycleScope.launch {
            val p = vm.get(productId)
            if (p == null) {
                nav.navigateUp()
                return@launch
            }

            binding.txtId.text = p.id
            binding.txtName.text = p.name
            binding.txtPrice.text = "%.2f".format(p.price)
            binding.imgPhoto.setImageByteArray(p.photo)

            generateQR(p.id)
        }

        // -----------------------------------------------------------------------------------------

        return binding.root
    }

    private fun generateQR(content: String) {
        // TODO(2): Generate QR and display it in an ImageView
        val bitmap = BarcodeEncoder().encodeBitmap(content, BarcodeFormat.QR_CODE, 400, 400)
        binding.imgQR.setImageBitmap(bitmap)
    }

}








