package com.inflames.curenciesviewmodel.currencylistscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.inflames.curenciesviewmodel.databinding.FragmentCurrencyListBinding
import com.inflames.curenciesviewmodel.enums.CryptoApiStatus
import timber.log.Timber


class CurrencyListFragment : Fragment() {

    private var _binding: FragmentCurrencyListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCurrencyListBinding.inflate(layoutInflater)
        val viewModel = ViewModelProvider(this)[CryptoListViewModel::class.java]

        viewModel.list.observe(viewLifecycleOwner) {
            Timber.d(it[0].name)
            Timber.d(it[0].logoUrl)
        }


        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}