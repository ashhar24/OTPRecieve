package com.noc.otprecieve.reciever;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import com.noc.otprecieve.R;
import com.noc.otprecieve.activity.Otp;

/**
 * Created by defoliate on 09-10-2015.
 */
public class SmsReciever extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs ;
        String messageReceived = "";
        if(bundle != null)
        {
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i=0; i<msgs.length; i++)
            {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                messageReceived += msgs[i].getMessageBody().toString();
                messageReceived += "\n";
            }
            Log.d("MSG", messageReceived);
            if (messageReceived.contains("OTP is"))
            {

                int i = messageReceived.indexOf('"');
                int j = messageReceived.lastIndexOf('"');
                messageReceived = messageReceived.substring(i + 1, j);
                Toast.makeText(context, messageReceived, Toast.LENGTH_SHORT).show();
                Otp.getSmsDetails(messageReceived);
            }
        }
    }
}
