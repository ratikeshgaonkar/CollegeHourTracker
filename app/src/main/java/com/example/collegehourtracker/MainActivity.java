package com.example.collegehourtracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Displays the launch screen briefly, then opens the home screen.
 *
 * The delayed navigation is cancelled when this Activity is destroyed so
 * that the Handler does not retain a stale Activity reference.
 */
public class MainActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY_MILLIS = 5000L;

    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private final Runnable openHome = new Runnable() {
        @Override
        public void run() {
            if (isFinishing() || isDestroyed()) {
                return;
            }

            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainHandler.postDelayed(openHome, SPLASH_DELAY_MILLIS);
    }

    @Override
    protected void onDestroy() {
        // Prevent delayed navigation from running after this Activity is gone.
        mainHandler.removeCallbacks(openHome);
        super.onDestroy();
    }
}
