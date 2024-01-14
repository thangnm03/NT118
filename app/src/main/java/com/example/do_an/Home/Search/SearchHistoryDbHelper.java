package com.example.do_an.Home.Search;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SearchHistoryDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "search_history.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "search_history";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SEARCH_TERM = "search_term";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_SEARCH_TERM + " TEXT);";

    public SearchHistoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private static final int MAX_HISTORY_ITEMS = 20;

    public void insertSearchTerm(String searchTerm) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Kiểm tra xem chuỗi đã tồn tại hay chưa
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_SEARCH_TERM + " = ?", new String[]{searchTerm}, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            // Chuỗi đã tồn tại, xóa nó
            db.delete(TABLE_NAME, COLUMN_SEARCH_TERM + " = ?", new String[]{searchTerm});
        }

        cursor.close();

        // Kiểm tra số lượng mục
        cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        int currentItemCount = cursor.getCount();
        cursor.close();

        if (currentItemCount >= MAX_HISTORY_ITEMS) {
            // Xóa mục cũ nhất
            String deleteQuery = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID +
                    " IN (SELECT " + COLUMN_ID + " FROM " + TABLE_NAME +
                    " ORDER BY " + COLUMN_ID + " LIMIT ?)";
            db.execSQL(deleteQuery, new Object[]{(currentItemCount - MAX_HISTORY_ITEMS + 1)});
        }

        // Thêm mục mới vào
        ContentValues values = new ContentValues();
        values.put(COLUMN_SEARCH_TERM, searchTerm);
        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();

        // Xóa hết dữ liệu trong bảng
        db.delete(TABLE_NAME, null, null);

        db.close();
    }


}
