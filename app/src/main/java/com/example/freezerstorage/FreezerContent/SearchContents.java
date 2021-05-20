package com.example.freezerstorage.FreezerContent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freezerstorage.DAO.FreezerViewModel;
import com.example.freezerstorage.FreezerListing.Overview;
import com.example.freezerstorage.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class SearchContents extends AppCompatActivity implements ContentAdapter.OnListItemClickListener {
    private FreezerViewModel freezerViewModel;
    private ContentAdapter mContentAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        searchView = findViewById(R.id.searchbar);


        // Bottom nav bar stuff
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (item -> {
                    if (item.getItemId() == R.id.action_freezers) {
                        Intent intent1 = new Intent(this, Overview.class);
                        startActivity(intent1);
                    }
                    if (item.getItemId() == R.id.action_contents) {
                        Intent intent1 = new Intent(this, Contents.class);
                        startActivity(intent1);
                    }
                    if (item.getItemId() == R.id.action_search) {
                        Intent intent1 = new Intent(this, SearchContents.class);
                        startActivity(intent1);
                    }
                    return true;
                });

        //setting up recyclerview
        RecyclerView recyclerView = findViewById(R.id.recyclerViewSearchContent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();
        freezerViewModel = new ViewModelProvider(this).get(FreezerViewModel.class);
        freezerViewModel.getSearchedItems().observe(this, items -> {
            mContentAdapter = new ContentAdapter((ArrayList) items, this);
            recyclerView.setAdapter(mContentAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

        });


        //Sets right nav item as highlighted (I HATE FRAGMENTS!)
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);


        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                freezerViewModel.itemSearch(newText);
                return false;
            }
        });
        }

    // Search stuff
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public void onListItemClick(Item item){

    }
}
