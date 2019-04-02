package com.example.sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDbAdapter {

    MyDbHelper helper;

    public MyDbAdapter(Context context)
    {
        this.helper = new MyDbHelper(context);
    }

    static class MyDbHelper extends SQLiteOpenHelper
    {

        // Table Name
        public static final String TABLE_NAME = "Product_Table";

        // Table columns
        public static final String ID = "id";
        private static final String NAME = "name";
        private static final String quantity= "qty";
        // Database Information
        static final String DB_NAME = "JAVATECHIG_TODOS.DB";

        // database version
        static final int DB_VERSION = 1;

        // Creating table query
        private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + quantity + " NUMBER);";

        public MyDbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public long insertData(Product product)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDbHelper.NAME, product.getName());
        contentValues.put(MyDbHelper.quantity, product.getQty());
        long id = db.insert(MyDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public Product findProductById(int id){
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {MyDbHelper.ID, MyDbHelper.NAME, MyDbHelper.quantity};
        String selection = MyDbHelper.ID+" = "+id;

        Cursor cursor = db.query(MyDbHelper.TABLE_NAME,projection,selection,null,null,null,null);

        Product product = new Product();
        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            product.setId(Integer.parseInt(cursor.getString(0)));
            product.setName(cursor.getString(1));
            product.setQty(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        }

        return product;
    }

    public List<Product> getList() {
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {MyDbHelper.ID,
                MyDbHelper.NAME, MyDbHelper.quantity};
        Cursor cursor = db.query(
                MyDbHelper.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        List<Product> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Product product = new Product(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)));
            list.add(product);
        }
        cursor.close();
        return list;
    }

    public void deleteProduct(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(" DELETE FROM " + MyDbHelper.TABLE_NAME + "WHERE" + MyDbHelper.ID + "='" + id +"'");
        db.close();
    }
    public long updateData(Product product){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MyDbHelper.NAME, product.getName());
        cv.put(MyDbHelper.quantity,product.getQty());
        long id = db.update(MyDbHelper.TABLE_NAME,cv,MyDbHelper.ID+"="+String.valueOf(product.getId()),null);
        return id;
    }
}
