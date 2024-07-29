package com.mobile.mobilewallet;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.mobile.mobilewallet.helperclass.ContextDeterminer;

public class TransactionHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions_history);

        Spinner spinner = findViewById(R.id.categoryDrop);
        String[] items = {"Item 1", "Item 2", "Item 3"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_transactions_history, R.id.spinner_button, items);
        spinner.setAdapter(adapter);
    }

    public void nextScreen(View view){
        ContextDeterminer contextDetermine = new ContextDeterminer();
        Class<?> context = contextDetermine.nextScreenDeterminator(view);
        Intent intent = new Intent(this, context);
        this.startActivity(intent);
        finish();
    }
}