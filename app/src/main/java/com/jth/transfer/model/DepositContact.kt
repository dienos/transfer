package com.jth.transfer.model

import com.google.gson.annotations.SerializedName

data class DepositContact(
    @SerializedName("name")
    var name: String = "",

    @SerializedName("phoneNumber")
    var phoneNumber: String = ""
)