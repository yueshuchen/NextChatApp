package com.example.ysc8v.nextchatapp.main;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.ysc8v.nextchatapp.R;

import java.io.File;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout layoutswitch;
    private ImageView faceImage;

    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESULT_REQUEST_CODE = 2;
    private static final String IMAGE_FILE_NAME = "profilepicture.jpg";

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        layoutswitch = findViewById(R.id.switch_picture);
        faceImage = findViewById(R.id.face);
        layoutswitch.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }

    }


    @Override
    public void onClick(View v) {
        showDialog(this);
    }



    private void showDialog(Context context) {
        final Context c = context;
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_title)
                .setItems(R.array.dialog_arrays, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:

                                Intent intentFromGallery = new Intent();
                                intentFromGallery.setType("image/*");
                                intentFromGallery
                                        .setAction(Intent.ACTION_GET_CONTENT);

                                startActivityForResult(intentFromGallery,
                                        IMAGE_REQUEST_CODE);
                                break;

                            case 1:
                                Intent intentFromCapture = new Intent(
                                        MediaStore.ACTION_IMAGE_CAPTURE);
                                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                                File outPutImage = new File(path, IMAGE_FILE_NAME);
                                try {
                                    if (outPutImage.exists()) {
                                        outPutImage.delete();
                                    }
                                    outPutImage.createNewFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                intentFromCapture.putExtra(
                                        MediaStore.EXTRA_OUTPUT,
                                        Uri.fromFile(outPutImage));
                                startActivityForResult(intentFromCapture,CAMERA_REQUEST_CODE);
                                break;
                        }
                }})
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_CANCELED) {

            switch (requestCode) {
                case IMAGE_REQUEST_CODE:
                    ContentValues contentValues = new ContentValues(1);
                    contentValues.put(MediaStore.Images.Media.DATA, data.getData().getPath());

                    Uri imageUri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                    data.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startPhotoZoom(imageUri);
                    break;
                case CAMERA_REQUEST_CODE:

                    File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                    File tempFile = new File(path,IMAGE_FILE_NAME);
                    startPhotoZoom(Uri.fromFile(tempFile));

                    break;
                case RESULT_REQUEST_CODE:
                    if (data != null) {
                        Bundle extras = data.getExtras();
                        if (extras != null) {
                            Bitmap photo = extras.getParcelable("data");
                            Drawable drawable = new BitmapDrawable(this.getResources(),photo);
                            faceImage.setImageDrawable(drawable);
                        }

                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void startPhotoZoom(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("scale", "true");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);


        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }



}
