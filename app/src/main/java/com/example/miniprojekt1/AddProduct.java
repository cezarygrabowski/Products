package com.example.miniprojekt1;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.miniprojekt1.database.MyDB;
import com.example.miniprojekt1.database.Product;

public class AddProduct extends AppCompatActivity {
    private static final String myIntent = "com.example.product.added";
    private MyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        db = MyDB.getDatabase(getApplicationContext());
        Button addProductButton = (Button) findViewById(R.id.add_product_btn);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct(view);
            }
        });
    }

    private void addProduct(View view) {
        EditText productPidEditText = (EditText) findViewById(R.id.edit_product_pid);
        String productPid = productPidEditText.getText().toString();

        EditText productNameEditText = (EditText) findViewById(R.id.edit_product_name);
        String productName = productNameEditText.getText().toString();

        EditText productAmountEditText = (EditText) findViewById(R.id.edit_product_amount);
        String productAmount = productAmountEditText.getText().toString();

        EditText productPriceEditText = (EditText) findViewById(R.id.edit_product_price);
        String productPrice = productPriceEditText.getText().toString();

        MyDB db = MyDB.getDatabase(getApplicationContext());
        Product product = db.productDAO().loadById(Integer.parseInt(productPid));
        if(product == null){
            product = new Product();
            product.pid = Integer.parseInt(productPid);
            product.amount = Integer.parseInt(productAmount);
            product.productName = productName;
            product.price = Integer.parseInt(productPrice);
            product.isSold = false;
            db.productDAO().insertAll(product);
            Intent intent = new Intent(myIntent);
            intent.putExtra("productName" , productName);
            sendBroadcast(intent, "com.example.my_permissions.MY_PERMISSION");

        } else {
            product.pid = Integer.parseInt(productPid);
            product.amount = Integer.parseInt(productAmount);
            product.productName = productName;
            product.price = Integer.parseInt(productPrice);
            product.isSold = false;
            db.productDAO().update(product);
        }

        finish();
    }
}