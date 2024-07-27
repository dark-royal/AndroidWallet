package com.mobile.mobilewallet;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.mobile.mobilewallet.helperclass.ContextDeterminer;

public class MyAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
    }
    public void nextScreen(View view){
        ContextDeterminer contextDetermine = new ContextDeterminer();
        Class<?> context = contextDetermine.nextScreenDeterminator(view);
        Intent intent = new Intent(this, context);
        this.startActivity(intent);
        finish();
    }
}