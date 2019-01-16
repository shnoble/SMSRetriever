package com.noble.smsretriever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

class SmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            val extras = intent.extras
            val status = extras!!.get(SmsRetriever.EXTRA_STATUS) as Status
            when (status.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    val message = extras.get(SmsRetriever.EXTRA_SMS_MESSAGE) as String
                    Log.d(Companion.TAG, message)
                }
                CommonStatusCodes.TIMEOUT -> {
                    Log.d(Companion.TAG, "Timeout.")
                }
                else -> Log.d(TAG, "Unknown status.")
            }
        }
        if (intent?.action == SmsRetriever.SMS_RETRIEVED_ACTION) {

        }
    }

    companion object {
        private const val TAG: String = "SmsReceiver"
    }
}