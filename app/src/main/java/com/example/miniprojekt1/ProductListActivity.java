package com.example.miniprojekt1;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.miniprojekt1.database.MyDB;
import com.example.miniprojekt1.database.Product;
import com.example.miniprojekt1.product.ListItem;
import com.example.miniprojekt1.product.MyAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        //instantiate custom adapter
        MyAdapter adapter = new MyAdapter(this.getItems(), this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.products_recycle_view);
        lView.setAdapter(adapter);

        Button goBackFromProductList = findViewById(R.id.go_back_from_product_list);
        goBackFromProductList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack(view);
            }
        });
    }

    private void goBack(View view) {
        finish();
    }

    public ArrayList<ListItem> getItems() {
        ArrayList<ListItem> list = new ArrayList<ListItem>();

        MyDB db = MyDB.getDatabase(getApplicationContext());
        for (Product product: db.productDAO().getAll()) {
            ListItem test1 = new ListItem();
            test1.setPid(product.pid);
            test1.setAmount(product.amount);
            test1.setPrice(product.price);
            test1.setName(product.productName);
            test1.setSold(product.isSold);
            list.add(test1);
        }

        return list;
    }
}
