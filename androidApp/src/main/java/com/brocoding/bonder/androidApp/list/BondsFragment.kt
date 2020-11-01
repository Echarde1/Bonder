package com.brocoding.bonder.androidApp.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.ui.tooling.preview.Preview
import com.brocoding.bonder.androidApp.details.BondDetailsFragment
import com.brocoding.bonder.androidApp.R
import com.brocoding.bonder.shared.Greeting

class BondsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                getBondsContent()
            }
        }
    }

    @Preview
    @Composable
    private fun getBondsContent() {
        LazyColumnForIndexed(
            modifier = Modifier.fillMaxSize(),
            items = (1..10).toList()
        ) { idx, _ ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = { goToDetails(idx.toString()) }),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .preferredSize(100.dp)
                        .padding(16.dp)
                        .clip(CircleShape)
                        .background(Color.Red)
                )

                Text("${greet()} $idx")
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