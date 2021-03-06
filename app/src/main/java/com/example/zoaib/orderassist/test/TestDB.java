package com.example.zoaib.orderassist.test;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.example.zoaib.orderassist.data.OrderAssistContract.*;
import com.example.zoaib.orderassist.data.OrderAssistDBHelper;

import java.util.Map;
import java.util.Set;

/**
 * Created by Zoaib on 8/10/2015.
 */
public class TestDB extends AndroidTestCase {

    public void testCreateDb() throws Throwable
    {
        mContext.deleteDatabase(OrderAssistDBHelper.DATABASE_NAME);
        SQLiteDatabase db = new OrderAssistDBHelper(this.mContext).getWritableDatabase();
        assertEquals(true,db.isOpen());
        db.close();
    }

    public void testInsertReadDb() throws Throwable
    {
        SQLiteDatabase db = new OrderAssistDBHelper(mContext).getWritableDatabase();

        ContentValues values = getFoodItemTypeContentValues();

        long foodItemTypeRowId;
        foodItemTypeRowId = db.insert(FoodItemType.TABLE_NAME,null,values);

        assertTrue(foodItemTypeRowId != -1);
        assertEquals(foodItemTypeRowId,34);

        Cursor cursor = db.query(
                FoodItemType.TABLE_NAME,
                null,
                null,
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
        values.put(FoodItemType._ID,34);
        values.put(FoodItemType.COLUMN_NAME,"Drinks");
        values.put(FoodItemType.COLUMN_CREATED_DATE,"20150811");
        values.put(FoodItemType.COLUMN_IMAGE_URL,"testUrl");

        return values;
    }



}
