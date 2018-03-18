package com.example.ysc8v.nextchatapp.main;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.example.ysc8v.nextchatapp.R;
import com.theartofdev.edmodo.cropper.CropImage;




public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{





    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private AutoCompleteTextView username;
    private AutoCompleteTextView email;
    private AutoCompleteTextView password;
    private AutoCompleteTextView repassword;


    private ImageView faceImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RelativeLayout layoutswitch = findViewById(R.id.switch_picture);

        faceImage = findViewById(R.id.face);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);


        layoutswitch.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }

    }


    @Override
    public void onClick(View v) {
        CropImage.startPickImageActivity(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_CANCELED) {
            if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                Uri imageUri = CropImage.getPickImageResultUri(this, data);
                startPhotoZoom(imageUri);
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                faceImage.setImageURI(result.getUri());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void startPhotoZoom(Uri uri) {
        CropImage.activity(uri)
                .start(this);
    }

    private boolean attemptRegister() {

        username.setError(null);
        email.setError(null);
        password.setError(null);
        repassword.setError(null);

        boolean cancel = false;
        View focusView = null;


        String username_string = username.getText().toString();
        String email_string = email.getText().toString();
        String password_string = password.getText().toString();
        String repassword_string = repassword.getText().toString();


        if (TextUtils.isEmpty(username_string)) {
            //TODO: Firebase check if username is existed or not
            username.setError(getString(R.string.error_field_required));
            focusView = username;
            cancel = true;
        }

        if (TextUtils.isEmpty(email_string)) {
            //TODO: Firebase check if email is existed or not
            email.setError(getString(R.string.error_field_required));
            focusView = email;
            cancel = true;
        }

        if (TextUtils.isEmpty(password_string)) {
            password.setError(getString(R.string.error_field_required));
            focusView = password;
            cancel = true;
        }

        if (TextUtils.isEmpty(repassword_string)) {
            repassword.setError(getString(R.string.error_field_required));
            focusView = repassword;
            cancel = true;
        }

        if (!password.equals(repassword_string)) {
            repassword.setError(getString(R.string.error_password_is_different));
            focusView = repassword;
            cancel = true;
        }

        if (cancel) {

            focusView.requestFocus();
            return false;
        }






        return true;



    }



}
