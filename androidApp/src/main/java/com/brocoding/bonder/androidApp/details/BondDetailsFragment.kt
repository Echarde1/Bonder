package com.brocoding.bonder.androidApp.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.brocoding.bonder.androidApp.getFragmentArgument
import com.brocoding.bonder.shared.data.BonderRepository
import com.brocoding.bonder.shared.feature.details.presentation.BondDetailsState
import com.brocoding.bonder.shared.feature.details.presentation.BondDetailsViewModel
import com.brocoding.bonder.shared.feature.list.BondListEntity
import com.brocoding.bonder.shared.service_locator.ServiceLocator
import kotlinx.coroutines.flow.collect

internal class BondDetailsFragment : Fragment() {

    companion object {

        const val ARG_BOND_LIST_ENTITY = "ARG_BOND_LIST_ENTITY"

    }

    private val detailsViewModel: BondDetailsViewModel by viewModels(
        factoryProducer = {
            BondDetailViewModelFactory(getFragmentArgument(ARG_BOND_LIST_ENTITY))
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Text("Дарова ептыть ${getFragmentArgument<BondListEntity>(ARG_BOND_LIST_ENTITY)}")
            }
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            detailsViewModel.state.origin.collect {
                when (it) {
                    BondDetailsState.Loading -> Log.i("mytag", "LOOOOOADDDDDING")
                    is BondDetailsState.Success -> Log.i("mytag", "result: ${it.result}")
                    is BondDetailsState.Error -> Log.i("mytag", it.th.message!!)
                }
            }
        }
    }

    private class BondDetailViewModelFactory(
        private val bondListEntity: BondListEntity
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass
                .getConstructor(BondListEntity::class.java, BonderRepository::class.java)
                .newInstance(bondListEntity, ServiceLocator.bonderRepository)
        }
    }
}