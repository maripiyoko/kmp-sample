package app.chestnuts.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import data.FakeCardItem
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun AnimatedCardItemView(
    item: FakeCardItem,
    minCardHeight: Int = 90,
) {

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(visible) {
        if (visible) {
            // 一定時間後に自動で非表示にする
            delay(1000)
            visible = false
        }
    }

    OutlinedCard(
        modifier = Modifier.heightIn(minCardHeight.dp),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
        ) {
            val (box, button) = createRefs()
            val dividerGuideline = createGuidelineFromEnd(minCardHeight.dp)

            val boxModifier = Modifier.constrainAs(box) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(dividerGuideline)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            }

            CardItemMainView(
                onClick = {},
                item,
                visible,
                boxModifier,
                minCardHeight,
            )

            FilledTonalButton(
                onClick = { visible = !visible },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .constrainAs(button) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(dividerGuideline)
                        height = Dimension.fillToConstraints
                    }
                    .width(minCardHeight.dp)
            ) {
                Icon(
                    Icons.Default.ThumbUp,
                    contentDescription = "good",
                )
            }
        }
    }
}

@Composable
private fun CardItemMainView(
    onClick: () -> Unit,
    item: FakeCardItem,
    visible: Boolean,
    boxModifier: Modifier,
    minCardHeight: Int
) {
    Box (
        modifier = boxModifier
            .clickable { onClick() }
            .height(IntrinsicSize.Min)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = boxModifier
                .heightIn(minCardHeight.dp)
                .fillMaxWidth()
                .padding(16.dp),
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

        BoxAnimation(
            visible,
            boxModifier,
            minCardHeight
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun BoxAnimation(
    visible: Boolean,
    modifier: Modifier,
    minCardHeight: Int,
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
            modifier = modifier
                .background(Color.Yellow)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = "dummy image",
                modifier = Modifier.size(minCardHeight.dp)
            )
        }
    }
}


@Preview
@Composable
private fun Preview_AnimatedCardItemView_LongText() {
    AnimatedCardItemView(
        item = FakeCardItem.fakes().first(),
    )
}

@Preview
@Composable
private fun Preview_AnimatedCardItemView_ShortText() {
    AnimatedCardItemView(
        item = FakeCardItem(
            title = "aa",
            summary = "短い"
        ),
    )
}

@Preview
@Composable
private fun Preview_BoxAnimation() {
    BoxAnimation(visible = true, modifier = Modifier, minCardHeight = 90)
}