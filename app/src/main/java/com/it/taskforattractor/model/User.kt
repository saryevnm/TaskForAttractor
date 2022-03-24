package com.it.taskforattractor.model

data class User(

    val first_name: String,
    val photo: String,
    val second_name: String,
    val education: Int,
    val company: List<Company>
)