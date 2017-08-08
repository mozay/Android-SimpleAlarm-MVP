package endsstudios.alarmapp.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import endsstudios.alarmapp.Model.AlarmModel;

/**
 * Created by MEHMET on 7.08.2017.
 */

public class DBHandler extends SQLiteOpenHelper{


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "alarms";

    // Contacts table name
    private static final String TABLE_ALARM= "setAlarm";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_MINUTE = "minute";
    private static final String KEY_HOUR = "hour";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_ALARM + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_HOUR + " INTEGER,"
                + KEY_MINUTE + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARM);

        // Create tables again
        onCreate(db);
    }

    public void addAlarm(AlarmModel alarm) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HOUR, alarm.getHour()); // Contact Name
        values.put(KEY_MINUTE, alarm.getMinute()); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_ALARM, null, values);
        db.close(); // Closing database connection
    }

    public List<AlarmModel> getAllAlarm() {
        List<AlarmModel> alarmList = new ArrayList<AlarmModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ALARM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AlarmModel alarm = new AlarmModel();
                alarm.setID(Integer.parseInt(cursor.getString(0)));
                alarm.setHour(cursor.getInt(1));
                alarm.setMinute(cursor.getInt(2));
                // Adding contact to list
                alarmList.add(alarm);
            } while (cursor.moveToNext());
        }

        // return contact list
        return alarmList;
    }



    public int updateAlarm(int id,int hour,int minute) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HOUR, hour);
        values.put(KEY_MINUTE, minute);

        // updating row
        return db.update(TABLE_ALARM, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }


    public void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ALARM, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

}
