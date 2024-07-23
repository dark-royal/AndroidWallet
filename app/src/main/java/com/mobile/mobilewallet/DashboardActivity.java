package com.mobile.mobilewallet;

import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mobile.mobilewallet.Objects.TransactionHistory;
import com.mobile.mobilewallet.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        SharedPreferences sharedPreferences = getSharedPreferences("userLoginStatus", MODE_PRIVATE);

        String id = sharedPreferences.getString("user",null);
        assert id != null;

        RecyclerView recyclerView = findViewById(R.id.recentTransfer);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<TransactionHistory> histories = new ArrayList<>();
        MyAdapter myAdapter = new MyAdapter(histories,this);
        recyclerView.setAdapter(myAdapter);
    }
}