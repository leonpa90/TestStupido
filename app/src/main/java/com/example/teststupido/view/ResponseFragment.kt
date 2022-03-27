package com.example.teststupido.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.teststupido.data.repository.Repository
import com.example.teststupido.databinding.ResponseFragmentBinding

lateinit var bind:ResponseFragmentBinding
class ResponseFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind= ResponseFragmentBinding.inflate(inflater,container,false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var model=ViewModelProvider(this).get(ResponseViewModel::class.java)
        model.data.observe(viewLifecycleOwner, Observer {
            bind.usernameTxt.text=it.username
            bind.passTxt.text=it.password
            bind.danaroTxt.text=it.porcoDio.toString()


        })

        model.pianetaData.observe(viewLifecycleOwner, Observer {
            bind.pianetaTxt.text=it.name
        })
     val argomenti  =arguments?.getString("user")
        argomenti?.let {
            model.getUserInfo(requireContext(),it)
        }


        bind.aggiungiBtn.setOnClickListener {
            argomenti?.let {
                model.aggiungiDanaro(requireContext(), it, bind.aggiungiEditTxt.text.toString().toDouble())
//                model.getPianeta(requireContext(),bind.aggiungiEditTxt.text.toString())
            }
        }


    }
}