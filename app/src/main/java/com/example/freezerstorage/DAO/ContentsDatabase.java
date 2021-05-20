package com.example.freezerstorage.DAO;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.freezerstorage.FreezerContent.Item;


@androidx.room.Database(entities = {Item.class}, version = 1)
public abstract class ContentsDatabase extends RoomDatabase {

    private static ContentsDatabase instance;

    public abstract ContentsDao contentsDao();

    public static synchronized ContentsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, ContentsDatabase.class, "contents_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}