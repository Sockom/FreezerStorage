package com.example.freezerstorage.FreezerListing;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freezerstorage.DAO.FreezerViewModel;
import com.example.freezerstorage.FreezerContent.Contents;
import com.example.freezerstorage.R;
import com.example.freezerstorage.FreezerContent.SearchContents;
import com.example.freezerstorage.FreezerContent.ThisFreezerOverview;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Overview extends AppCompatActivity implements FreezerAdapter.OnListItemClickListener {

    private FreezerViewModel freezerViewModel;
    private ArrayList<Freezer> freezers;
    private FreezerAdapter.ViewHolder viewHolder;
    private FreezerAdapter mFreezerAdapter;
    private TextView textView;
    private ImageButton imageButton;

    //message for putExtra
    public static final String IdFreezer = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        Intent intent = getIntent();
        textView = findViewById(R.id.freezer_name);


        //setting up recyclerview
        RecyclerView recyclerView = findViewById(R.id.recyclerViewFreezer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();
        freezerViewModel = new ViewModelProvider(this).get(FreezerViewModel.class);
        freezerViewModel.getAllFreezers().observe(this, freezers -> {
            mFreezerAdapter = new FreezerAdapter((ArrayList)freezers, this);
            recyclerView.setAdapter(mFreezerAdapter);
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
    }

//Button to add freezer intent
    public void addFreezer(View view){
        Intent intent2 = new Intent(this, AddFreezer.class);
        startActivity(intent2);
    }

    //onClicklistener that opens specific freezer
    @Override
    public void onListItemClick(Freezer freezer) {
        Intent intent3 = new Intent(this, ThisFreezerOverview.class);
        intent3.putExtra(IdFreezer, freezer.getId());
        startActivity(intent3);
    }

}