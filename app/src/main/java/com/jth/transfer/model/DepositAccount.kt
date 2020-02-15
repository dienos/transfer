package com.jth.transfer.model

import com.google.gson.annotations.SerializedName

data class DepositAccount(
    @SerializedName("accountHolder")
    var accountHolder: String = "",

    @SerializedName("accountNumber")
    var accountNumber: String = "",

    @SerializedName("isEnabled")
    var isEnabled: Boolean = true
)