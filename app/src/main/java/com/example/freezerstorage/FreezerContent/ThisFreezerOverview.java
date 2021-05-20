package com.example.freezerstorage.FreezerContent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import com.example.freezerstorage.DAO.FreezerViewModel;
import com.example.freezerstorage.FreezerListing.Freezer;
import com.example.freezerstorage.FreezerListing.FreezerAdapter;
import com.example.freezerstorage.FreezerListing.Overview;
import com.example.freezerstorage.R;

import java.util.ArrayList;
import java.util.List;

public class ThisFreezerOverview extends AppCompatActivity implements ContentAdapter.OnListItemClickListener {
    private FreezerViewModel freezerViewModel;
    private ArrayList<Freezer> freezers;
    private ContentAdapter.ViewHolder viewHolder;
    private ContentAdapter mContentAdapter;
    private MutableLiveData<Freezer> thisFreezer;


    private ImageView imageView;
    private TextView textView;
    private Button button;

    private int id;
    private RecyclerView recyclerView;
    //message for putExtra
    public static final String ThisIdFreezer = "id";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_freezer_overview);

        //Getting id for the specific freezer from Overview intent
        Intent intent = getIntent();
        id = intent.getIntExtra(Overview.IdFreezer, 0);
        recyclerView = new RecyclerView(this);
//live data
        thisFreezer = new MutableLiveData<>();

//Views
        textView = findViewById(R.id.textviewthisfreezername);
        imageView = findViewById(R.id.freezer_delete);
        button = findViewById(R.id.createcontentbutton);

        //setting up recyclerview
        freezerViewModel = new ViewModelProvider(this).get(FreezerViewModel.class);
        recyclerView = findViewById(R.id.recyclerViewSpecificContent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(mContentAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        freezerViewModel.getAllItemsById(id).observe(this, items -> {
                    mContentAdapter = new ContentAdapter((ArrayList) items, this);
                    recyclerView.setAdapter(mContentAdapter);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                });
// getting information from previous intent about freezer

            freezerViewModel.getFreezer(id).observe(this, freezer -> {
                if (freezer != null) {
                    textView.setText(freezer.getName());
                    thisFreezer.setValue(freezer);
                }
            });

// setting button to delete freezers
            imageView.setOnClickListener(v -> {
                freezerViewModel.delete(thisFreezer.getValue());
                freezerViewModel.deleteAllItems(id);
                Toast.makeText(this, thisFreezer.getValue().getName() + " deleted", Toast.LENGTH_SHORT).show();
                Intent overviewIntent = new Intent(this, Overview.class);
                startActivity(overviewIntent);
            });

// changing activity to add content to freezer
            button.setOnClickListener(v -> {
                Intent addIntent = new Intent(this, AddContents.class);
                addIntent.putExtra(ThisIdFreezer, id);
                startActivity(addIntent);
            });

        }

    @Override
    public void onListItemClick(Item item) {
      freezerViewModel.cDelete(item);
        Toast.makeText(this, item.getContentsName() + " deleted", Toast.LENGTH_SHORT).show();
        }
    }
