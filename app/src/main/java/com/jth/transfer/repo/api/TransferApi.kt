package com.jth.transfer.repo.api

import com.google.gson.JsonElement
import com.jth.transfer.model.ReqTransferContact
import com.jth.transfer.model.ReqTransferAccount
import retrofit2.Call
import retrofit2.http.*

interface TransferApi {
    @GET("/withdraw-account-list")
    fun getWithdrawAccount(): Call<JsonElement>

    @GET("/deposit-account-list")
    fun getDepositAccount(): Call<JsonElement>

    @GET("/deposit-contact-list")
    fun getDepositContact(): Call<JsonElement>

    @POST("/transfer/account")
    fun postAccountTransfer(@Body data : ReqTransferAccount) : Call<JsonElement>

    @POST("/transfer/contact")
    fun postContactTransfer(@Body data : ReqTransferContact) : Call<JsonElement>
}

