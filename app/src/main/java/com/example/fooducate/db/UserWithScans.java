package com.example.fooducate.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.fooducate.db.Scan;
import com.example.fooducate.db.User;

import java.util.List;

public class UserWithScans {

    @Embedded
    public User user;
    @Relation(
            parentColumn = "firebaseUserId",
            entityColumn = "scanId"
    )
    public List<Scan> scans;

    public UserWithScans(User user, List<Scan> scans) {
        this.user = user;
        this.scans = scans;
    }



}
