package com.islam.listing.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.islam.core.visible
import com.islam.listing.databinding.FragmentUniversitiesBinding
import com.islam.listing.presentation.viewmodel.UniversitiesViewModel
import com.islam.listing.presentation.viewmodel.UniversitiesViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class UniversitiesFragment : DaggerFragment() {
    private lateinit var binding: FragmentUniversitiesBinding

    @Inject
    lateinit var viewModelFactory: UniversitiesViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[UniversitiesViewModel::class.java]
    }

    private val adapter by lazy {
        UniversityListAdapter(viewModel::navigateToUniversityDetails)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getUniversities()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUniversitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener(viewModel.getResultKey()) { _, _ ->
            getUniversities()
        }
        initViews()
        initObservations()
    }

    private fun getUniversities(){
        viewModel.getUniversities(COUNTRY)
    }

    private fun initViews() = with(binding) {
        rvUniversities.addItemDecoration(
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        )
        rvUniversities.adapter = adapter
    }

    private fun initObservations() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.universitiesStateFlow.collectLatest(adapter::submitList)
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadingSharedFlow.collectLatest(binding.pbLoading::visible)
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.errorSharedFlow.collectLatest {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    companion object {
        private const val COUNTRY = "United Arab Emirates"
    }
}