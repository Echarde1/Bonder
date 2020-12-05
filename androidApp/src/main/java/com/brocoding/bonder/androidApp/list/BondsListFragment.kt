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
import androidx.compose.material.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.brocoding.bonder.androidApp.R
import com.brocoding.bonder.androidApp.base.BonderError
import com.brocoding.bonder.androidApp.base.BonderText
import com.brocoding.bonder.androidApp.details.BondDetailsFragment.Companion.ARG_BOND_LIST_ENTITY
import com.brocoding.bonder.androidApp.getColor
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
        val state: BondsListState by bondsViewModel.state.origin.collectAsState()

        BondsList(state)
    }

    @Composable
    private fun BondsList(state: BondsListState) {
        when (state) {
            BondsListState.Loading -> BondsListLoadingView()
            is BondsListState.Success -> BondsListSuccessView(state.result)
            is BondsListState.Error -> BonderError()
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

    @Composable
    private fun BondsListSuccessView(result: List<BondListEntity>) {
        LazyColumnFor(
            modifier = Modifier.fillMaxSize(),
            items = result
        ) { bond ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clickable(onClick = { goToDetails(bond) }),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .preferredSize(32.dp)
                        .clip(CircleShape)
                        .background(getRandomBondImageColor())
                )
                Spacer(Modifier.preferredSize(16.dp))
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BonderText(text = bond.bondName, textColor = getColor(R.color.color_333333))
                        BonderText(text = "${bond.couponPercent} %", textColor = getColor(R.color.color_333333))
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BonderText(bond.maturityDate, size = 8.sp)
                        BonderText(text = "${bond.prevPrice} ₽", size = 8.sp)
                    }
                }
            }
        }
    }

    @Composable
    private fun BondsListLoadingView() {
        LazyColumnFor(
            items = IntRange(1, 20).toList()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .preferredSize(32.dp)
                        .clip(CircleShape)
                        .background(getColor(R.color.color_DDDDDD))
                )
                Spacer(Modifier.preferredSize(16.dp))
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            Modifier.preferredSize(200.dp, 12.dp)
                                .background(getColor(R.color.color_DDDDDD))
                        )
                        Box(
                            Modifier.preferredSize(40.dp, 12.dp)
                                .background(getColor(R.color.color_DDDDDD))
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            Modifier.preferredSize(100.dp, 12.dp)
                                .background(getColor(R.color.color_DDDDDD))
                        )
                        Box(
                            Modifier.preferredSize(26.dp, 12.dp)
                                .background(getColor(R.color.color_DDDDDD))
                        )
                    }
                }
            }
        }
    }
}