package com.example.freezerstorage.FreezerContent;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.example.freezerstorage.FreezerListing.Freezer;
import com.example.freezerstorage.DAO.FreezerViewModel;
import com.example.freezerstorage.FreezerListing.Overview;
import com.example.freezerstorage.R;

import java.util.ArrayList;

public class AddContents extends AppCompatActivity {

    private EditText editText;
    private EditText editText1;
    private Button addButton;
    private Button cancelButton;
    private FreezerViewModel freezerViewModel;
    ArrayList<Freezer> freezerList = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contents);
        Intent intent = getIntent();

        editText = findViewById(R.id.contentnamefield);
        editText1 = findViewById(R.id.amountfield);
        addButton = findViewById(R.id.addcontentbutton);
        cancelButton = findViewById(R.id.canceladd);
        freezerViewModel = new ViewModelProvider(this).get(FreezerViewModel.class);

        // getting information from previous intent about freezerId For database
        int frId = intent.getIntExtra(ThisFreezerOverview.ThisIdFreezer,0);

        //Button for adding items to database
        addButton.setOnClickListener(v -> {
            String check1 = editText.getText().toString();
            String check2 = editText1.getText().toString();
            if (!(TextUtils.isEmpty(check1))) {
                if(!(TextUtils.isEmpty(check2))) {
                    freezerViewModel.cInsert(new Item(editText.getText().toString(), editText1.getText().toString(), frId,1));
                    Intent intent2 = new Intent(this, Overview.class);
                    Toast.makeText(this, check1 +" added to freezer", Toast.LENGTH_SHORT).show();
                } else editText1.setError("This Field Cannot be empty");
            } else {
                editText.setError("This Field Cannot be empty");
            }
            Intent intent1 = new Intent(this, Overview.class);
            startActivity(intent1);
        });

        //Button for canceling adding items and going back to freezer overview
        cancelButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, Overview.class);
            startActivity(intent1);
        });
    }
}

