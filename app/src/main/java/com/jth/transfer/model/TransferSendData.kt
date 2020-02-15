package com.jth.transfer.model

data class TransferSendData(
    var withdrawAccount: WithdrawAccount = WithdrawAccount(),
    var deposit: Receiver = Receiver(),
    var sendMoneyAmount : String = ""
)