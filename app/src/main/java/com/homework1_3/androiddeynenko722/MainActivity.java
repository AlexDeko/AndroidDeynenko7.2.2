package com.homework1_3.androiddeynenko722;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 11;
    EditText numberMobile;
    EditText txtSMS;
    Button btnSendSMS;
    Button btnCallMobile;
    String numberThisMobile;
    String txtSMSThisMobile;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        sendSMS();
        callMobile();
    }

    public void init(){
        txtSMS =findViewById(R.id.editTextSMS);
        numberMobile =findViewById(R.id.editNumberMobile);
        btnSendSMS= findViewById(R.id.btnSendSMS);
        btnCallMobile = findViewById(R.id.btnCallMobile);
        numberThisMobile = numberMobile.getText().toString();
        txtSMSThisMobile = txtSMS.getText().toString();
    }

    private void sendSMS(){
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_SEND);
                Uri uri = Uri.parse(txtSMSThisMobile);
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }

    private void callMobile(){
        btnCallMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);
                }
                Uri  uri = Uri.parse("tel:+7 (495) 152-55-28");
                intent = new Intent(Intent.ACTION_CALL, uri);
                // Uri uri = Uri.parse(numberThisMobile);
                startActivity(intent);
            }
        });
    }
}
