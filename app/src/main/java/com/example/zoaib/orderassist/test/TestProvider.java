package com.example.zoaib.orderassist.test;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

import com.example.zoaib.orderassist.data.OrderAssistContract;
import com.example.zoaib.orderassist.data.OrderAssistContract.FoodItem;
import com.example.zoaib.orderassist.data.OrderAssistContract.FoodItemType;
import com.example.zoaib.orderassist.data.OrderAssistContract.FoodVendor;
import com.example.zoaib.orderassist.data.OrderAssistContract.Order;
import com.example.zoaib.orderassist.data.OrderAssistContract.OrderDetail;

import java.util.Map;
import java.util.Set;

/**
 * Created by Zoaib on 8/11/2015.
 */
public class TestProvider extends AndroidTestCase {

    public static final int foodItemTypeId = 36;
    public static final int foodItemId = 35;
    public static final int foodVendorId = 12;
    public static final int orderId = 13;
    public static final int orderDetailId = 15;

    public void deleteAllRecords()
    {
        mContext.getContentResolver().delete(Order.CONTENT_URI,
                null,
                null);

        mContext.getContentResolver().delete(OrderDetail.CONTENT_URI,
                null,
                null);

        mContext.getContentResolver().delete(FoodItem.CONTENT_URI,
                null,
                null);

        mContext.getContentResolver().delete(FoodItemType.CONTENT_URI,
                null,
                null);

        mContext.getContentResolver().delete(FoodVendor.CONTENT_URI,
                null,
                null);

        Cursor cursor = mContext.getContentResolver().query(
                Order.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        assertEquals(cursor.getCount(), 0);

        cursor.close();

        cursor = mContext.getContentResolver().query(
                FoodItem.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        assertEquals(cursor.getCount(),0);

        cursor.close();

    }

    public void testType()
    {
        String type = mContext.getContentResolver().getType(Order.CONTENT_URI);

        assertEquals(type,Order.CONTENT_TYPE);

        type = mContext.getContentResolver().getType(Order.buildOrderUri(orderId));

        assertEquals(type, Order.CONTENT_ITEM_TYPE);

        type = mContext.getContentResolver().getType(OrderDetail.CONTENT_URI);

        assertEquals(type,OrderDetail.CONTENT_TYPE);

        type = mContext.getContentResolver().getType(OrderDetail.buildOrderDetailUri(orderDetailId));

        assertEquals(type,OrderDetail.CONTENT_ITEM_TYPE);

        type = mContext.getContentResolver().getType(FoodItem.CONTENT_URI);

        assertEquals(type,FoodItem.CONTENT_TYPE);

        type = mContext.getContentResolver().getType(FoodItem.buildFoodItemUri(foodItemId));

        assertEquals(type,FoodItem.CONTENT_ITEM_TYPE);

        type = mContext.getContentResolver().getType(FoodItemType.CONTENT_URI);

        assertEquals(type,FoodItemType.CONTENT_TYPE);

        type = mContext.getContentResolver().getType(FoodItemType.buildFoodItemTypeUri(foodItemTypeId));

        assertEquals(type,FoodItemType.CONTENT_ITEM_TYPE);

        type = mContext.getContentResolver().getType(FoodVendor.CONTENT_URI);

        assertEquals(type,FoodVendor.CONTENT_TYPE);

        type = mContext.getContentResolver().getType(FoodVendor.buildFoodVendorUri(foodVendorId));

        assertEquals(type,FoodVendor.CONTENT_ITEM_TYPE);


    }

    public void testInsertReadProvider() throws Throwable
    {

        deleteAllRecords();

        //Food Item Type
        ContentValues values = getFoodItemTypeContentValues();

        Uri returnUri;
        returnUri = mContext.getContentResolver().insert(OrderAssistContract.FoodItemType.CONTENT_URI,values);
        long foodItemTypeRowId = ContentUris.parseId(returnUri);

        assertEquals(foodItemTypeId,foodItemTypeRowId);

        Cursor cursor = mContext.getContentResolver().query(
                OrderAssistContract.FoodItemType.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        if(cursor.moveToFirst())
        {
            validateCursor(values,cursor);
        }
        else
        {
            assertFalse(true);
        }

        //Food Vendor
        values = getFoodVendorContentValues();

        returnUri = mContext.getContentResolver().insert(OrderAssistContract.FoodVendor.CONTENT_URI,values);

        assertEquals(foodVendorId,ContentUris.parseId(returnUri));

        cursor = mContext.getContentResolver().query(
                OrderAssistContract.FoodVendor.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        if(cursor.moveToFirst())
        {
            validateCursor(values,cursor);
        }
        else
        {
            assertFalse(true);
        }


        //Food Items
        values = getFoodItemContentValues();

        returnUri = mContext.getContentResolver().insert(OrderAssistContract.FoodItem.CONTENT_URI,values);

        assertEquals(foodItemId,ContentUris.parseId(returnUri));

        cursor = mContext.getContentResolver().query(
                OrderAssistContract.FoodItem.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        if(cursor.moveToFirst())
        {
            validateCursor(values,cursor);
        }
        else
        {
            assertFalse(true);
        }

        //Order
        values = getOrderContentValues();

        returnUri = mContext.getContentResolver().insert(OrderAssistContract.Order.CONTENT_URI,values);

        assertEquals(orderId,ContentUris.parseId(returnUri));

        cursor = mContext.getContentResolver().query(
                OrderAssistContract.Order.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        if(cursor.moveToFirst())
        {
            validateCursor(values,cursor);
        }
        else
        {
            assertFalse(true);
        }

        //Order Detail
        values = getOrderDetailContentValues();

        returnUri = mContext.getContentResolver().insert(OrderAssistContract.OrderDetail.CONTENT_URI,values);

        assertEquals(orderDetailId,ContentUris.parseId(returnUri));

        cursor = mContext.getContentResolver().query(
                OrderAssistContract.OrderDetail.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        if(cursor.moveToFirst())
        {
            validateCursor(values,cursor);
        }
        else
        {
            assertFalse(true);
        }

    }


    static public void validateCursor(ContentValues expectedValues,Cursor valueCursor)
    {
        Set<Map.Entry<String,Object>> valueSet = expectedValues.valueSet();

        for(Map.Entry<String,Object> entry : valueSet)
        {
            String columnName = entry.getKey();
            int idx = valueCursor.getColumnIndex(columnName);
            assertFalse(idx == -1);

            String expectedValue = entry.getValue().toString();
            String actualValue = valueCursor.getString(idx);
            assertEquals(expectedValue, actualValue);

        }

    }

    ContentValues getFoodItemTypeContentValues()
    {

        ContentValues values = new ContentValues();
        values.put(OrderAssistContract.FoodItemType._ID, foodItemTypeId);
        values.put(OrderAssistContract.FoodItemType.COLUMN_NAME,"Chinese");
        values.put(OrderAssistContract.FoodItemType.COLUMN_CREATED_DATE,"20150811");
        values.put(OrderAssistContract.FoodItemType.COLUMN_IMAGE_URL,"testUrl");

        return values;
    }

    ContentValues getFoodItemContentValues()
    {

        ContentValues values = new ContentValues();
        values.put(FoodItem._ID, foodItemId);
        values.put(FoodItem.COLUMN_NAME,"Shashlick with Rice");
        values.put(FoodItem.COLUMN_FOOD_ITEM_TYPE_ID,foodItemTypeId);
        values.put(FoodItem.COLUMN_FOOD_VENDOR_ID, foodVendorId);
        values.put(FoodItem.COLUMN_STATUS,"Available");
        values.put(FoodItem.COLUMN_UNIT_PRICE,130);

        return values;
    }

    ContentValues getOrderContentValues()
    {

        ContentValues values = new ContentValues();
        values.put(Order._ID, orderId);
        values.put(Order.COLUMN_CREATED_DATE,"20150812");
        values.put(Order.COLUMN_IS_DELIVERED,"Yes");
        values.put(Order.COLUMN_STATUS, "Paid");

        return values;
    }

    ContentValues getOrderDetailContentValues()
    {

        ContentValues values = new ContentValues();
        values.put(OrderDetail._ID, orderDetailId);
        values.put(OrderDetail.COLUMN_FOOD_ITEM_ID,foodItemId);
        values.put(OrderDetail.COLUMN_ORDER_ID,orderId);
        values.put(OrderDetail.COLUMN_QUANTITY, 3);

        return values;
    }

    ContentValues getFoodVendorContentValues()
    {

        ContentValues values = new ContentValues();
        values.put(FoodVendor._ID, foodVendorId);
        values.put(FoodVendor.COLUMN_NAME,"Ginsoy Extreme");
        values.put(FoodVendor.COLUMN_ADDRESS,"Sindhi Muslim Society");
        values.put(FoodVendor.COLUMN_IMAGE_URL, "testUrl");
        values.put(FoodVendor.COLUMN_STATUS, "Open");

        return values;
    }

}
