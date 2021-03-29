package com.example.freezerstorage;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FreezerList extends AppCompatActivity {

    FreezerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freezer_list);

        Intent intent = getIntent();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        FridgeAdapter adapter = new FridgeAdapter();
        recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(FreezerViewModel.class);
        viewModel.getSearchedFreezer().observe(this, adapter::updateList);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewModel.searchFreezer(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}