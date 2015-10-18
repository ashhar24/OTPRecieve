package com.noc.otprecieve.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.noc.otprecieve.R;
import com.noc.otprecieve.reciever.SmsReciever;

/**
 * Created by defoliate on 09-10-2015.
 */
public class Otp extends Activity
{
    static EditText otp;
    static String msg;
    private static Context mContext;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        otp = (EditText) findViewById(R.id.etOtp);
        mContext = this;
        enableBroadcastReceiver();
    }

    public static void getSmsDetails (String SMSBody)
    {
        otp.setText(SMSBody);
        //Intent i = new Intent(mContext,MainActivity.class);
        //i.putExtra("OTP", SMSBody);
        disableBroadcastReceiver();
    }

    public void enableBroadcastReceiver()
    {

        ComponentName receiver = new ComponentName(this, SmsReciever.class);
        PackageManager pm = this.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        Toast.makeText(this, "Enabled broadcast receiver", Toast.LENGTH_SHORT).show();
    }

    public static void disableBroadcastReceiver ()
    {
        ComponentName receiver = new ComponentName(mContext, SmsReciever.class);
        PackageManager pm = mContext.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
        Toast.makeText(mContext, "Disabled broadcast receiver", Toast.LENGTH_SHORT).show();
    }

}
