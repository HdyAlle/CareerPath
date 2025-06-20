package com.example.careerpath;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity; // Gunakan AppCompatActivity

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_splash_screen); // typo sudah diperbaiki

        // Membuat CareerPath dua warna
        TextView appLogo = findViewById(R.id.app_logo);
        String coloredText = "<font color='#0056D6'><b>Career</b></font><font color='#8E98A7'><b>Path</b></font>";
        appLogo.setText(Html.fromHtml(coloredText));

        // Splash delay 2 detik
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); // efek transisi (opsional)
            finish();
        }, 2000);
    }
}