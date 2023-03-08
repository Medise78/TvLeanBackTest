package ir.box_net.test.presentation.detail_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ir.box_net.test.common.Resources
import ir.box_net.test.domain.model.detail.VideoDetail
import ir.box_net.test.domain.repository.TvRepository
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: TvRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(DetailState())
    val state: State<DetailState> get() = _state

    init {
        savedStateHandle.get<Int>("id")?.let {
            fetchDetailDataById(it)
        }
    }

    private fun fetchDetailDataById(id: Int) = viewModelScope.launch {
        repository.getVideoDetail(id).onEach {
            when (it) {
                is Resources.Success -> {
                    _state.value = state.value.copy(
                        success = it.data,
                        loading = false
                    )
                }
                is Resources.Loading -> {
                    _state.value = state.value.copy(
                        loading = true
                    )
                }
                is Resources.Error -> {
                    _state.value = state.value.copy(
                        error = it.message ?: "Error",
                        loading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class DetailState(
    val success: VideoDetail? = null,
    val loading: Boolean = false,
    val error: String = ""
)