package com.example.madproject;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.madproject.R;

public class UpdateActivity extends AppCompatActivity {
    EditText title_input, author_input,pages_input;
    Button update_button, delete_button;
    String id,title,author,pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        pages_input = findViewById(R.id.pages_input);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //first we call this
        getAndSetIntentData();

        // set Actionbar title after getAndSetIntent method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateData(id,title,author,pages);
            }
        });


    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author")&& getIntent().hasExtra("pages")){
            //getting data from intent
            id=getIntent().getStringExtra("id");
            title=getIntent().getStringExtra( "title");
            author=getIntent().getStringExtra("author");
            pages=getIntent().getStringExtra("pages");

            //setting data
            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);

        }
        else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }


}