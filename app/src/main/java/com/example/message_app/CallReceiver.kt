package com.example.message_app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import android.util.Log

class CallReceiver: BroadcastReceiver()
{
    override fun onReceive(
        context: Context,
        intent: Intent
    ) {
        // Verificar si la acción es un cambio en el estado del teléfono
        if (intent.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)

            // Verificar si el estado es "Ringing" (llamada entrante)
            if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                // Obtener el número de teléfono de la llamada entrante
                val incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
                Log.d("CallReceiver", "Llamada entrante de: $incomingNumber")

                // Verificar si el número coincide y enviar el mensaje
                if (incomingNumber != null) {
                    checkAndSendMessage(context, incomingNumber)
                }
            }
        }
    }

    private fun checkAndSendMessage(
        context: Context,
        incomingNumber: String
    ) {
        // Obtener el número y mensaje guardados en SharedPreferences
        val sharedPreferences = context.getSharedPreferences("sms_prefs", Context.MODE_PRIVATE)
        val savedNumber = sharedPreferences.getString("phone_number", "")
        val savedMessage = sharedPreferences.getString("message", "")

        // Verificar si el número coincide
        if (incomingNumber == savedNumber && !savedMessage.isNullOrEmpty()) {
            Log.d("CallReceiver", "Número coincide. Enviando mensaje: $savedMessage")
            sendSms(incomingNumber, savedMessage)
        } else {
            Log.d("CallReceiver", "Número no coincide o no hay mensaje guardado.")
        }
    }

    private fun sendSms(
        phoneNumber: String,
        message: String
    ) {
        // Usar SmsManager para enviar el mensaje
        val smsManage = SmsManager.getDefault()
        smsManage.sendTextMessage(phoneNumber,
            null,
            message,null,null
        )
        Log.d("CallReceiver", "Mensaje enviado a $phoneNumber")
    }
}