package com.group4.otpgenerator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final Button button = findViewById(R.id.buttonOTP);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String key = "NHOM4";
                long time = System.currentTimeMillis() / 1000 / 30;
                String plaintext = key + time;
                String hashedText = getSHAHash(plaintext);

                TextView tv;
                tv = (TextView) findViewById(R.id.tvOTP);
                tv.setText(hashedText);

            }
        });


    }


    public static String getSHAHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            String full = convertByteToHex(messageDigest);
            full = full.substring(full.length() - 6);

            return full;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public static String convertByteToHex(byte[] data) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }


}
