package com.mobile.mobilewallet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mobile.mobilewallet.Objects.TransactionHistory;
import com.mobile.mobilewallet.adapter.TransactionAdapter;
import com.mobile.mobilewallet.adapter.TransferAdapter;

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

        RecyclerView recentTransfer = findViewById(R.id.recentTransfer);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recentTransfer.setLayoutManager(layoutManager);
        List<TransactionHistory> histories = new ArrayList<>();
        TransferAdapter transferAdapter = new TransferAdapter(histories,this);
        recentTransfer.setAdapter(transferAdapter);

        RecyclerView transactionMade = findViewById(R.id.latestTran);
        RecyclerView.LayoutManager transLayoutManager = new LinearLayoutManager(this);
        transactionMade.setLayoutManager(transLayoutManager);
        TransactionAdapter transactionAdapter = new TransactionAdapter(new ArrayList<>(), this);
        transactionMade.setAdapter(transactionAdapter);

    }
    public void viewAllTrans(View view){
        Intent intent = new Intent(this, TransactionHistory.class);
        startActivity(intent);
    }
}