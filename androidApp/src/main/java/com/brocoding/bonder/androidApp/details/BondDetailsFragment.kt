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
import com.brocoding.bonder.shared.data.BonderApi
import com.brocoding.bonder.shared.feature.details.presentation.BondDetailsState
import com.brocoding.bonder.shared.feature.details.presentation.BondDetailsViewModel
import com.brocoding.bonder.shared.service_locator.ServiceLocator
import kotlinx.coroutines.flow.collect

class BondDetailsFragment : Fragment() {

    companion object {

        const val ARG_SEC_ID = "ARG_SEC_ID"

    }

    private val detailsViewModel: BondDetailsViewModel by viewModels(
        factoryProducer = {
            BondDetailViewModelFactory(getFragmentArgument(ARG_SEC_ID))
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Text("Дарова ептыть ${getFragmentArgument<String>(ARG_SEC_ID)}")
            }
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            detailsViewModel.state.collect {
                when (it) {
                    BondDetailsState.Loading -> Log.i("mytag", "LOOOOOADDDDDING")
                    is BondDetailsState.Success -> Log.i("mytag", "result: ${it.result}")
                    is BondDetailsState.Error -> Log.i("mytag", it.th.message!!)
                }
            }
        }
    }

    private class BondDetailViewModelFactory(
        private val secId: String
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass
                .getConstructor(String::class.java, BonderApi::class.java)
                .newInstance(secId, ServiceLocator.bonderApi)
        }
    }
}