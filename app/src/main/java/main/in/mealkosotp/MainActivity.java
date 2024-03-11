package main.in.mealkosotp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import main.in.mealkosotp.databinding.ActivityMainBinding;
//! this activity is not very much useful for sending otp because i want to automate otp sending , main use of this
//!activity is just for requesting permission at runtime.
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        handleClick();




    }

    private void handleClick() {
        mainBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                    try {
                        sendSms(mainBinding.editTextPhone.getText().toString(),mainBinding.editTextTextMultiLine.getText().toString());
                        Toast.makeText(MainActivity.this, "Sms send successfully", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this, "Exception "+ e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }else if(shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)){
                    showPermissionDialog();


                }else{

                   request_permission_launcher.launch(Manifest.permission.SEND_SMS);
                }


            }
        });

    }

    private final ActivityResultLauncher<String> request_permission_launcher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if (result){
                handleClick();

            }else{

                showPermissionDialog();


            }
        }
    });




    private void showPermissionDialog() {
        AlertDialog alertDialogBuilder = new AlertDialog.Builder(MainActivity.this)
                .setMessage("this is important to provide service Rat")
                .setTitle("Allow send sms permission to use this service")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        requestPermissions(new String[] {Manifest.permission.SEND_SMS},2000);

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();

    }

    private void sendSms(String phone,String sms){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone,null,sms,null,null);

    }
}