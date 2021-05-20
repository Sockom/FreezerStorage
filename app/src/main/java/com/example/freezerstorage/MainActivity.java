package com.example.freezerstorage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.freezerstorage.FreezerContent.Contents;
import com.example.freezerstorage.FreezerContent.SearchContents;
import com.example.freezerstorage.FreezerListing.Overview;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
// buttons to get to the 3 main intents
    public void freezerList(View view){
       Intent intent = new Intent(this, Overview.class);
       startActivity(intent);
    }
    public void contentsList(View view){
        Intent intent = new Intent(this, Contents.class);
        startActivity(intent);
    }
    public void searchList(View view){
        Intent intent = new Intent(this, SearchContents.class);
        startActivity(intent);
    }
}
