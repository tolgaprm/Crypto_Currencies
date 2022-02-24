package com.inflames.curenciesviewmodel.currencydetailscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.inflames.curenciesviewmodel.currencydetailscreen.viewmodel.CryptoDetailFactory
import com.inflames.curenciesviewmodel.currencydetailscreen.viewmodel.CryptoDetailViewModel
import com.inflames.curenciesviewmodel.databinding.FragmentCurrencyDetailBinding

class CurrencyDetail : Fragment() {

    private var _binding: FragmentCurrencyDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCurrencyDetailBinding.inflate(layoutInflater, container, false)

        val clickedCryptoModelById = CurrencyDetailArgs.fromBundle(requireArguments()).cryptoModel

        val app = requireNotNull(this.activity)
        val viewModel = ViewModelProvider(
            this,
            CryptoDetailFactory(app.application, clickedCryptoModelById)
        )[CryptoDetailViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner
        binding.cryptoModel = clickedCryptoModelById
        viewModel.cryptoDetail.observe(viewLifecycleOwner, Observer {
            binding.cryptoDetail = it
        })

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