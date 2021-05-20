package com.example.freezerstorage.FreezerContent;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contents_table")
public class Item {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String contentsName;
    private String amount;
    private int freezerId;
    private int priority;

    public Item(String contentsName, String amount, int freezerId, int priority) {
        this.contentsName = contentsName;
        this.amount = amount;
        this.freezerId = freezerId;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContentsName() {
        return contentsName;
    }

    public void setContentsName(String name) {
        this.contentsName = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getFreezerId() {
        return freezerId;
    }

    public void setFreezerId(int freezerId) {
        this.freezerId = freezerId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}