package com.brocoding.bonder.androidApp.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.brocoding.bonder.androidApp.R
import com.brocoding.bonder.androidApp.details.BondDetailsFragment
import com.brocoding.bonder.shared.Greeting
import com.brocoding.bonder.shared.feature.list.presentation.BondsListState
import com.brocoding.bonder.shared.feature.list.presentation.BondsListViewModel

internal class BondsListFragment : Fragment() {

    private val bondsViewModel: BondsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                BondsListScreen()
            }
        }
    }

    @Composable
    private fun BondsListScreen() {
        val state: BondsListState by bondsViewModel.state.origin.collectAsState()

        BondsList(state)
    }

    @Composable
    private fun BondsList(state: BondsListState) {
        when (state) {
            BondsListState.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        Modifier
                            .size(Dp(150F))
                    )
                }
            }
            is BondsListState.Success -> {
                LazyColumnFor(
                    modifier = Modifier.fillMaxSize(),
                    items = state.result
                ) { bond ->
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(onClick = { goToDetails(bond.secid) }),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .preferredSize(100.dp)
                                .padding(16.dp)
                                .clip(CircleShape)
                                .background(Color.Red)
                        )

                        Text("${greet()} ${bond.secid}")
                    }
                }
            }
            is BondsListState.Error -> {
                Snackbar(
                    text = { Text(text = "Error епта") }
                )
            }
        }
    }

    private fun goToDetails(secId: String) {
        findNavController().navigate(object : NavDirections {

            override fun getActionId(): Int = R.id.action_bondsFragment_to_bondDetailsFragment

            override fun getArguments(): Bundle = bundleOf(BondDetailsFragment.ARG_SEC_ID to secId)

        })
    }

    private fun greet(): String {
        return Greeting().greeting()
    }
}