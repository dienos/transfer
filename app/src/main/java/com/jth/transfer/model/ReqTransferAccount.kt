package com.jth.transfer.model

import com.google.gson.annotations.SerializedName

data class ReqTransferAccount(
    @SerializedName("accountNumber")
    var accountNumber: String = "",

    @SerializedName("amount")
    var amount: Int = 0,

    @SerializedName("receiverAccountNumber")
    var receiverAccountNumber: String = ""
)