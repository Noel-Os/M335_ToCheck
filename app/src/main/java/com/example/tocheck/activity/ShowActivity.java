package com.example.tocheck.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tocheck.R;
import com.example.tocheck.model.ToDo;

import java.util.Date;

public class ShowActivity extends AppCompatActivity {

    private TextView title;
    private TextView date;

    private Bitmap img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        title = findViewById(R.id.todo_title);
        date = findViewById(R.id.date);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);
        ToDo toDo = MainActivity.getInstance().toDoList.get((int) id);
        title.setText(toDo.getTitle());
        date.setText(toDo.getDate().toString());

    }
}