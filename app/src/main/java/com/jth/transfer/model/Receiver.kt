package com.jth.transfer.model

data class Receiver(
    var accountHolder: String = "",
    var accountNumber: String = "",
    var isEnabled: Boolean = true,
    var name: String = "",
    var phoneNumber: String = "",
    var type : SendType = SendType.ACCOUNT
)