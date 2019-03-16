package com.example.skyjar.dormitoryapp.Entities.LoginEntites;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.skyjar.dormitoryapp.Entities.User;

public class LoginSessionService extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE_NAME = "tblAccount";
    public static final String COLNAME1 = "fullname";
    public static final String COLNAME2 = "email";
    public static final String COLNAME3 = "sex";
    public static final String COLNAME4 = "phone";
    public static final String COLNAME5 = "birthdate";
    public static final String COLNAME6 = "id";


    public LoginSessionService(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (fullname TEXT, email TEXT, sex TEXT, phone TEXT, " +
                "birthdate TEXT, id INTERGER)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(User user) {
        deleteAll();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLNAME1, user.getFullname());
        contentValues.put(COLNAME2, user.getEmail());
        contentValues.put(COLNAME3, user.getSex());
        contentValues.put(COLNAME4, user.getPhone());
        contentValues.put(COLNAME5, user.getBirthdate());
        contentValues.put(COLNAME6, user.getId());

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        if (result == -1) return false;
        return true;
    }

    public int getUserId() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        int userId = -1;
        if(res.moveToNext())
            userId = res.getInt(5);
        db.close();
        return userId;
    }

    public User getUserInfo() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        User user = null;
        if(res.moveToNext()){
            user = new User();
            user.setFullname(res.getString(0));
            user.setEmail(res.getString(1));
            user.setSex(res.getString(2));
            user.setPhone(res.getString(3));
            user.setBirthdate(res.getString(4));
            user.setId(res.getInt(5));
        }
        db.close();
        return user;
    }

    public boolean deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.close();
        return true;
    }
}
