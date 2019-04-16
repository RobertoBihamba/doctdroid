package com.example.joseph.doctdroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {


    public DbHelper( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "doctdroid", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE Table info(ID INTEGER PRIMARY KEY AUTOINCREMENT,TENSION DOUBLE,TEMPERATURE DOUBLE,POID DOUBLE,SUCRE DOUBLE,TAILLE DOUBLE,RYHTME DOUBLE,DATE DATE,PRESCRIPTION VARCHAR(300))");
       // db.execSQL("CREATE Table medoc(ID INTEGER PRIMARY KEY AUTOINCREMENT,NOM VARCHAR(20),DESCRIPTION VARCHAR(200))");
        db.execSQL("CREATE Table profil(ID INTEGER PRIMARY KEY AUTOINCREMENT,GROUPESANGUIN VARCHAR(20),AGE VARCHAR(20),TENSION DOUBLE,TEMPERATURE DOUBLE,POID DOUBLE,SUCRE DOUBLE,TAILLE DOUBLE,RYHTME DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS info");
    }

    public void insertInfo(Double tension,Double temperature,Double poid,Double sucre,Double taille,Double rythme,String prescr){
        ContentValues contentValues=new ContentValues();
        contentValues.put("TENSION",tension);
        contentValues.put("TEMPERATURE",temperature);
        contentValues.put("POID",poid);
        contentValues.put("SUCRE",sucre);
        contentValues.put("TAILLE",taille);
        contentValues.put("RYHTME",rythme);
        contentValues.put("PRESCRIPTION",prescr);
        this.getWritableDatabase().insertOrThrow("info","",contentValues);
    }

    public void insertProfil(String groupe,String age,Double tension,Double temperature,Double poid,Double sucre,Double taille,Double rythme){
        ContentValues contentValues=new ContentValues();
        contentValues.put("GROUPESANGUIN",groupe);
        contentValues.put("AGE",age);
        contentValues.put("TENSION",tension);
        contentValues.put("TEMPERATURE",temperature);
        contentValues.put("POID",poid);
        contentValues.put("SUCRE",sucre);
        contentValues.put("TAILLE",taille);
        contentValues.put("RYHTME",rythme);
        this.getWritableDatabase().insertOrThrow("profil","",contentValues);
    }

    public List<Infos> getAll(){
        List<Infos> li=new ArrayList<>();
        Cursor cursor=this.getReadableDatabase().rawQuery("SELECT * FROM infos",null);
        while (cursor.moveToNext()){
            Infos infos=new Infos();
            infos.setId(cursor.getInt(1));
            infos.setTension(cursor.getDouble(2));
            infos.setTemprature(cursor.getDouble(3));
            infos.setPoid(cursor.getDouble(4));
            infos.setSucre(cursor.getDouble(5));
            infos.setTaille(cursor.getDouble(6));
            infos.setRythme(cursor.getDouble(7));
          //  infos.setDate(cursor.get);
            li.add(infos);
        }
        return li;
    }
}
