package ir.box_net.test.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.itemsIndexed
import ir.box_net.test.domain.model.TvVideos
import ir.box_net.test.presentation.home_screen.component.VideoCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value

    if (state.success != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                TvLazyColumn(
                    contentPadding = PaddingValues(vertical = 27.dp),
                ) {
                    item {
                        CardRow(
                            homeViewModel = viewModel,
                            contentPaddingValues = PaddingValues(vertical = 27.dp),
                            videos = state.success,
                            navController = navController
                        )
                    }
                }
            }
        }
    }

    if (state.loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp), color = Color.White)
        }
    }

    if (state.error.isNotEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "${state.error} hi")
        }
    }
}

@Composable
fun CardRow(
    homeViewModel: HomeViewModel,
    contentPaddingValues: PaddingValues,
    videos: TvVideos,
    navController: NavController
) {
    Column {
        Text(
            text = videos.name ?: "",
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(contentPaddingValues)
        )

        TvLazyRow(
            modifier = Modifier.heightIn(min = 180.dp),
            contentPadding = contentPaddingValues,
            verticalAlignment = Alignment.CenterVertically
        ) {
            itemsIndexed(videos.videoDto ?: emptyList()) { index, video ->
                VideoCard(
                    focused = index == 0 && homeViewModel.isFirstTime,
                    video = video,
                    onFocusedFirstTime = {
                        homeViewModel.isFirstTime = false
                    },
                    navController = navController
                )
            }
        }
    }
}
