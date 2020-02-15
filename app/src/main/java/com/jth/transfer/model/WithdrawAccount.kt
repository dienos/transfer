package com.jth.transfer.model

import com.google.gson.annotations.SerializedName

data class WithdrawAccount(
    @SerializedName("accountNumber")
    var accountNumber: String = "",

    @SerializedName("balance")
    var balance: String = "",

    @SerializedName("bankLogo")
    var bankLogo: String = ""
)

