package com.example.tocheck.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tocheck.R;
import com.example.tocheck.model.ToDo;

import java.util.ArrayList;

public class DoneActivity extends AppCompatActivity {

    private static DoneActivity instance;

    ArrayList<ToDo> allToDos = new ArrayList<>();
    ArrayList<ToDo> toDos = new ArrayList<>();
    ArrayList<String> toDosS = new ArrayList<>();
    ListView checked_list;

    public static DoneActivity getInstance(){
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        checked_list = findViewById(R.id.checked_list);
        instance = this;
        checked_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ShowActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("from", "done");
                startActivity(intent);
            }
        });

        this.allToDos = MainActivity.getInstance().allToDos;
    }

    public Activity getActivity(){
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadToDos();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, toDosS);
        checked_list.setAdapter(arrayAdapter);
    }

    public void loadToDos(){
        ArrayList<ToDo> save = new ArrayList<>();
        save.addAll(toDos);
        toDos.clear();
        if (allToDos.size() != 0) {
            for (ToDo toDo:allToDos){
                if (toDo.getDone()){
                    toDos.add(toDo);
                }
            }
            toDosS.clear();
            for (ToDo toDo:toDos) {
                toDosS.add(toDo.getTitle());
            }
        }
    }
}