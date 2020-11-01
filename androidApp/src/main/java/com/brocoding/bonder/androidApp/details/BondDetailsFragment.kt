package com.brocoding.bonder.androidApp.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.brocoding.bonder.androidApp.getFragmentArgument

class BondDetailsFragment : Fragment() {

    companion object {

        const val ARG_SEC_ID = "ARG_SEC_ID"

    }

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
}