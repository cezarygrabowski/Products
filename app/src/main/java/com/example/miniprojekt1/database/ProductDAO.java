package com.example.miniprojekt1.database;

import android.database.Cursor;
import androidx.room.*;

import java.util.List;

@Dao
public interface ProductDAO {
    @Query("SELECT * FROM product")
    Cursor getAllInContentProvider();

    @Query("SELECT * FROM product")
    List<Product> getAll();

    @Query("SELECT * FROM product WHERE pid IN (:productIds)")
    List<Product> loadAllByIds(int[] productIds);

    @Query("SELECT * FROM product WHERE pid = :pid LIMIT 1")
    Product loadById(int pid);

    @Query("SELECT * FROM product WHERE product_name LIKE :productname AND " +
            "price LIKE :price LIMIT 1")
    Product findByProductAndPrice(String productname, int price);

    @Insert
    void insertAll(Product... products);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);
}