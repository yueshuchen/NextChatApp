package com.example.ysc8v.nextchatapp.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.ysc8v.nextchatapp.R;

public class RegisterActivity extends AppCompatActivity {

    private RelativeLayout layoutswitch;
    private ImageView faceImage;

    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESULT_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        layoutswitch = findViewById(R.id.switch_picture);
        faceImage = findViewById(R.id.face);
        layoutswitch.setOnClickListener(listener);

    }

    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            showDialog();
        }
    };


    private void showDialog() {

    }
}
