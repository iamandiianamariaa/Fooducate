package com.example.fooducate.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.fooducate.utils.Converters;

@androidx.room.Database(entities = {
        User.class,
        Scan.class
}
        , version = 4
        , exportSchema = false
)
@TypeConverters({Converters.class})
public abstract class Database extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile Database INSTANCE;
    public static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    System.out.println("S-A CREAT!!!!");
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    Database.class, "db_example")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

