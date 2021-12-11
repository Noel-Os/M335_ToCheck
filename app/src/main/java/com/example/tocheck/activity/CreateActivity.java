package com.example.tocheck.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tocheck.R;
import com.example.tocheck.model.ToDo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView preview;
    TextView tw;
    TextView titleInput;
    Date date;
    Bitmap img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        tw = (TextView) findViewById(R.id.tw);
        titleInput = (TextView) findViewById(R.id.titleInput);
    }

    public void takePhoto(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            preview = (ImageView) findViewById(R.id.preview);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            preview.setImageBitmap(imageBitmap);
            this.img = imageBitmap;
        }
    }

    public void openDatePicker(View view){
        DatePickerDialog dpd = new DatePickerDialog (this);
        dpd.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String dateString = Integer.toString(dayOfMonth) + "." + Integer.toString(month) + "." + Integer.toString(year);
                tw.setText(dateString);
                date = new Date(year, month, dayOfMonth);
            }
        });
        dpd.show();
    }

    public void createToDo(View view){
        String t = titleInput.getText().toString();
        ToDo toDo = new ToDo(t, date, img);
        MainActivity.getInstance().allToDos.add(toDo);
        this.finish();
    }
}