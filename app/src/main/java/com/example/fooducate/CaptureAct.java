package com.example.fooducate;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.journeyapps.barcodescanner.CaptureActivity;

public class CaptureAct extends CaptureActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }
}
