package com.example.zoaib.orderassist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.zoaib.orderassist.data.OrderAssistContract.*;

/**
 * Created by Zoaib on 8/10/2015.
 */
public class OrderAssistDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;

    public static final String DATABASE_NAME = "orderAssist.db";

    public OrderAssistDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_FOOD_ITEM_TYPE_TABLE =
                "CREATE TABLE " + FoodItemType.TABLE_NAME + " (" +
                        FoodItemType._ID + " INTEGER PRIMARY KEY, " +
                        FoodItemType.COLUMN_NAME + " TEXT NOT NULL, " +
                        FoodItemType.COLUMN_IMAGE_URL + " TEXT, " +
                        FoodItemType.COLUMN_CREATED_DATE + " TEXT);";

        final String SQL_CREATE_FOOD_ITEM_TABLE =
                "CREATE TABLE " + FoodItem.TABLE_NAME + " (" +
                        FoodItem._ID + " INTEGER PRIMARY KEY, " +
                        FoodItem.COLUMN_FOOD_VENDOR_ID + " INTEGER NOT NULL, " +
                        FoodItem.COLUMN_FOOD_ITEM_TYPE_ID + " INTEGER NOT NULL, " +
                        FoodItem.COLUMN_NAME + " TEXT NOT NULL, " +
                        FoodItem.COLUMN_IMAGE_URL + " TEXT, " +
                        FoodItem.COLUMN_STATUS + " TEXT, " +
                        FoodItem.COLUMN_CREATED_DATE + " TEXT," +
                        FoodItem.COLUMN_UNIT_PRICE + " REAL," +

                        " FOREIGN KEY (" + FoodItem.COLUMN_FOOD_ITEM_TYPE_ID + ") REFERENCES " +
                        FoodItemType.TABLE_NAME + " (" + FoodItemType._ID + "), " +

                        " FOREIGN KEY (" + FoodItem.COLUMN_FOOD_VENDOR_ID + ") REFERENCES " +
                        FoodVendor.TABLE_NAME + " (" + FoodVendor._ID + "));";

        final String SQL_CREATE_FOOD_VENDOR_TABLE =
                "CREATE TABLE " + FoodVendor.TABLE_NAME + " (" +
                        FoodVendor._ID + " INTEGER PRIMARY KEY, " +
                        FoodVendor.COLUMN_NAME + " TEXT NOT NULL, " +
                        FoodVendor.COLUMN_ADDRESS + " TEXT, " +
                        FoodVendor.COLUMN_CONTACT + " TEXT, " +
                        FoodVendor.COLUMN_COORD_LAT + " REAL, " +
                        FoodVendor.COLUMN_COORD_LONG + " REAL, " +
                        FoodVendor.COLUMN_IMAGE_URL + " TEXT, " +
                        FoodVendor.COLUMN_STATUS + " TEXT, " +
                        FoodVendor.COLUMN_CREATED_DATE + " TEXT);";

        final String SQL_CREATE_ORDER_TABLE =
                "CREATE TABLE " + Order.TABLE_NAME + " (" +
                        Order._ID + " INTEGER PRIMARY KEY, " +
                        Order.COLUMN_IS_DELIVERED + " TEXT NOT NULL, " +
                        Order.COLUMN_CREATED_BY + " TEXT, " +
                        Order.COLUMN_STATUS + " TEXT, " +
                        Order.COLUMN_CREATED_DATE + " TEXT);";

        final String SQL_CREATE_ORDER_DETAIL_TABLE =
                "CREATE TABLE " + OrderDetail.TABLE_NAME + " (" +
                        OrderDetail._ID + " INTEGER PRIMARY KEY, " +
                        OrderDetail.COLUMN_ORDER_ID + " INTEGER NOT NULL, " +
                        OrderDetail.COLUMN_FOOD_ITEM_ID + " INTEGER NOT NULL, " +
                        OrderDetail.COLUMN_QUANTITY + " INTEGER NOT NULL, " +
                        OrderDetail.COLUMN_STATUS + " TEXT," +

                        " FOREIGN KEY (" + OrderDetail.COLUMN_ORDER_ID + ") REFERENCES " +
                        Order.TABLE_NAME + " (" + Order._ID + "), " +

                        " FOREIGN KEY (" + OrderDetail.COLUMN_FOOD_ITEM_ID + ") REFERENCES " +
                        FoodItem.TABLE_NAME + " (" + FoodItem._ID + "));";

        db.execSQL(SQL_CREATE_FOOD_ITEM_TYPE_TABLE);
        db.execSQL(SQL_CREATE_FOOD_VENDOR_TABLE);
        db.execSQL(SQL_CREATE_FOOD_ITEM_TABLE);
        db.execSQL(SQL_CREATE_ORDER_TABLE);
        db.execSQL(SQL_CREATE_ORDER_DETAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + OrderDetail.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Order.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FoodItem.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FoodItemType.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FoodVendor.TABLE_NAME);
        onCreate(db);
    }
}
