package components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import data.FakeCardItem
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun CardItemView(
    item: FakeCardItem,
    cardHeight: Int = 80,
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(visible) {
        // 一定時間後に非表示にする
        if (visible) {
            delay(1000)
            visible = false
        }
    }

    OutlinedCard {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f, fill = true)
                    .height(cardHeight.dp)
            ) {
                CardMainBox(item)
                AnimatedBox(visible)
            }
            FilledTonalButton(
                onClick = { visible = !visible },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(cardHeight.dp),
            ) {
                Icon(
                    Icons.Default.ThumbUp,
                    contentDescription = "done"
                )
            }
        }
    }
}

@Composable
private fun CardMainBox(
    item: FakeCardItem
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Text(
            item.title,
            maxLines = 1
        )
        Text(
            item.summary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun AnimatedBox(
    visible: Boolean,
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally (
            initialOffsetX = {400},
        ),
        exit = shrinkHorizontally (
            shrinkTowards = Alignment.End,
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
                .background(Color.Yellow)
        ) {
            Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = "dummy image",
            )
        }
    }
}
