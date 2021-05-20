package com.example.freezerstorage.FreezerListing;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "freezer_table")
public class Freezer {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int priority;

    public Freezer(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}