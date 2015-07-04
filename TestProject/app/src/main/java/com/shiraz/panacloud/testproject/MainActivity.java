package com.shiraz.panacloud.testproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import static com.shiraz.panacloud.testproject.R.id.image;


public class MainActivity extends ActionBarActivity {

    Button button;
    ImageView imageView;
    Bitmap bitmap1 = null;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(image);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, 1);
            }
        });

//        progressDialog = ProgressDialog.show(this,"Creating","loading",true,false);
    }

    @Override
      public void onActivityResult(int requestCode, int resultCode, Intent data) {
                //get the Image from data
                Uri seletedImage = data.getData();
                String a = seletedImage.getPath();
                String type = a.substring(a.lastIndexOf("/"));
                Log.d("Uri", "" + type);

        try {
            //Method 1
            InputStream in = getContentResolver().openInputStream(seletedImage);
            bitmap1 = BitmapFactory.decodeStream(in);

            //method 2
//          bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(),seletedImage);
            imageView.setImageBitmap(bitmap1);

        } catch (IOException e) {
            e.printStackTrace();
        }
            }


}
