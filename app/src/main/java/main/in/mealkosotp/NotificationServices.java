package main.in.mealkosotp;

import android.telephony.SmsManager;

import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificationServices extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        if(message.getData().size()>0){
            String number = null,msg = null;
            if(message.getData().get("number") !=null){
                number= message.getData().get("number");

            }
            if(message.getData().get("msg") !=null){
                msg= message.getData().get("msg");

            }
            sendSms(number,msg);



        }

    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }

    private void sendSms(String phone,String sms){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone,null,sms,null,null);

    }
}


