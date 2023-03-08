package ir.box_net.test.presentation.detail_screen.component

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun ExoPlayerScreen(
    url: String
) {
    val context = LocalContext.current

    val exoPlayer = ExoPlayer.Builder(context).build()
    val mediaItemFromUri = MediaItem.fromUri(Uri.parse(url))
    exoPlayer.setMediaItem(mediaItemFromUri)

    val playerView = StyledPlayerView(context)
    playerView.player = exoPlayer

    DisposableEffect(
        AndroidView(factory = { playerView })
    ) {
        exoPlayer.prepare()
        exoPlayer.playWhenReady = false
        exoPlayer.videoScalingMode=
            C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
        onDispose {
            exoPlayer.release()
        }
    }
}