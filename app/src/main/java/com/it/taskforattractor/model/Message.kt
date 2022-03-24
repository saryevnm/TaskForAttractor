package com.it.taskforattractor.model

import java.io.Serializable


class Message : Serializable {
    var message: String? = null
    var details: ArrayList<String>? = null
}