package com.jth.transfer.interf

interface DataCallBackListener {
    fun onSuccess(items: Any)
    fun onFailure(message: String?)
}