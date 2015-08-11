package com.example.zoaib.orderassist.test;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

import com.example.zoaib.orderassist.data.OrderAssistContract;

import java.util.Map;
import java.util.Set;

/**
 * Created by Zoaib on 8/11/2015.
 */
public class TestProvider extends AndroidTestCase {

    public void testInsertReadProvider() throws Throwable
    {

        ContentValues values = getFoodItemTypeContentValues();

        Uri returnUri;
        returnUri = mContext.getContentResolver().insert(OrderAssistContract.FoodItemType.CONTENT_URI,values);
        long foodItemTypeRowId = ContentUris.parseId(returnUri);

        assertEquals(foodItemTypeRowId,34);

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
        values.put(OrderAssistContract.FoodItemType._ID,36);
        values.put(OrderAssistContract.FoodItemType.COLUMN_NAME,"Drinks");
        values.put(OrderAssistContract.FoodItemType.COLUMN_CREATED_DATE,"20150811");
        values.put(OrderAssistContract.FoodItemType.COLUMN_IMAGE_URL,"testUrl");

        return values;
    }

}
