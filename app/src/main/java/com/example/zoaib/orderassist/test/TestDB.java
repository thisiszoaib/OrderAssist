package com.example.zoaib.orderassist.test;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.example.zoaib.orderassist.data.OrderAssistDBHelper;

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

}
