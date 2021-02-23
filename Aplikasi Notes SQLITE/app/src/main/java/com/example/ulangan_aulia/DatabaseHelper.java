package com.example.ulangan_aulia;


import  android.content.ContentValues;
import android.content.Context;
import  android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION=1;
    private static final String DB_NAME="Notes";
    private static final String TABLE_NAME="tbl_user";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_ISI="isi";
    private static final String KEY_TGL="tgl";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String create="Create Table "+TABLE_NAME+"("+KEY_ID+" TEXT PRIMARY KEY,"+KEY_NAME+" TEXT,"+KEY_ISI+" TEXT,"+KEY_TGL+" TEXT"+")";
        db.execSQL(create);
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){
        String sql=("drop table if exists "+TABLE_NAME);
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(Note note){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, note.getId());
        values.put(KEY_NAME, note.getName());
        values.put(KEY_ISI, note.getIsi());
        values.put(KEY_TGL, note.getTgl());

        db.insert(TABLE_NAME, null, values);
    }

    public void update(Note ctt){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, ctt.getName());
        cv.put(KEY_ISI, ctt.getIsi());
        cv.put(KEY_TGL, ctt.getTgl());

        String whereClause = KEY_ID+"='"+ ctt.getId()+"'";
        db.update(TABLE_NAME, cv, whereClause, null);
    }

    public List<Note> getAllNotes(){
        ArrayList<Note> noteList = new ArrayList<Note>();

        SQLiteDatabase db = getReadableDatabase();
        String[] columns={KEY_NAME,KEY_ISI, KEY_TGL, KEY_ID};
        Cursor c = db.query(TABLE_NAME, columns, null, null, null, null, null);

        while (c.moveToNext()){

            Note ctt=new Note();
            ctt.setName(c.getString(0));
            ctt.setIsi(c.getString(1));
            ctt.setTgl(c.getString(2));
            ctt.setId(c.getString(3));

            noteList.add(ctt);
        }

        return noteList;
    }

    public void delete(String id){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = KEY_ID+"='"+id+"'";
        db.delete(TABLE_NAME, whereClause, null);
    }



}

