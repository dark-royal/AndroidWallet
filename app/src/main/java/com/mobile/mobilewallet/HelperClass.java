package com.mobile.mobilewallet;

import android.content.Intent;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HelperClass {

    public String validatePassword(String password) {
        if (password.length() < 8) {
            return "password too short";
        } else {
            StringBuilder pass = new StringBuilder();
            for (int i = 1; i <= 8; i++) {
                pass.append(i);
            }
            if (pass.toString().equals(password)) {
                return "password is too common";
            }
        }
        return null;}};




