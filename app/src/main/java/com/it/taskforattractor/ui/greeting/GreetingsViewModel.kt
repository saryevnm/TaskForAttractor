package com.it.taskforattractor.ui.greeting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.it.taskforattractor.model.UserBase
import com.it.taskforattractor.model.result.Resource

class GreetingsViewModel(private val repository: GreetingsRepository) : ViewModel() {

    private var _user = MutableLiveData<Resource<UserBase>>()

    val user: LiveData<Resource<UserBase>>
        get() = _user

    fun getUser() {
        _user = repository.getUser() as MutableLiveData<Resource<UserBase>>
    }
}