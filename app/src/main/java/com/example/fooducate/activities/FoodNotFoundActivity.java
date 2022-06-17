package com.example.fooducate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fooducate.R;

public class FoodNotFoundActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_found);
    }

    public void onPressTry(View view){
        startActivity(new Intent(getApplicationContext(), ScannerActivity.class));
        finish();
    }

    public void onPressHome(View view){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
