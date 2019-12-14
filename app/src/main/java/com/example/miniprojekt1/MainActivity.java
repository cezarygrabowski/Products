package com.example.miniprojekt1;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.miniprojekt1.database.MyDB;
import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
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
