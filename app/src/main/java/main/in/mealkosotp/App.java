package main.in.mealkosotp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessaging;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        buildNotificationToken();
        buildNotificationChannel();

    }


    public void buildNotificationToken() {

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                String token=task.getResult();
                Log.e("notification_token",token);

            }else {

                Log.e("notification_token","error");

            }

        });
    }

    private void buildNotificationChannel() {
        //order notification channel
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel orderNotificationChannel = new NotificationChannel("SEND OTP CHANNEL","Otp", NotificationManager.IMPORTANCE_HIGH);
            orderNotificationChannel.setDescription("send otp form this");
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(orderNotificationChannel);
        }

    }
}
