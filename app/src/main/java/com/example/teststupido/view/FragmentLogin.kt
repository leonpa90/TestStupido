package com.example.teststupido.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.teststupido.R
import com.example.teststupido.databinding.FragmentLoginBinding

class FragmentLogin:Fragment()
{
    lateinit var bind:FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind= FragmentLoginBinding.inflate(inflater,container,false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // il view model lo si instanzia o cosi o cosi
        val logonViewModelz by viewModels<LogonViewModel>()
        val logonViewModel=ViewModelProvider(this).get(LogonViewModel::class.java)
        logonViewModel.userData.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_fragmentLogin_to_responseFragment, bundleOf("user" to it.username))
        })
        logonViewModel.registrationData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        logonViewModel.errorData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        bind.loginBtn.setOnClickListener {
            logonViewModel.login(requireContext(),bind.userEdit.text.toString(),bind.passEdit.text.toString())
        }
        bind.registratiBtn.setOnClickListener {
            logonViewModel.registrati(requireContext(),bind.userEdit.text.toString(),bind.passEdit.text.toString())
        }
    }
}