package com.example.fooducate.activities;

import android.content.Intent;

import com.example.fooducate.activities.MainActivity;
import com.journeyapps.barcodescanner.CaptureActivity;

public class CaptureAct extends CaptureActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }
}
