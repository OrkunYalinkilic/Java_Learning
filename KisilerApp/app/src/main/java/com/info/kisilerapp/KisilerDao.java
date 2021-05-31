package com.info.kisilerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class KisilerDao {

    public ArrayList<Kisiler> tumKisiler(com.info.kisilerapp.VeriTabani vt) {

        ArrayList<Kisiler> kisilerArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase(); // database'i yazabilecek modda aldık.

        Cursor c = db.rawQuery("SELECT * FROM kisiler", null); // db ' e sorduk.

        while (c.moveToNext()) {

            Kisiler k = new Kisiler();
            k.setKisi_id(c.getInt(c.getColumnIndex("kisi_id")));
            k.setKisi_ad(c.getString(c.getColumnIndex("kisi_ad")));
            k.setKisi_tel(c.getString(c.getColumnIndex("kisi_tel")));

            kisilerArrayList.add(k);
            db.close();
        }
        return kisilerArrayList;
    }

    public ArrayList<Kisiler> tumKisilerAra(VeriTabani vt, String arananKelime) {

        ArrayList<Kisiler> kisilerArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM kisiler WHERE kisi_ad like '" + arananKelime + "%'", null);

        while (c.moveToNext()) {

            Kisiler gelenKisi = new Kisiler();

            gelenKisi.setKisi_id(c.getInt(c.getColumnIndex("kisi_id")));
            gelenKisi.setKisi_ad(c.getString(c.getColumnIndex("kisi_ad")));
            gelenKisi.setKisi_tel(c.getString(c.getColumnIndex("kisi_tel")));

            kisilerArrayList.add(gelenKisi);
        }
        db.close();
        return kisilerArrayList;
    }

    public void kisiSil(VeriTabani vt, int kisi_id) {

        SQLiteDatabase db = vt.getWritableDatabase();

        db.delete("kisiler", "kisi_id=?", new String[]{String.valueOf(kisi_id)});
        db.close();
    }

    public void kisiEkle(VeriTabani vt, String kisi_Ad, String kisi_Tel) {

        SQLiteDatabase db = vt.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("kisi_ad", kisi_Ad);
        contentValues.put("kisi_tel", kisi_Tel);

        db.insertOrThrow("kisiler", null, contentValues);

        db.close();
    }

    public void kisiGuncelle(VeriTabani vt, int kisi_id, String kisi_Ad, String kisi_Tel) {

        SQLiteDatabase db = vt.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("kisi_ad", kisi_Ad);
        contentValues.put("kisi_tel", kisi_Tel);

        db.update("kisiler", contentValues, "kisi_id=?", new String[]{String.valueOf(kisi_id)});
        db.close();
    }
}

