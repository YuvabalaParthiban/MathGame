//internet code refered for writting this
package com.example.callback.mathgames;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class DbScore extends SQLiteOpenHelper {
    String user;

    public DbScore(Context context) {
        super(context, "MathScore.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table oddoneout(username Text,score int)");
        db.execSQL("Create Table divisibility(username Text,score int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists divisibility");
        db.execSQL("drop Table if exists oddoneout");
    }


    public Boolean insertOddoneoutScore(String username, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("score", score);
        long result = db.insert("oddoneout", null, contentValues);
        if (result == -1) {
            return false;

        } else
            return true;

    }

    public int getOddoneoutScore(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        int maximum = 0;
        Cursor cursor = db.rawQuery("SELECT MAX(score) as maximum from oddoneout WHERE username = ?", new String[]{name});

        if (cursor.moveToFirst()) {
            maximum = cursor.getInt(cursor.getColumnIndex("maximum"));
        }
        return maximum;
    }

    public Boolean insertDivisibility(String username, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("score", score);
        long cresult = db.insert("divisibility", null, contentValues);
        if (cresult == -1) {
            return false;

        } else
            return true;

    }

    public int getDivisibility(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        int maximum = 0;
        Cursor cursor = db.rawQuery("SELECT MAX(score) as maximum from divisibility WHERE username = ?", new String[]{name});

        if (cursor.moveToFirst()) {
            maximum = cursor.getInt(cursor.getColumnIndex("maximum"));
        }
        return maximum;
    }


}
