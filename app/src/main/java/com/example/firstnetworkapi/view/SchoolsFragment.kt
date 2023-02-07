package com.example.firstnetworkapi.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstnetworkapi.R
import com.example.firstnetworkapi.adapter.SchoolAdapter
import com.example.firstnetworkapi.databinding.FragmentSchoolsBinding
import com.example.firstnetworkapi.model.Schools
import com.example.firstnetworkapi.service.Network
import com.example.firstnetworkapi.viewmodel.SchoolsViewModel

class SchoolsFragment : Fragment() {

    private val binding by lazy {
        FragmentSchoolsBinding.inflate(layoutInflater)
    }

    binding.Schoolresults.apply {
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, FALSE
            setHasFixedSize(true)
            adapter = Schoolresults
    }

        SchoolsViewModel.people.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS<Schools> -> {
                    schoolAdapter.updateSchools(state.response.results ?: emptyList())
                }
                is UIState.ERROR -> {}
            }

        }

    }
    return binding.root
}
    private val schoolAdapter by lazy {
        SchoolAdapter {
            findNavController().navigate(R.id.action_SchoolsFragment_to_DetailsFragment)
        }
    }

    private val schoolsViewModel: SchoolsViewModel by activityViewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SchoolsViewModel(Network.serviceApi) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.schoolRv.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = schoolAdapter
        }

        schoolsViewModel.schools.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.LOADING -> {

                }
                is UIState.SUCCESS -> {
                    schoolAdapter.updateSchools(state.response)
                }
                is UIState.ERROR -> {
                    AlertDialog.Builder(requireActivity())
                        .setTitle("Error occurred")
                        .setMessage(state.error.localizedMessage)
                        .setPositiveButton("RETRY") { dialog, _ ->
                            schoolsViewModel.getAllSchools()
                            dialog.dismiss()
                        }
                        .setNegativeButton("DISMISS") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
            }
        }

        schoolsViewModel.getAllSchools()

        return binding.root
    }
}