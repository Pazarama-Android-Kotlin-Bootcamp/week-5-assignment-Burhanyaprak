package com.burhanyaprak.patikaweekfive.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.burhanyaprak.patikaweekfive.R
import com.burhanyaprak.patikaweekfive.data.model.DataState
import com.burhanyaprak.patikaweekfive.data.model.UserDTO
import com.burhanyaprak.patikaweekfive.databinding.FragmentUserBinding
import com.burhanyaprak.patikaweekfive.ui.loadingprogress.LoadingProgressBar
import com.burhanyaprak.patikaweekfive.ui.viewmodel.UserViewEvent
import com.burhanyaprak.patikaweekfive.ui.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment(){
    lateinit var loadingProgressBar: LoadingProgressBar
    private lateinit var binding: FragmentUserBinding
    private val viewModel by viewModels<UserViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingProgressBar = LoadingProgressBar(requireContext())

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.postLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    loadingProgressBar.hide()
                    it.data?.let { safeData ->
                        binding.recyclerViewUserList.adapter = UserAdapter().apply {
                            submitList(safeData)
                        }
                    } ?: run {
                        Toast.makeText(requireContext(), "No data", Toast.LENGTH_SHORT).show()
                    }
                }
                is DataState.Error -> {
                    loadingProgressBar.hide()
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                }
                is DataState.Loading -> {
                    loadingProgressBar.show()
                }
            }
        }

        viewModel.eventStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is UserViewEvent.ShowMessage -> {}
                is UserViewEvent.NavigateToDetail -> {}
            }
        }

    }
}