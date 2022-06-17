package com.example.fooducate.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.fooducate.db.Scan;
import com.example.fooducate.db.User;

import java.util.List;

@Dao
public interface UserDao {

    @Transaction
    @Insert
    long insertUser(User user);

    @Insert
    void insertScan(List<Scan> scans);

    @Query("DELETE FROM scan WHERE barcode like :code")
    void deleteByScanBarcode(String code);

    @Query("SELECT EXISTS(SELECT * FROM user WHERE firebaseUserId like :id)")
    boolean isDataExist(String id);
}
