package com.mobile.mobilewallet;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("userLoginStatus", MODE_PRIVATE);
        int user_id = sp.getInt("user_id", -1);
        Intent intent;
        if (user_id != -1){
            boolean logged = sp.getBoolean("isLoggedIn", false);
            if (logged){
                intent = new Intent(this, DashboardActivity.class);
            }else {
                intent = new Intent(this, LoginActivity.class);
            }
        }else {
            intent = new Intent(this, SignUpActivity.class);
        }
        startActivity(intent);
    }
}