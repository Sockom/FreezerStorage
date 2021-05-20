package com.example.freezerstorage.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.freezerstorage.FreezerContent.Item;
import com.example.freezerstorage.FreezerListing.Freezer;

import java.util.List;

import javax.sql.DataSource;

@Dao
public interface ContentsDao {
    @Insert
    void cInsert(Item item);

    @Update
    void cUpdate(Item item);

    @Delete
    void cDelete(Item item);

    @Query("SELECT * FROM contents_table ORDER BY priority DESC")
    LiveData<List<Item>> getAllItems();

    @Query("SELECT * FROM contents_table WHERE freezerId = :FreezerId")
    LiveData<List<Item>> getAllItemsById(int FreezerId);

    @Query("DELETE FROM contents_table WHERE FreezerId = :FreezerId")
    void deleteAllItems(int FreezerId);


}
