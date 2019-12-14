package com.example.miniprojekt1;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.miniprojekt1.product.ListItem;
import com.example.miniprojekt1.product.MyAdapter;
import com.google.android.gms.tasks.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.*;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ArrayList<ListItem> list = new ArrayList<ListItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            setContentView(R.layout.activity_product_list);

                            render(task);
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void render(@NonNull Task<QuerySnapshot> task) {
        for (QueryDocumentSnapshot document : task.getResult()) {
            Log.d("TAG", document.get("pid") + " => " + document.getData());

            list.add(getListItem(document));
            MyAdapter adapter = new MyAdapter(list, getApplicationContext());

            //handle listview and assign adapter
            ListView lView = (ListView)findViewById(R.id.products_recycle_view);
            lView.setAdapter(adapter);

            addGoBackFromProductListOnClickListener();
        }
    }

    private void addGoBackFromProductListOnClickListener() {
        Button goBackFromProductList = findViewById(R.id.go_back_from_product_list);
        goBackFromProductList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack(view);
            }
        });
    }

    private ListItem getListItem(QueryDocumentSnapshot document) {
        ListItem item = new ListItem();
        item.setPid((int) (long) document.get("pid"));
        item.setAmount((int) (long) document.get("amount"));
        item.setPrice((int) (long) document.get("price"));
        item.setName((String) document.get("productName"));
        item.setSold((Boolean) document.get("isSold"));
        return item;
    }

    private void goBack(View view) {
        finish();
    }
}
