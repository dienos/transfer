package com.jth.transfer.model

import com.google.gson.annotations.SerializedName

data class ReqTransferContact(
    @SerializedName("accountNumber")
    var accountNumber: String = "",

    @SerializedName("amount")
    var amount: Int = 0,

    @SerializedName("receiverPhoneNumber")
    var receiverPhoneNumber: String = ""
)