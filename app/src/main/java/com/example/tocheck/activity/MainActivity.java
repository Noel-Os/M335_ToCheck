package com.example.tocheck.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tocheck.R;
import com.example.tocheck.model.ToDo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;
    ArrayList<ToDo> toDoList = new ArrayList<ToDo>();
    ArrayList<String> toDoListS = new ArrayList<String>();
    ListView list;

    public static MainActivity getInstance(){
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        list = (ListView) findViewById(R.id.list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ShowActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

    }

    public Activity getActivity(){
        return this;
    }

    public void toCreateActivity(View view){
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    public void loadToDos(){
        toDoListS.clear();
        for (ToDo toDo:toDoList) {
            toDoListS.add(toDo.getTitle());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadToDos();
        if(toDoList.size() > 0){
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, toDoListS);
            list.setAdapter(arrayAdapter);
        }

    }
}