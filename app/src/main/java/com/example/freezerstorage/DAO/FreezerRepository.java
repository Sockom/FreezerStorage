package com.example.freezerstorage.DAO;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.freezerstorage.FreezerContent.Item;
import com.example.freezerstorage.FreezerContent.SearchContents;
import com.example.freezerstorage.FreezerContent.ThisFreezerOverview;
import com.example.freezerstorage.FreezerListing.Freezer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FreezerRepository {
    private final FreezerDao freezerDao;
    private final ContentsDao contentsDao;
    private final LiveData<List<Freezer>> allFreezers;
    private final LiveData<List<Item>> allItems;


    private final MutableLiveData<List<Item>> searchedContent = new MutableLiveData<>();
    private static FreezerRepository instance;
    private final ExecutorService executorService;

    private FreezerRepository(Application application){
        FreezerDatabase freezerDatabase = FreezerDatabase.getInstance(application);
        ContentsDatabase contentsDatabase = ContentsDatabase.getInstance(application);
        freezerDao = freezerDatabase.freezerDao();
        contentsDao = contentsDatabase.contentsDao();
        allFreezers = freezerDao.getAllFreezers();
        allItems = contentsDao.getAllItems();
        allItems.observeForever(new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                searchedContent.setValue(allItems.getValue());
            }
        });

        executorService = Executors.newFixedThreadPool(4);
    }

    public static FreezerRepository getInstance(Application application){
        if(instance == null)
            instance = new FreezerRepository(application);

        return instance;
    }

    //Freezer stuff
    public LiveData<List<Freezer>> getAllFreezers(){
        return allFreezers;
    }

    public void insert (Freezer freezer) {
        executorService.execute(() -> freezerDao.insert(freezer));
    }

    //Delete method for removing freezers from the database
    public void delete(Freezer freezer)
    {
        new DeleteFreezerAsync(freezerDao).execute(freezer);
    }

    private static class DeleteFreezerAsync extends AsyncTask<Freezer, Void, Void> {
        private final FreezerDao freezerDao;

        private DeleteFreezerAsync(FreezerDao freezerDao) {this.freezerDao = freezerDao;}

        @Override
        protected Void doInBackground(Freezer... freezers){
            freezerDao.delete(freezers[0]);
            return null;
        }
    }

    //get freezer
    public LiveData<Freezer> getFreezer(int id){
        return freezerDao.getFreezer(id);
    }

    //Item stuff
    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }

    public void cInsert (Item item) {
        executorService.execute(() -> contentsDao.cInsert(item));
    }

    //Delete method for removing items from the database
    public void cDelete(Item item){
        new DeleteContentAsync(contentsDao).execute(item);
    }

    private static class DeleteContentAsync extends AsyncTask<Item, Void, Void>{
        private final ContentsDao contentsDao;

        private DeleteContentAsync(ContentsDao contentsDao) {
            this.contentsDao = contentsDao;
        }

        @Override
        protected Void doInBackground(Item... items){
            contentsDao.cDelete(items[0]);
            return null;
            }
        }

    //delete all items in specific freezer
    public void deleteAllItems(int id) {
        executorService.execute(() -> contentsDao.deleteAllItems(id));
        }

    // Wooo fixed, gets items by id in freezers
    public LiveData<List<Item>> getAllItemsById(int id){
        return contentsDao.getAllItemsById(id);
    }

    //Search stuff
        public void itemSearch(String query){
        List<Item> result = new ArrayList<>();
        for(Item i : allItems.getValue()){
            if(i.getContentsName().toLowerCase().contains(query.toLowerCase())){
                result.add(i);
            }
        }
        searchedContent.setValue(result);
    }

    public LiveData<List<Item>> getSearchedItems(){
        return searchedContent;
    }
}


