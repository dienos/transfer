package com.jth.transfer.repo

import android.content.Context
import android.widget.Toast
import com.google.gson.JsonElement
import com.jth.transfer.interf.DataCallBackListener
import com.jth.transfer.model.*
import com.jth.transfer.repo.api.ServiceGenerator
import com.jth.transfer.repo.api.TransferApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransferRepository(val context : Context) {
    companion object {
        private var instance: TransferRepository? = null

        fun getInstance(context: Context): TransferRepository {
            if(instance == null) {
                instance = TransferRepository(context)
            }

            return instance!!
        }
    }

    var withdrawAccountResult : WithdrawAccountResult = WithdrawAccountResult()
    var depositAccountResult : DepositAccountResult = DepositAccountResult()
    var depositContactResult : DepositContactResult = DepositContactResult()

    fun getWithdrawAccount(listener : DataCallBackListener) {
        val service = ServiceGenerator.createService(TransferApi::class.java)
        val request = service.getWithdrawAccount()
        request.enqueue(object : Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    val result = response.body()

                    result?.apply {
                        parsingWithdrawAccount(listener,this)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                listener.onFailure(t.message)
            }
        })
    }

    fun getDepositAccount(listener : DataCallBackListener) {
        val service = ServiceGenerator.createService(TransferApi::class.java)
        val request = service.getDepositAccount()
        request.enqueue(object : Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    val result = response.body()

                    result?.apply {
                        parsingDepositAccount(listener,this)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                listener.onFailure(t.message)
            }
        })
    }

    fun getDepositContact(listener : DataCallBackListener) {
        val service = ServiceGenerator.createService(TransferApi::class.java)
        val request = service.getDepositContact()
        request.enqueue(object : Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    val result = response.body()

                    result?.apply {
                        parsingDepositContact(listener, this)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun postAccountTransfer(data : ReqTransferAccount) {
        val service = ServiceGenerator.createService(TransferApi::class.java)
        val request = service.postAccountTransfer(data)
        request.enqueue(object : Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    val result = response.body()

                    result?.apply {

                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun postContactTransfer(data : ReqTransferContact) {
        val service = ServiceGenerator.createService(TransferApi::class.java)
        val request = service.postContactTransfer(data)
        request.enqueue(object : Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    val result = response.body()

                    result?.apply {

                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun parsingWithdrawAccount(listener : DataCallBackListener,
                                       json : JsonElement) {
        val list : ArrayList<WithdrawAccount> = arrayListOf()

        if(withdrawAccountResult.list.isNotEmpty()) {
            withdrawAccountResult.list.clear()
        }

        json.asJsonArray.forEach {
            val data = WithdrawAccount()
            val jsonObject =  it.asJsonObject
            data.accountNumber = jsonObject.get("accountNumber").asString
            data.balance = jsonObject.get("balance").asString
            data.bankLogo = jsonObject.get("bankLogo").asString
            list.add(data)
        }

        withdrawAccountResult.list = list
        listener.onSuccess(withdrawAccountResult)
    }

    private fun parsingDepositAccount(listener : DataCallBackListener,
                                      json : JsonElement) {
        val list : ArrayList<DepositAccount> = arrayListOf()

        if(depositAccountResult.list.isNotEmpty()) {
            depositAccountResult.list.clear()
        }

        json.asJsonArray.forEach {
            val data = DepositAccount()
            val jsonObject =  it.asJsonObject
            data.accountNumber = jsonObject.get("accountNumber").asString
            data.accountHolder = jsonObject.get("accountHolder").asString
            data.isEnabled = jsonObject.get("isEnabled").asBoolean
            list.add(data)
        }

        depositAccountResult.list = list
        listener.onSuccess(depositAccountResult)
    }

    private fun parsingDepositContact(listener : DataCallBackListener, json : JsonElement) {
        val list : ArrayList<DepositContact> = arrayListOf()

        if(depositContactResult.list.isNotEmpty()) {
            depositContactResult.list.clear()
        }

        json.asJsonArray.forEach {
            val data = DepositContact()
            val jsonObject =  it.asJsonObject
            data.name = jsonObject.get("name").asString
            data.phoneNumber = jsonObject.get("phoneNumber").asString
            list.add(data)
        }

        depositContactResult.list = list
        listener.onSuccess(depositContactResult)
    }
}