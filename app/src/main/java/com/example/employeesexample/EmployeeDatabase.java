package com.example.employeesexample;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Employee.class}, version = 2)
public abstract class EmployeeDatabase extends RoomDatabase {

    private static EmployeeDatabase instance;

    public abstract EmployeeDao employeeDao();

    static final Migration MIGRATION = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE employee_table ADD COLUMN salary INTEGER NOT NULL DEFAULT 4000");
        }
    };

    public static synchronized EmployeeDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    EmployeeDatabase.class,
                    "employee_database")
                    .addCallback(roomCallback)
                    .addMigrations(MIGRATION)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    EmployeeDao dao = instance.employeeDao();

                    for (int i = 1; i <=10; i++){
                       dao.insert(new Employee("Name : " +i, 2000));
                    }
                }
            }).start();
        }
    };
}