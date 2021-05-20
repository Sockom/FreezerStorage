package com.example.freezerstorage.DAO;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.freezerstorage.FreezerListing.Freezer;

import java.util.ArrayList;
import java.util.List;

    @Dao
    public interface FreezerDao {

        @Insert
        void insert(Freezer freezer);

        @Update
        void update(Freezer freezer);

        @Delete
        void delete(Freezer freezer);

        @Query("SELECT * FROM freezer_table ORDER BY priority DESC")
        LiveData<List<Freezer>> getAllFreezers();

        @Query("SELECT * FROM freezer_table WHERE id = :id")
        LiveData<Freezer> getFreezer(int id);

        @Query("DELETE FROM freezer_table WHERE id = :id")
        void deleteFreezer(int id);
    }
