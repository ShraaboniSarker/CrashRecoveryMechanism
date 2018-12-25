package com.example.techland.crashrecoverysystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CustomErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_error);
//        TextView errorDetailsText = findViewById(R.id.error_details);
//        //errorDetailsText.setText(CustomActivityOnCrash.getStackTraceFromIntent(getIntent()));
//
//        Button restartButton = findViewById(R.id.restart_button);
//
//        final CaocConfig config = CustomActivityOnCrash.getConfigFromIntent(getIntent());
//
//
//        if (config == null) {
//            //This should never happen - Just finish the activity to avoid a recursive crash.
//            finish();
//            return;
//        }
//
//        if (config.isShowRestartButton() && config.getRestartActivityClass() != null) {
//            restartButton.setText("Restart App!");
//            restartButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    CustomActivityOnCrash.restartApplication(CustomErrorActivity.this, config);
//                }
//            });
//        } else {
//            restartButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    CustomActivityOnCrash.closeApplication(CustomErrorActivity.this, config);
//                }
//            });
//        }
    }
    }

