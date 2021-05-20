package com.example.freezerstorage.FreezerListing;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.freezerstorage.DAO.FreezerViewModel;
import com.example.freezerstorage.R;


public class AddFreezer extends AppCompatActivity {

    private EditText editText;
    private FreezerViewModel freezerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_freezer);
        Intent intent = getIntent();
        editText = findViewById(R.id.freezernamefield);
        freezerViewModel = new ViewModelProvider(this).get(FreezerViewModel.class);
    }
//cancel button to get back to Overview
    public void cancel(View view) {
        Intent intent1 = new Intent(this, Overview.class);
        startActivity(intent1);
    }
// creates freezer and adds it to room database, added fun error
    public void createFreezer(View view) {
        String check = editText.getText().toString();
        if (!(TextUtils.isEmpty(check))) {
            freezerViewModel.insert(new Freezer(editText.getText().toString(), 1));
            Toast.makeText(this, check +" added to list", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(this, Overview.class);
            startActivity(intent1);
        } else {
        editText.setError("This Field Cannot be empty");
        }
    }
}