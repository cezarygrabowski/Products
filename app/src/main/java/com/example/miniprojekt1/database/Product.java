package com.example.miniprojekt1.database;

import android.icu.text.Replaceable;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey
    @NonNull
    public Integer pid;

    @ColumnInfo(name = "product_name")
    public String productName;

    @ColumnInfo(name = "price")
    public int price;

    @ColumnInfo(name = "amount")
    public int amount;

    @ColumnInfo(name = "isSold")
    public boolean isSold;
}