package com.example.zoaib.orderassist.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Zoaib on 8/10/2015.
 */
public class OrderAssistContract {

    public static final String CONTENT_AUTHORITY = "com.example.zoaib.orderassist";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_ORDER = "order";
    public static final String PATH_VENDOR = "foodVendor";
    public static final String PATH_FOOD_ITEM = "foodItem";

    public static final class Order implements BaseColumns
    {
        public static final String TABLE_NAME = "orders";

        public static final String COLUMN_ORDER_ID = "orderId";

        //public static final String COLUMN_USER_ID = "user_id";

        public static final String COLUMN_IS_DELIVERED = "isDelivered";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_CREATED_DATE = "createdDate";
        public static final String COLUMN_CREATED_BY = "createdBy";

    }

    public static final class OrderDetail implements BaseColumns
    {
        public static final String TABLE_NAME = "orderDetail";

        public static final String COLUMN_ORDER_DETAIL_ID = "orderDetailId";
        public static final String COLUMN_ORDER_ID = "orderId";

        public static final String COLUMN_FOOD_ITEM_ID = "foodItemId";
        public static final String COLUMN_QUANTITY = "quantity";

        public static final String COLUMN_STATUS = "status";

    }

    public static final class FoodVendor implements BaseColumns
    {
        public static final String TABLE_NAME = "foodVendor";

        public static final String COLUMN_FOOD_VENDOR_ID = "foodVendorId";
        public static final String COLUMN_NAME = "name";

        public static final String COLUMN_IMAGE_URL = "imageUrl";
        public static final String COLUMN_ADDRESS = "address";

        public static final String COLUMN_CONTACT = "contactNumber";
        public static final String COLUMN_COORD_LAT = "latitude";
        public static final String COLUMN_COORD_LONG = "longitude";
        public static final String COLUMN_STATUS = "status";

        public static final String COLUMN_CREATED_DATE = "createdDate";



    }

    public static final class FoodItem implements BaseColumns
    {
        public static final String TABLE_NAME = "foodItem";

        public static final String COLUMN_FOOD_ITEM_ID = "foodItemId";
        public static final String COLUMN_FOOD_VENDOR_ID = "foodVendorId";

        public static final String COLUMN_FOOD_ITEM_TYPE_ID = "foodItemTypeId";
        public static final String COLUMN_NAME = "name";

        public static final String COLUMN_IMAGE_URL = "imageUrl";
        public static final String COLUMN_UNIT_PRICE = "unitPrice";
        public static final String COLUMN_STATUS = "status";

        public static final String COLUMN_CREATED_DATE = "createdDate";
    }

    public static final class FoodItemType implements BaseColumns
    {
        public static final String TABLE_NAME = "foodItemType";

        public static final String COLUMN_FOOD_ITEM_TYPE_ID = "foodItemTypeId";
        public static final String COLUMN_NAME = "name";

        public static final String COLUMN_IMAGE_URL = "imageUrl";
        public static final String COLUMN_STATUS = "status";

        public static final String COLUMN_CREATED_DATE = "createdDate";
    }

}
