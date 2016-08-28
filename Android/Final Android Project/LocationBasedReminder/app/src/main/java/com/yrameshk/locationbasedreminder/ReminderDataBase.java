package com.yrameshk.locationbasedreminder;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.view.SupportActionModeWrapper;
import android.util.Log;


public class ReminderDataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Loc_Reminder.db";
    public static final String REMINDER_TABLE_NAME = "Reminders";
    public static final String REMINDER_COLUMN_ID = "Id";
    public static final String REMINDER_COLUMN_NAME = "Name";
    public static final String REMINDER_COLUMN_TIMES = "Repeating_Times";
    public static final String REMINDER_COLUMN_REPEAT_MON = "Repeat_Mon";
    public static final String REMINDER_COLUMN_REPEAT_TUE = "Repeat_Tue";
    public static final String REMINDER_COLUMN_REPEAT_WED = "Repeat_Wed";
    public static final String REMINDER_COLUMN_REPEAT_THU = "Repeat_Thu";
    public static final String REMINDER_COLUMN_REPEAT_FRI = "Repeat_Fri";
    public static final String REMINDER_COLUMN_REPEAT_SAT = "Repeat_Sat";
    public static final String REMINDER_COLUMN_REPEAT_SUN = "Repeat_Sun";
    public static final String REMINDER_COLUMN_REPEAT_TIMES = "Repeat_Times";
    public static final String REMINDER_COLUMN_REPEATCOUNTER = "Repeat_Number";
    public static final String REMINDER_COLUMN_LOCATION_LAT = "Location_Lat";
    public static final String REMINDER_COLUMN_LOCATION_LONG = "Location_Long";
    private HashMap hp;

    public ReminderDataBase(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table Reminders " +
                        "(Id integer primary key,Name text,Repeating_Times BIT DEFAULT 1,Repeat_Mon text,Repeat_Tue text,Repeat_Wed text,Repeat_Thu text,Repeat_Fri text,Repeat_Sat text,Repeat_Sun text,Repeat_Times text,Repeat_Number int,Location_Lat text,Location_Long text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Reminders");
        onCreate(db);
    }

    public boolean insertReminder  (String Name, Integer Repeating_Times, String Repeat_Mon, String Repeat_Tue, String Repeat_Wed, String Repeat_Thu, String Repeat_Fri, String Repeat_Sat, String Repeat_Sun, String Repeat_Times, String Repeat_Number,String Location_Lat,String Location_Long)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", Name);
        contentValues.put("Repeating_Times", Repeating_Times);
        contentValues.put("Repeat_Mon", Repeat_Mon);
        contentValues.put("Repeat_Tue", Repeat_Tue);
        contentValues.put("Repeat_Wed", Repeat_Wed);
        contentValues.put("Repeat_Thu", Repeat_Thu);
        contentValues.put("Repeat_Fri", Repeat_Fri);
        contentValues.put("Repeat_Sat", Repeat_Sat);
        contentValues.put("Repeat_Sun", Repeat_Sun);
        contentValues.put("Repeat_Times", Repeat_Times);
        contentValues.put("Repeat_Number", Repeat_Number);
        contentValues.put("Location_Lat", Location_Lat);
        contentValues.put("Location_Long", Location_Long);
        db.insert("Reminders", null, contentValues);
        return true;
    }

    public String getReminderName(int id){
        String Result;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select Name from Reminders where Id="+id+"", null );
        if (res.moveToFirst()) {
            int clmn = res.getColumnIndex(REMINDER_COLUMN_NAME);
            Result = res.getString(clmn);
            return Result;
        }
        return "";
    }

    public int getRepeatingTimes(int id){
        int Result;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select Repeating_Times from Reminders where Id="+id+"", null );
        if (res.moveToFirst()) {
            int clmn = res.getColumnIndex(REMINDER_COLUMN_TIMES);
            Result = Integer.parseInt(res.getString(clmn));
            return Result;
        }
        return 0;
    }

    public Double getLocationLat(int id){
        Double Result;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select Location_Lat from Reminders where Id="+id+"", null );
        if (res.moveToFirst()) {
            int clmn = res.getColumnIndex(REMINDER_COLUMN_LOCATION_LAT);
            if (res.getString(clmn) != null){
            Result = Double.parseDouble(res.getString(clmn));
            return Result;}
            else return 0.0;
        }
        return 0.0;
    }

    public Double getLocationLong(int id){
        Double Result;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select Location_Long from Reminders where Id="+id+"", null );
        if (res.moveToFirst()) {
            int clmn = res.getColumnIndex(REMINDER_COLUMN_LOCATION_LONG);
            if (res.getString(clmn) != null){
                Result = Double.parseDouble(res.getString(clmn));
                return Result;}
            else return 0.0;
        }
        return 0.0;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, REMINDER_TABLE_NAME);
        return numRows;
    }

    public boolean updateReminder (Integer Id, String Name, Integer Repeating_Times, String Repeat_Mon, String Repeat_Tue, String Repeat_Wed, String Repeat_Thu, String Repeat_Fri, String Repeat_Sat, String Repeat_Sun, String Repeat_Times, String Repeat_Number,String Location_Lat,String Location_Long)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", Name);
        contentValues.put("Repeating_Times", Repeating_Times);
        contentValues.put("Repeat_Mon", Repeat_Mon);
        contentValues.put("Repeat_Tue", Repeat_Tue);
        contentValues.put("Repeat_Wed", Repeat_Wed);
        contentValues.put("Repeat_Thu", Repeat_Thu);
        contentValues.put("Repeat_Fri", Repeat_Fri);
        contentValues.put("Repeat_Sat", Repeat_Sat);
        contentValues.put("Repeat_Sun", Repeat_Sun);
        contentValues.put("Repeat_Times", Repeat_Times);
        contentValues.put("Repeat_Number", Repeat_Number);
        contentValues.put("Location_Lat", Location_Lat);
        contentValues.put("Location_Long", Location_Long);
        db.update("Reminders", contentValues, "Id = ? ", new String[] { Integer.toString(Id) } );
        return true;
    }

    public Integer deleteReminder (Integer Id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Reminders",
                "Id = ? ",
                new String[] { Integer.toString(Id) });
    }

    public ArrayList<String> getAllReminders()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Reminders", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(REMINDER_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}
