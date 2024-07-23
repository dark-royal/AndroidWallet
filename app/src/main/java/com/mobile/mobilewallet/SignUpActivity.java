package com.mobile.mobilewallet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.gson.Gson;

import java.util.LinkedHashMap;

public class SignUpActivity extends AppCompatActivity {
    private EditText etPassword;
    private EditText etEmail;
    private boolean isPasswordVisible = false;
    HelperClass helperClass = new HelperClass();
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etPassword = findViewById(R.id.et_confirmpassword);
        etEmail = findViewById(R.id.et_email);

        Button signup = findViewById(R.id.button);
        signup.setOnClickListener(x->{
            boolean isValid = throwEmptyNessError(new int[]{R.id.et_confirmpassword, R.id.et_email, R.id.et_name});
            String errorMessage = helperClass.validatePassword(etPassword.getText().toString());
            etPassword.setError(errorMessage);
            if (isValid && errorMessage == null){
                Intent intent = new Intent(this, DashboardActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences("userLoginStatus", MODE_PRIVATE);
                int startFrom = sharedPreferences.getInt("userId", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                LinkedHashMap<String, String> user = new LinkedHashMap<>();
                user.put("userId", String.valueOf(startFrom+1));
                user.put("userLoginStatus", String.valueOf(true));
                user.put("userEmail", etEmail.getText().toString());
                user.put("userPassword", etEmail.getText().toString());
                Gson gson = new Gson();
                editor.putString("user", gson.toJson(user));
                editor.apply();
                startActivity(intent);
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

    public void back(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
        }
