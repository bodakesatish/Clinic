package com.bodakesatish.clinic.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bodakesatish.clinic.NavigationActivity
import com.bodakesatish.clinic.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentLogin : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel : ViewModelLogin by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(tag, "In $tag onCreateView")
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showKeyboard()
//        viewModel.getSchemeList()
        setupForLoginWithPassword()

        initObserver()
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLogin.collect { isLogin ->
                    if(isLogin) {
                        val intent = Intent(requireContext(), NavigationActivity::class.java)
                        requireActivity().startActivity(intent)
                        requireActivity().finish()
                    }
                }
            }
        }
    }

    // USERNAME + PASSWORD SECTION
    private fun setupForLoginWithPassword() {
        viewModel.loginWithPasswordFormState.observe(viewLifecycleOwner, Observer { formState ->
            val loginState = formState ?: return@Observer
            when (loginState) {
                is SuccessfulLoginFormState -> {
                    binding.evUsername.error = null
                    binding.evPassword.error = null
                    binding.btnLogin.isEnabled = loginState.isDataValid

                }
                is FailedLoginFormState -> {
                    binding.evUsername.editText?.setText("")
                    binding.evPassword.editText?.setText("")
                }

                is PasswordFormState -> {
                    if(loginState.isDataValid) {
                        binding.evPassword.error = null
                    } else {
                        loginState.passwordError?.let { binding.evPassword.error = getString(it) }
                    }
                }
                is UserNameFormState -> {
                    if(loginState.isDataValid) {
                        binding.evUsername.error = null
                    } else {
                        loginState.usernameError?.let { binding.evUsername.error = getString(it) }
                    }
                }
            }
        })
        viewModel.loginResult.observe(viewLifecycleOwner, Observer {
            val loginResult = it ?: return@Observer
            if (loginResult.success) {
                val intent = Intent(requireContext(), NavigationActivity::class.java)
                requireActivity().startActivity(intent)
                requireActivity().finish()
            }else {
                updateApp(
                    "Failed to signed up"
                    //+ "${SampleAppUser.username} with fake token ${SampleAppUser.fakeToken}"
                )
            }
        })
        binding.evUsername.editText?.doAfterTextChanged {
            viewModel.onUserNameChanged(
                binding.evUsername.editText?.text.toString()
            )
        }
        binding.evPassword.editText?.doAfterTextChanged {
            viewModel.onPasswordChanged(
                binding.evPassword.editText?.text.toString()
            )
        }
        binding.evPassword.editText?.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE ->
                    viewModel.login(
                        binding.evUsername.editText?.text.toString(),
                        binding.evPassword.editText?.text.toString()
                    )
            }
            false
        }
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.evUsername.editText?.text.toString(),
                binding.evPassword.editText?.text.toString()
            )
        }
//        Log.d(TAG, "Username ${SampleAppUser.username}; fake token ${SampleAppUser.fakeToken}")

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showKeyboard() {
        lifecycleScope.launch {
            binding.evUsername.requestFocus()
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(binding.evUsername, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun updateApp(successMsg: String) {
        binding.success.text = successMsg
    }

}