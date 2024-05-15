package com.example.demo.ui

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.demo.databinding.FragmentEmailBinding
import com.example.demo.util.SimpleEmail
import com.example.demo.util.errorDialog
import com.example.demo.util.hideKeyboard
import com.example.demo.util.snackbar

class EmailFragment : Fragment() {

    private lateinit var binding: FragmentEmailBinding
    private val nav by lazy { findNavController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEmailBinding.inflate(inflater, container, false)

        // -----------------------------------------------------------------------------------------

        binding.edtEmail.requestFocus()
        binding.btnSend.setOnClickListener { send() }

        // -----------------------------------------------------------------------------------------

        return binding.root
    }

    private fun send() {
        // NOTE: Extension function --> Fragment.hideKeyboard()
        hideKeyboard()

        val email = binding.edtEmail.text.toString().trim()
        if (!isEmail(email)) {
            errorDialog("Invalid email.");
            binding.edtEmail.requestFocus()
            return
        }

        val number = "%04d".format((0..9999).random())

        val subject = "Lucky number - $number";
        val content = """
            <p>Your <b>lucky number</b> is:</p>
            <h1 style="color: red">$number</h1>
            <p>Thank you.</p>
        """.trimIndent();

        // TODO(1): Send email
        SimpleEmail()
            .to(email)
            .subject(subject)
            .content(content)
            .isHtml()
            .send{
                snackbar("Email sent.")
                binding.btnSend.isEnabled = true
                binding.edtEmail.text.clear()
                binding.edtEmail.requestFocus()
            }

        snackbar("Sending email...")
        binding.btnSend.isEnabled = false
    }

    private fun isEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

}








