package com.inflames.curenciesviewmodel.currencylistscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.inflames.curenciesviewmodel.currencylistscreen.adapter.CryptoListAdapter
import com.inflames.curenciesviewmodel.databinding.FragmentCurrencyListBinding
import timber.log.Timber


class CurrencyListFragment : Fragment() {

    private var _binding: FragmentCurrencyListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CryptoListViewModel by lazy {
        val activity = requireNotNull(this.activity) {}
        ViewModelProvider(this, CryptoListFactory(activity.application))
            .get(CryptoListViewModel::class.java)
    }
    private val adapter: CryptoListAdapter by lazy { CryptoListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCurrencyListBinding.inflate(layoutInflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.recyclerView.adapter = adapter

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