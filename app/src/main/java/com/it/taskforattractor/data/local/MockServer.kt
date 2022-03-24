package com.it.taskforattractor.data.local

import com.it.taskforattractor.model.Company
import com.it.taskforattractor.model.User
import com.it.taskforattractor.model.UserBase
import retrofit2.Response

class MockServer : Service {
    override suspend fun getUser(): Response<UserBase> {
        return Response.success(
            UserBase(
                User(
                    "Magnus",
                    "https://www.countryflags.com/wp-content/uploads/kyrgyzstan-flag-png-large.png",
                    "Carlson",
                    1,
                    listOf(
                        Company("Two Sigma", "Software engineer"),
                        Company("Google", "Software engineer")
                    )
                )
            )
        )
    }
}