package com.example.lcmonitoring;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Object[] smsObj = (Object[]) bundle.get("pdus");
        for(Object obj:smsObj){
            SmsMessage  message = SmsMessage.createFromPdu((byte[]) obj);
            String CUGNumber = message.getDisplayOriginatingAddress();
            String msg = message.getDisplayMessageBody();
            Log.d("MessageDetails","CUGNumber : "+CUGNumber+"\n Message : "+msg);
            Intent iNext=new Intent(context.getApplicationContext(),MainActivity.class);
            PendingIntent pi= PendingIntent.getActivity(context.getApplicationContext(), 0, intent,0);
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("+916355330419",null,"Please Take Permit !!!",pi,null);
        }
    }
}
