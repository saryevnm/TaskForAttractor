package com.it.taskforattractor.ui.greeting

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.it.taskforattractor.data.local.MockServer
import com.it.taskforattractor.data.local.Service
import com.it.taskforattractor.data.remote.ServiceApi
import com.it.taskforattractor.model.UserBase
import com.it.taskforattractor.model.result.BaseDataSource
import com.it.taskforattractor.model.result.Resource
import kotlinx.coroutines.Dispatchers

class GreetingsRepositoryImpl(
    private val service: Service,
) :
    GreetingsRepository, BaseDataSource() {
    override fun getUser(): LiveData<Resource<UserBase>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val result = getResult { service.getUser() }
            emit(result)
        }
}