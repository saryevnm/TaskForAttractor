package com.it.taskforattractor.data.remote

import com.it.taskforattractor.model.UserBase
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi{

    @GET("api/user")
    suspend fun getUser(): Response<UserBase>
}