package com.example.tocheck.model;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.Date;

public class ToDo {

    private String title;
    private Date date;
    private Bitmap img;
    private boolean done;

    public  ToDo(String title, Date date, Bitmap img){
        this.title = title;
        this.date = date;
        this.img = img;
        this.done = false;
    }

    public String getTitle(){
        return this.title;
    }

}
