package com.it.taskforattractor.data.local

import com.it.taskforattractor.model.UserBase
import retrofit2.Response

interface Service {
    suspend fun getUser(): Response<UserBase>
}