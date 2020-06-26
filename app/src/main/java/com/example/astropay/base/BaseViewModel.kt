package com.example.astropay.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astropay.R
import com.example.astropay.network.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    val uiScope = Dispatchers.Main

    private val _errorMessage = MutableLiveData<Int>()
    val errorMessage: LiveData<Int>
        get() = _errorMessage

    protected fun <T : Any> processResponse(
        response: ResultWrapper<T>,
        onSuccess: (T) -> Unit
    ) {
        when (response) {
            is ResultWrapper.NetworkError -> {
                showErrorMessage()
            }
            is ResultWrapper.Success -> {
                onSuccess.invoke(response.value)
            }
            else -> showErrorMessage()
        }
    }

    protected fun showErrorMessage(message: Int? = null) {
        viewModelScope.launch(uiScope) {
            _errorMessage.value = message ?: R.string.error_message_default
        }
    }


}