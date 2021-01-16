package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button button;
    ImageView empty_imageview;
TextView no_data;
MyDatabaseHelper myDB;
ArrayList<String> book_id, book_title, book_author, book_pages;
CustomAdapter customAdapter;
private FloatingActionButton fb_add_activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView = findViewById(R.id.recyclerview);
        button = findViewById(R.id.button);

        myDB = new MyDatabaseHelper(MenuActivity.this);
        book_id = new ArrayList<>();
        book_title =new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();
        storeDataInArrays();
        customAdapter = new CustomAdapter(MenuActivity.this, this, book_id, book_title, book_author, book_pages);
        recyclerView.setLayoutManager(new LinearLayoutManager(MenuActivity.this));
        recyclerView.setAdapter(customAdapter);
                 fb_add_activity=findViewById(R.id.fb_add);
                 fb_add_activity.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         startActivity(new Intent(MenuActivity.this,AddActivity.class));
                     }
                 });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor=  myDB.readAllData();
        if (cursor.getCount()==0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();

        }else {
            while (cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.delete_all){
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
            MyDatabaseHelper myDB=new MyDatabaseHelper(this);
            myDB.deleteAllData();
            Intent intent1= new Intent(this, MenuActivity.class);
            startActivity(intent1);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
public void zala(View v){

            Intent intent = new Intent(MenuActivity.this, AddActivity.class);
            startActivity(intent);
        }




}
