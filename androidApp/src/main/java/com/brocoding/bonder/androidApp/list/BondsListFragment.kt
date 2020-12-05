package com.brocoding.bonder.androidApp.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.ui.tooling.preview.Preview
import com.brocoding.bonder.androidApp.R
import com.brocoding.bonder.androidApp.details.BondDetailsFragment.Companion.ARG_BOND_LIST_ENTITY
import com.brocoding.bonder.androidApp.getColor
import com.brocoding.bonder.shared.Greeting
import com.brocoding.bonder.shared.feature.list.BondListEntity
import com.brocoding.bonder.shared.feature.list.presentation.BondsListState
import com.brocoding.bonder.shared.feature.list.presentation.BondsListViewModel
import kotlin.random.Random

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
//        val state: BondsListState by bondsViewModel.state.origin.collectAsState()
        val state = BondsListState.Loading

        BondsList(state)
    }

    @Composable
    private fun BondsList(state: BondsListState) {
        when (state) {
            BondsListState.Loading -> {
                /*Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        Modifier
                            .size(150F.dp)
                    )
                }*/
                LazyColumnFor(
                    modifier = Modifier.padding(16.dp),
                    items = IntRange(1, 20).toList()
                ) { idx ->
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .preferredSize(32.dp)
//                                .padding(16.dp)
                                .clip(CircleShape)
                                .background(getColor(R.color.color_DDDDDD))
                        )
                        Text("${greet()} $idx")
                    }
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
                            .clickable(onClick = { goToDetails(bond) }),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .preferredSize(100.dp)
                                .padding(16.dp)
                                .clip(CircleShape)
                                .background(getRandomBondImageColor())
                        )

                        Text("${greet()} ${bond.secId}")
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

    private enum class BondImageColor(@ColorRes val colorId: Int) {

        PINK(R.color.color_FFB0B0),
        YELLOW(R.color.color_FFF0D3),
        PURPLE(R.color.color_BBBBFF);

    }

    private fun getRandomBondImageColor(): Color {
        val colorInts = BondImageColor.values()
        val randomIdx = Random.nextInt(colorInts.size)
        return getColor(colorInts[randomIdx].colorId)
    }

    private fun goToDetails(bondListEntity: BondListEntity) {
        findNavController().navigate(object : NavDirections {

            override fun getActionId(): Int = R.id.action_bondsFragment_to_bondDetailsFragment

            override fun getArguments(): Bundle = bundleOf(ARG_BOND_LIST_ENTITY to bondListEntity)

        })
    }

    private fun greet(): String {
        return Greeting().greeting()
    }

    @Preview
    @Composable
    fun BondsListPreview() {
        MaterialTheme {
            BondsList(state = BondsListState.Loading)
        }
    }
}