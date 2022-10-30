package com.example.smartpixabay.presentation.registration.view.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.smartpixabay.R
import com.example.smartpixabay.databinding.FragmentRegistrationBinding
import com.example.smartpixabay.presentation.registration.viewmodel.RegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

    private val viewModel: RegistrationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val ageEditText = binding.age
        val registerButton = binding.register
        val loadingProgressBar = binding.loading

        viewModel.registrationFormState.observe(viewLifecycleOwner,
            Observer { registrationFormState ->
                if (registrationFormState == null) {
                    return@Observer
                }
                registerButton.isEnabled = registrationFormState.isDataValid
                registrationFormState.usernameError?.let {
                    usernameEditText.error = getString(it)
                }
                registrationFormState.passwordError?.let {
                    passwordEditText.error = getString(it)
                }
                registrationFormState.ageError?.let {
                    ageEditText.error = getString(it)
                }
            })

        viewModel.registrationResult.observe(viewLifecycleOwner,
            Observer { isRegistrationSuccessful ->
                loadingProgressBar.visibility = View.GONE
                if (isRegistrationSuccessful) {
                    val action = RegistrationFragmentDirections.goToHome()
                    findNavController().navigate(action)
                } else {
                    showRegistrationFailed(R.string.registration_failed)
                }
            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                viewModel.registrationDataChanged(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString(),
                    ageEditText.text.toString().toInt()
                )
            }
        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.register(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString(),
                    ageEditText.text.toString()
                )
            }
            false
        }

        registerButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            viewModel.register(
                usernameEditText.text.toString(),
                passwordEditText.text.toString(),
                ageEditText.text.toString()
            )
        }
    }

    private fun showRegistrationFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }
}