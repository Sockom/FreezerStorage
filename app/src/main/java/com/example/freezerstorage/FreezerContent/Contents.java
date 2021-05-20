package com.example.freezerstorage.FreezerContent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freezerstorage.DAO.FreezerDao;
import com.example.freezerstorage.DAO.FreezerViewModel;
import com.example.freezerstorage.FreezerListing.Freezer;
import com.example.freezerstorage.FreezerListing.FreezerAdapter;
import com.example.freezerstorage.FreezerListing.Overview;
import com.example.freezerstorage.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Contents extends AppCompatActivity implements ContentAdapter.OnListItemClickListener {
    private FreezerViewModel freezerViewModel;
    private ContentAdapter mContentAdapter;
    private int toastId;
    private String toastList;
    private MutableLiveData<Freezer> thisFreezer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);
        Intent intent = getIntent();


        //setting up recyclerview
        RecyclerView recyclerView = findViewById(R.id.recyclerViewAllContents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();
        freezerViewModel = new ViewModelProvider(this).get(FreezerViewModel.class);
        freezerViewModel.getAllItems().observe(this, items -> {
            mContentAdapter = new ContentAdapter((ArrayList) items, this);
            recyclerView.setAdapter(mContentAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        });

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



        //Sets right nav item as highlighted (I HATE FRAGMENTS!)
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);


    }

    //OnClickListener that deletes items from freezer
    @Override
    public void onListItemClick(Item item) {
        freezerViewModel.cDelete(item);
        Toast.makeText(this, "Content deleted ", Toast.LENGTH_SHORT).show();
    }
}



