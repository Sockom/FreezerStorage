package com.example.freezerstorage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

public class FreezerRepository {
    private final List<Freezer> freezerList;
    private final MutableLiveData<List<Freezer>> searchedFreezer = new MutableLiveData<>();

    public FreezerRepository(){
        freezerList = new ArrayList<>();
        freezerList.add(new Freezer("Bulbasaur", 1, R.drawable.p1));
        freezerList.add(new Freezer("Ivysaur", 2, R.drawable.p2));
        freezerList.add(new Freezer("Venusaur", 3, R.drawable.p3));

        searchedFreezer.setValue(freezerList);
    }

    public void searchFreezer(String query){
        List<Freezer> result = new ArrayList<>();
        for (Freezer f : freezerList) {
            if (f.getName().toLowerCase().contains(query.toLowerCase())) {
                result.add(f);
            }
        }
        searchedFreezer.setValue(result);
    }

    public LiveData<List<Freezer>> getSearchedFreezer() {
        return searchedFreezer;
    }
}