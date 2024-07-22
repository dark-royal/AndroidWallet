package com.mobile.mobilewallet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SignUpActivity extends AppCompatActivity {
    private EditText etPassword;
    private boolean isPasswordVisible = false;
    HelperClass helperClass = new HelperClass();
    EditText password ;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        password = findViewById(R.id.et_confirmpassword);
        Button signup = findViewById(R.id.button);
        signup.setOnClickListener(x->{
            boolean isValid = throwEmptyNessError(new int[]{R.id.et_confirmpassword, R.id.et_email, R.id.et_name});
            String errorMessage = helperClass.validatePassword(password.getText().toString());
            password.setError(errorMessage);
            if (isValid && errorMessage == null){
                Intent intent = new Intent(this, DashboardActivity.class);
                startActivity(intent);
            }
        });
        etPassword = findViewById(R.id.et_confirmpassword);

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
