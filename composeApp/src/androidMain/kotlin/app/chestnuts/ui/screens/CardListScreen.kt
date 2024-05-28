package app.chestnuts.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.chestnuts.ui.components.AnimatedCardItemView
import data.FakeCardItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListScreen(

) {
    val cardItems = FakeCardItem.fakes()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Card List")
                },
            )
        }
    ) { innerPadding ->
        Box (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn (
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxHeight()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                items(cardItems) { item ->
                    AnimatedCardItemView(
                        item
                    )
                }
            }
        }

    }
}
