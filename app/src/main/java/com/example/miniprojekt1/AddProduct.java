package com.example.miniprojekt1;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.miniprojekt1.database.MyDB;
import com.example.miniprojekt1.database.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddProduct extends AppCompatActivity {
    private static final String myIntent = "com.example.product.added";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

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

        HashMap<String, Object> products = new HashMap<>();
        products.put("pid", Integer.parseInt(productPid));
        products.put("amount", Integer.parseInt(productAmount));
        products.put("productName", productName);
        products.put("price", Integer.parseInt(productPrice));
        products.put("isSold", false);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("products")
                .document(productPid)
                .set(products);
        Intent intent = new Intent(myIntent);
        intent.putExtra("productName" , productName);
        sendBroadcast(intent, "com.example.my_permissions.MY_PERMISSION");

        finish();
    }
}