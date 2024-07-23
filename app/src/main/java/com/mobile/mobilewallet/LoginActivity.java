package com.mobile.mobilewallet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    HelperClass helperClass;
    private EditText etPassword;
    private boolean isPasswordVisible = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_real);
        helperClass = new HelperClass();
        etPassword = findViewById(R.id.editTextPassword);


        Button signup = findViewById(R.id.button);
        signup.setOnClickListener(x->{
            boolean isValid = throwEmptyNessError(new int[]{R.id.editTextPassword, R.id.et_EmailAddress});
            if (isValid){
                SharedPreferences sp = getSharedPreferences("userLoginStatus", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                String userStr = sp.getString("user", null);
                if (userStr != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<HashMap<String, String>>() {}.getType();
                    HashMap<String, String> user = gson.fromJson(userStr, type);
                    user.replace("userLoginStatus", String.valueOf(true));
                    editor.putString("user", gson.toJson(user));
                    editor.apply();
                    Intent intent = new Intent(this, DashboardActivity.class);
                    intent.putExtra("user", gson.toJson(user));
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "you dont have an account", Toast.LENGTH_LONG).show();
                }
            }
        });

        etPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (etPassword.getRight() - etPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        togglePasswordVisibility();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.not_visible, 0);
        } else {
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visible, 0);
        }
        isPasswordVisible = !isPasswordVisible;
        etPassword.setSelection(etPassword.getText().length());
    }
    public boolean throwEmptyNessError(int[] ids) {
        int count = 0;
        for (int id : ids) {
            EditText editText = findViewById(id);
            if (editText.getText().toString().trim().isEmpty()) {
                editText.setError("Fields cannot be empty");
                count++;
            }
        }
        if (count == 0){
            return true;
        }
        return false;
    }
}