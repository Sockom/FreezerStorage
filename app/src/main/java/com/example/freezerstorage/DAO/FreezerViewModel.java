package com.example.freezerstorage.DAO;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.freezerstorage.DAO.FreezerRepository;
import com.example.freezerstorage.FreezerContent.Item;
import com.example.freezerstorage.FreezerListing.Freezer;

import java.util.List;


public class FreezerViewModel extends AndroidViewModel {

    private final FreezerRepository repository;

    public FreezerViewModel(Application app) {
        super(app);
        repository = FreezerRepository.getInstance(app);
    }

    //VM Freezer methods
    public LiveData<List<Freezer>> getAllFreezers() {
        return repository.getAllFreezers();
    }

    public void insert(final Freezer freezer) {
        repository.insert(freezer);
    }

    public void delete(Freezer freezer) {
        repository.delete(freezer);
    }

    public LiveData<Freezer> getFreezer(int id){
        return repository.getFreezer(id);
    }

    //VM Item methods
    public LiveData<List<Item>> getAllItems() {
        return repository.getAllItems();
    }

    public void cInsert(final Item item) {
        repository.cInsert(item);
    }

    public void cDelete(Item item) {
        repository.cDelete(item);
    }

    public void deleteAllItems(int id){
        repository.deleteAllItems(id);
    }

    public LiveData<List<Item>> getAllItemsById(int id){
        return repository.getAllItemsById(id);
    }

    public void itemSearch(String query){
        repository.itemSearch(query);
    }
    public LiveData<List<Item>> getSearchedItems(){
        return repository.getSearchedItems();
    }

}