package com.it.taskforattractor.ui.greeting

import androidx.lifecycle.LiveData
import com.it.taskforattractor.model.UserBase
import com.it.taskforattractor.model.result.Resource

interface GreetingsRepository {
    fun getUser(): LiveData<Resource<UserBase>>
}