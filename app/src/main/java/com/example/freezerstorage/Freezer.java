package com.example.freezerstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Freezer {
    private final String name;
    private final int id;
    private final int imageId;

    public Freezer(String name, int id, int imageId) {
        this.name = name;
        this.id = id;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public int getImageId() {
        return imageId;
    }
}