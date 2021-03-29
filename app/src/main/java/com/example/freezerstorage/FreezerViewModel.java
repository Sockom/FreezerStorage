package com.example.freezerstorage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;


public class FreezerViewModel extends ViewModel {

    FreezerRepository repository = new FreezerRepository();

    public void searchFreezer(String query) {
        repository.searchFreezer(query);
    }

    public LiveData<List<Freezer>> getSearchedFreezer() {
        return repository.getSearchedFreezer();
    }

}