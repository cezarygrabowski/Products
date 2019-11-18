package com.example.miniprojekt1;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.miniprojekt1.database.MyDB;
import com.example.miniprojekt1.database.MyDB_Impl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button launchProductList = (Button) findViewById(R.id.launch_product_list);
        launchProductList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchProductListActivity();
            }
        });

        Button launchOptionsList = (Button) findViewById(R.id.launch_options_list);
        launchOptionsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchOptionsListActivity();
            }
        });

        Button addModifyProduct = (Button) findViewById(R.id.add_modify_product);
        addModifyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addModifyProduct();
            }
        });
    }

    private void addModifyProduct() {
        Intent intent = new Intent(this, AddProduct.class);
        startActivity(intent);
    }

    private void launchOptionsListActivity() {
        Intent intent = new Intent(this, OptionsListActivity.class);
        startActivity(intent);
    }

    private void launchProductListActivity() {
        Intent intent = new Intent(this, ProductListActivity.class);
        startActivity(intent);
    }
}
