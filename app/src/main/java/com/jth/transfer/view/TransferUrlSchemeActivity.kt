package com.jth.transfer.view

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Toast
import com.jth.transfer.R

class TransferUrlSchemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.let {
            val intent = intent

            if (Intent.ACTION_VIEW == intent.action) {
                val uri = intent.data

                uri?.apply {
                    var isComplete = false

                    val accountNumber = uri.getQueryParameter("accountNumber")
                    val amount = uri.getQueryParameter("amount")
                    val receiverAccountNumber = uri.getQueryParameter("receiverAccountNumber")
                    val receiverPhoneNumber = uri.getQueryParameter("receiverPhoneNumber")

                    if (accountNumber.isNullOrEmpty() || amount.isNullOrEmpty()) {
                        showToast(getString(R.string.send_fail))
                    } else {
                        if (!receiverAccountNumber.isNullOrEmpty()) {
                            showToast(
                                getString(
                                    R.string.send_complete,
                                    receiverAccountNumber,
                                    getString(R.string.account)
                                )
                            )

                            isComplete = true
                        } else if (!receiverPhoneNumber.isNullOrEmpty()) {
                            showToast(
                                getString(
                                    R.string.send_complete,
                                    receiverPhoneNumber,
                                    getString(R.string.contact)
                                )
                            )

                            isComplete = true
                        } else {
                            showToast(getString(R.string.send_fail))
                            isComplete = false
                        }
                    }
                    
                    if(isComplete) {
                        setResult(Activity.RESULT_OK)
                        finish()
                    }
                }
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(
            applicationContext,
            msg,
            Toast.LENGTH_SHORT
        ).show()
    }
}