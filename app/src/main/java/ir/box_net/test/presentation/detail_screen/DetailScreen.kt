package ir.box_net.test.presentation.detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ir.box_net.test.domain.model.detail.VideoDetail
import ir.box_net.test.presentation.detail_screen.component.ExoPlayerScreen

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val state = detailViewModel.state.value

    if (state.success != null) {
        Detail(videoDetail = state.success)
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
fun Detail(
    videoDetail: VideoDetail
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ConstraintLayout {
            val (name, description, thumbnail) = createRefs()

            Box(
                modifier = Modifier
                    .constrainAs(thumbnail) {
                        start.linkTo(name.end, margin = 20.dp)
                        end.linkTo(parent.end)
                        start.linkTo(description.end)
                    }

            ) {
                Card(
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)
                        .border(
                            width = 1.dp,
                            color = Color.White.copy(0.3f),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    shape = RoundedCornerShape(10.dp),
                    elevation = 10.dp
                ) {
                    AsyncImage(
                        model = videoDetail.thumbnail,
                        contentDescription = videoDetail.thumbnail.toString(),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Box(
                modifier = Modifier
                    .constrainAs(name) {
                        start.linkTo(parent.start)
                    }
            ) {
                Text(
                    text = videoDetail.name?.substringBefore("؛") ?: "name",
                    fontSize = 20.sp
                )
            }
            Box(
                modifier = Modifier
                    .constrainAs(description) {
                        top.linkTo(name.bottom, margin = 15.dp)
                        end.linkTo(thumbnail.start, margin = 20.dp)
                        start.linkTo(parent.start, margin = 0.dp)
                    }
                    .fillMaxWidth(0.5f)
            ) {
                Text(
                    text = videoDetail.description ?: "",
                    fontSize = 15.sp,
                    softWrap = true
                )
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.6f),
                shape = MaterialTheme.shapes.medium
            ) {
                ExoPlayerScreen(url = videoDetail.fileSrc ?: "")
            }
        }
    }
}