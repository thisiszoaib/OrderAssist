package com.example.zoaib.orderassist.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Zoaib on 8/11/2015.
 */
public class OrderAssistProvider extends ContentProvider {

    public static final int FOOD_ITEM_TYPE = 100;
    public static final int FOOD_ITEM_TYPE_ID = 101;

    public static final int FOOD_ITEM = 200;
    public static final int FOOD_ITEM_ID = 201;
    public static final int FOOD_ITEM_WITH_VENDOR_AND_TYPE = 202;

    public static final int FOOD_VENDOR = 300;
    public static final int FOOD_VENDOR_ID = 301;

    public static final int ORDER = 400;
    public static final int ORDER_ID = 401;

    public static final int ORDER_DETAIL = 500;
    public static final int ORDER_DETAIL_WITH_ORDER_ID = 501;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private OrderAssistDBHelper mOpenHelper;

    private static UriMatcher buildUriMatcher()
    {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = OrderAssistContract.CONTENT_AUTHORITY;

        matcher.addURI(authority,OrderAssistContract.PATH_FOOD_ITEM_TYPE,FOOD_ITEM_TYPE);
        matcher.addURI(authority,OrderAssistContract.PATH_FOOD_ITEM_TYPE + "/#",FOOD_ITEM_TYPE_ID);

        matcher.addURI(authority,OrderAssistContract.PATH_FOOD_ITEM,FOOD_ITEM);
        matcher.addURI(authority,OrderAssistContract.PATH_FOOD_ITEM + "/#",FOOD_ITEM_ID);
        matcher.addURI(authority,OrderAssistContract.PATH_FOOD_ITEM + "/#/#",FOOD_ITEM_WITH_VENDOR_AND_TYPE);

        matcher.addURI(authority,OrderAssistContract.PATH_FOOD_VENDOR,FOOD_VENDOR);
        matcher.addURI(authority,OrderAssistContract.PATH_FOOD_VENDOR + "/#",FOOD_VENDOR_ID);

        matcher.addURI(authority,OrderAssistContract.PATH_FOOD_VENDOR,FOOD_VENDOR);
        matcher.addURI(authority,OrderAssistContract.PATH_FOOD_VENDOR + "/#",FOOD_VENDOR_ID);

        matcher.addURI(authority,OrderAssistContract.PATH_ORDER,ORDER);
        matcher.addURI(authority,OrderAssistContract.PATH_ORDER + "/#",ORDER_ID);

        matcher.addURI(authority,OrderAssistContract.PATH_ORDER_DETAIL,ORDER_DETAIL);
        matcher.addURI(authority,OrderAssistContract.PATH_ORDER_DETAIL + "/#",ORDER_DETAIL_WITH_ORDER_ID);

        return matcher;

    }



    @Override
    public boolean onCreate() {
        mOpenHelper = new OrderAssistDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor retCursor;

        switch(sUriMatcher.match(uri))
        {
            case FOOD_ITEM_TYPE_ID:
                retCursor = mOpenHelper.getReadableDatabase().query(
                        OrderAssistContract.FoodItemType.TABLE_NAME,
                        projection,
                        OrderAssistContract.FoodItemType._ID + " = '" + ContentUris.parseId(uri) + "'",
                        null,
                        null,
                        null,
                        sortOrder
                );
                break;

            case FOOD_ITEM_TYPE:
                retCursor = mOpenHelper.getReadableDatabase().query(
                        OrderAssistContract.FoodItemType.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;

            case FOOD_ITEM_ID:
                retCursor = mOpenHelper.getReadableDatabase().query(
                        OrderAssistContract.FoodItem.TABLE_NAME,
                        projection,
                        OrderAssistContract.FoodItem._ID + " = '" + ContentUris.parseId(uri) + "'",
                        null,
                        null,
                        null,
                        sortOrder
                );
                break;

            case FOOD_ITEM:
                retCursor = mOpenHelper.getReadableDatabase().query(
                        OrderAssistContract.FoodItem.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;

            case FOOD_VENDOR_ID:
                retCursor = mOpenHelper.getReadableDatabase().query(
                        OrderAssistContract.FoodVendor.TABLE_NAME,
                        projection,
                        OrderAssistContract.FoodVendor._ID + " = '" + ContentUris.parseId(uri) + "'",
                        null,
                        null,
                        null,
                        sortOrder
                );
                break;

            case FOOD_VENDOR:
                retCursor = mOpenHelper.getReadableDatabase().query(
                        OrderAssistContract.FoodVendor.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;

            case ORDER_ID:
                retCursor = mOpenHelper.getReadableDatabase().query(
                        OrderAssistContract.Order.TABLE_NAME,
                        projection,
                        OrderAssistContract.Order._ID + " = '" + ContentUris.parseId(uri) + "'",
                        null,
                        null,
                        null,
                        sortOrder
                );
                break;

            case ORDER:
                retCursor = mOpenHelper.getReadableDatabase().query(
                        OrderAssistContract.Order.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;

            case ORDER_DETAIL_WITH_ORDER_ID:
                retCursor = mOpenHelper.getReadableDatabase().query(
                        OrderAssistContract.OrderDetail.TABLE_NAME,
                        projection,
                        OrderAssistContract.OrderDetail.COLUMN_ORDER_ID + " = '" + ContentUris.parseId(uri) + "'",
                        null,
                        null,
                        null,
                        sortOrder
                );
                break;

            case ORDER_DETAIL:
                retCursor = mOpenHelper.getReadableDatabase().query(
                        OrderAssistContract.OrderDetail.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(),uri);

        return retCursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch(match)
        {
            case FOOD_ITEM_TYPE:
                return OrderAssistContract.FoodItemType.CONTENT_TYPE;
            case FOOD_ITEM_TYPE_ID:
                return OrderAssistContract.FoodItemType.CONTENT_ITEM_TYPE;
            case FOOD_ITEM:
                return OrderAssistContract.FoodItem.CONTENT_TYPE;
            case FOOD_ITEM_ID:
                return OrderAssistContract.FoodItem.CONTENT_ITEM_TYPE;
            case FOOD_VENDOR:
                return OrderAssistContract.FoodVendor.CONTENT_TYPE;
            case FOOD_VENDOR_ID:
                return OrderAssistContract.FoodVendor.CONTENT_ITEM_TYPE;
            case ORDER:
                return OrderAssistContract.Order.CONTENT_TYPE;
            case ORDER_ID:
                return OrderAssistContract.Order.CONTENT_ITEM_TYPE;
            case ORDER_DETAIL:
                return OrderAssistContract.OrderDetail.CONTENT_TYPE;
            case ORDER_DETAIL_WITH_ORDER_ID:
                return OrderAssistContract.OrderDetail.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;
        switch(match)
        {
            case FOOD_ITEM_TYPE:{
                long _id = db.insert(OrderAssistContract.FoodItemType.TABLE_NAME,
                        null,
                        values);
                if(_id > 0)
                    returnUri = OrderAssistContract.FoodItemType.buildFoodItemTypeUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case FOOD_ITEM:{
                long _id = db.insert(OrderAssistContract.FoodItem.TABLE_NAME,
                        null,
                        values);
                if(_id > 0)
                    returnUri = OrderAssistContract.FoodItem.buildFoodItemUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case FOOD_VENDOR:{
                long _id = db.insert(OrderAssistContract.FoodVendor.TABLE_NAME,
                        null,
                        values);
                if(_id > 0)
                    returnUri = OrderAssistContract.FoodVendor.buildFoodVendorUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case ORDER:{
                long _id = db.insert(OrderAssistContract.Order.TABLE_NAME,
                        null,
                        values);
                if(_id > 0)
                    returnUri = OrderAssistContract.Order.buildOrderUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case ORDER_DETAIL:{
                long _id = db.insert(OrderAssistContract.OrderDetail.TABLE_NAME,
                        null,
                        values);
                if(_id > 0)
                    returnUri = OrderAssistContract.OrderDetail.buildOrderDetailUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);

        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted=0;
        switch(match)
        {
            case ORDER:{
                rowsDeleted = db.delete(OrderAssistContract.Order.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            }
            case ORDER_DETAIL:{
                rowsDeleted = db.delete(OrderAssistContract.OrderDetail.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            }
            case FOOD_ITEM:{
                rowsDeleted = db.delete(OrderAssistContract.FoodItem.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            }
            case FOOD_ITEM_TYPE:{
                rowsDeleted = db.delete(OrderAssistContract.FoodItemType.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            }
            case FOOD_VENDOR:{
                rowsDeleted = db.delete(OrderAssistContract.FoodVendor.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }

        if(selection == null || rowsDeleted != 0)
            getContext().getContentResolver().notifyChange(uri,null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
