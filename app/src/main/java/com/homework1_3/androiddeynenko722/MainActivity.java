package com.homework1_3.androiddeynenko722;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 11;
    private static final String LOG = "Request";
    EditText numberMobile;
    EditText txtSMS;
    Button btnSendSMS;
    Button btnCallMobile;
    String numberThisMobile;
    String txtSMSThisMobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        sendSMS();
        callMobile();
    }

    public void init() {
        txtSMS = findViewById(R.id.editTextSMS);
        numberMobile = findViewById(R.id.editNumberMobile);
        btnSendSMS = findViewById(R.id.btnSendSMS);
        btnCallMobile = findViewById(R.id.btnCallMobile);
    }

    private void sendSMS() {
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.SEND_SMS},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    } else {
                        String number = numberMobile.getText().toString();
                        txtSMSThisMobile = txtSMS.getText().toString();
                        SmsManager smgr = SmsManager.getDefault();
                        smgr.sendTextMessage(number, null, txtSMSThisMobile,
                                null, null);
                    }
                } catch (Exception e) {
                    Log.e(LOG, "Не отправляем смс");
                    Toast.makeText(MainActivity.this, getString(R.string.toast),
                            Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    private void callMobile() {
        btnCallMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    } else {
                        numberThisMobile = getString(R.string.number_placeholder,
                                numberMobile.getText().toString());
                        Uri uri = Uri.parse(numberThisMobile);
                        // Uri  uri = Uri.parse("tel:+7 (495) 152-55-28");
                        Intent intent = new Intent(Intent.ACTION_CALL, uri);
                        startActivity(intent);
                    }

                } catch (Exception e) {
                    Log.e(LOG, "Не звоним");
                    Toast.makeText(MainActivity.this, getString(R.string.toast),
                            Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this,
                        getString(R.string.request_permission_true), Toast.LENGTH_LONG).show();
              //  btnCallMobile.setClickable(true);
            } else {
                Toast.makeText(MainActivity.this,
                        getString(R.string.request_permission_false), Toast.LENGTH_LONG).show();
            }

            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this,
                        getString(R.string.request_permission_true), Toast.LENGTH_LONG).show();
               // btnSendSMS.setClickable(true);
            } else {
                Toast.makeText(MainActivity.this,
                        getString(R.string.request_permission_false), Toast.LENGTH_LONG).show();
              //  btnSendSMS.setClickable(false);
            }
        }
    }
}