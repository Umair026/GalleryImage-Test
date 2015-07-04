package com.shiraz.panacloud.testproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import static com.shiraz.panacloud.testproject.R.id.image;


public class MainActivity extends ActionBarActivity {

    Button button;
    ImageView imageView;
    Bitmap bitmap1 = null;
    ProgressDialog progressDialog;
    SeekBar seekBar;
    TextView textView;
    int Value;
    int changeValue;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(image);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.text);
        editText = (EditText) findViewById(R.id.text2);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Value = (seekBar.getProgress()) * 1;
                Log.d("Tag","" + seekBar.getProgress());
                textView.setText(Value+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, 1);
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equalsIgnoreCase("") || s==null) {
                    seekBar.setProgress(0);
                } else {
                    changeValue = Integer.parseInt(s.toString());
                    seekBar.setProgress(changeValue);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



//        progressDialog = ProgressDialog.show(this,"Creating","loading",true,false);
    }

    @Override
      public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(Activity.RESULT_OK == resultCode && data !=null) {
            //get the Image from data
            Uri seletedImage = data.getData();
            //Method 1
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
        }else{
            Toast.makeText(this, "You haven't picked Image",
                    Toast.LENGTH_LONG).show();
        }
            }


}
