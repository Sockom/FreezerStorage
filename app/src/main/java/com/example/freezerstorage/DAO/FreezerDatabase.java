package com.example.freezerstorage.DAO;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.freezerstorage.FreezerListing.Freezer;

@androidx.room.Database(entities = {Freezer.class}, version = 1)
public abstract class FreezerDatabase extends RoomDatabase {

    private static FreezerDatabase instance;

    public abstract FreezerDao freezerDao();

    public static synchronized FreezerDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context, FreezerDatabase.class, "freezer_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }


}
