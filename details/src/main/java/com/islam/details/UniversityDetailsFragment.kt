package com.islam.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.islam.details.databinding.FragmentUniversityDetailsBinding

class UniversityDetailsFragment : Fragment() {
    private lateinit var binding: FragmentUniversityDetailsBinding
    private val args: UniversityDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUniversityDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initActions()
    }

    private fun initViews() = with(binding) {
        args.university?.run {
            tvUniversityName.text = name
            tvUniversityState.text = stateProvince
            tvCountry.text = country
            tvCountryCode.text = alphaTwoCode
            tvWebPage.text = webPage
        }
    }

    private fun initActions() = with(binding) {
        ivRefresh.setOnClickListener {
            setFragmentResult(RESULT_KEY, Bundle())
            findNavController().navigateUp()
        }
    }

    companion object{
        const val RESULT_KEY = "refresh"
    }

}