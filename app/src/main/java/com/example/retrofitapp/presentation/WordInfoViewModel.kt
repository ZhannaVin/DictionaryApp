package com.example.retrofitapp.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.room.Insert
import com.example.retrofitapp.R
import com.example.retrofitapp.domain.model.Word
import com.example.retrofitapp.domain.use_case.DictionaryUseCases
import com.example.retrofitapp.presentation.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.retrofitapp.data.network.Result

const val LOG_TAG = "NetworkTypeError"

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val dictionaryUseCases: DictionaryUseCases
) : ViewModel() {

    private val _words = MutableLiveData<List<Word>?>()
    val words: LiveData<List<Word>?> = _words

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _showSnackBarEventValue = MutableLiveData<Event<Int>>()
    val showSnackBarEventValue: LiveData<Event<Int>> = _showSnackBarEventValue

    fun getDefinitions(word: String?) = viewModelScope.launch {
        if (word.isNullOrEmpty()) return@launch
        dictionaryUseCases.getWordInfoFromNetwork.invoke(word).onEach { result ->
            when (result) {
                is Result.Loading -> {
                    _loading.value = true
                }
                is Result.Success -> {
                    _words.value = result.data
                    _loading.value = false
                }
                is Result.Error -> {
                    val words = dictionaryUseCases.getWordInfoFromDatabase.invoke(word)
                    if (words.isNullOrEmpty()) {
                        _words.value = emptyList()
                        _showSnackBarEventValue.value = Event(R.string.error_data_loading)
                    } else {
                        _words.value = words
                        //_showSnackBarEventValue.value = Event(R.string.error_previous_results)
                    }
                    _loading.value = false
                    Log.e(LOG_TAG, result.exception.toString())
                }
            }
        }.launchIn(this)
    }
}
