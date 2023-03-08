package ir.box_net.test.presentation.home_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import ir.box_net.test.common.Routes
import ir.box_net.test.domain.model.Videos

@Composable
fun VideoCard(
    focused: Boolean,
    video: Videos,
    onFocusedFirstTime: () -> Unit,
    navController: NavController,
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(focused) {
        if (focused) {
            onFocusedFirstTime()
            focusRequester.requestFocus()
        }
    }

    val height = 180
    val width = height * 3f / 2

    Column(
        modifier = Modifier
            .widthIn(max = width.dp)
            .padding(horizontal = 8.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val boxInteractionSource = remember { MutableInteractionSource() }
        val isItemFocused by boxInteractionSource.collectIsFocusedAsState()

        Card(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .heightIn(max = height.dp)
                .aspectRatio(3f / 2)
                .focusRequester(focusRequester)
                .focusable(interactionSource = boxInteractionSource)
                .border(
                    width = 2.dp,
                    color = if (isItemFocused) Color.White else Color.Transparent,
                    shape = MaterialTheme.shapes.medium
                )
                .clickable {
                    navController.navigate(Routes.DetailScreen.routes + "/${video.id}")
                }
        ) {
            Box {
                AsyncImage(
                    model = video.thumbnail,
                    contentDescription = video.description,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .padding(4.dp)
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    Color.Black.copy(0.5f)
                                ),
                                startY = 0.3f,
                                endY = 0.5f
                            )
                        )
                ) {
                    Text(
                        text = video.name?.substringBefore("؛") ?: "name",
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Visible
                    )
                    Text(
                        text = video.description ?: "",
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}