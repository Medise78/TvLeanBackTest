package ir.box_net.test.presentation.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ir.box_net.test.common.Resources
import ir.box_net.test.domain.model.TvVideos
import ir.box_net.test.domain.repository.TvRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TvRepository
) : ViewModel() {

    var isFirstTime:Boolean = true

    private val _state = mutableStateOf(TvVideosState())
    val state: State<TvVideosState> get() = _state

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch(Dispatchers.IO) {
        repository.getAllVideos().onEach {
            when (it) {
                is Resources.Success -> {
                    _state.value = state.value.copy(
                        success = it.data,
                        loading = false
                    )
                }
                is Resources.Error -> {
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
            }
        }.launchIn(viewModelScope)
    }
}

data class TvVideosState(
    val success: TvVideos? = null,
    val loading: Boolean = false,
    val error: String = ""
)