package com.info.filmlerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView kategoriRV;
    private Toolbar toolbar;
    private ArrayList<Kategori>  kategoriArrayList;
    private KategoriAdapter kategoriAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        kategoriRV = findViewById(R.id.kategoriRV);

        toolbar.setTitle("Kategoriler");
        setSupportActionBar(toolbar);

        kategoriRV.setHasFixedSize(true);
        kategoriRV.setLayoutManager(new LinearLayoutManager(this)); // alt alta tasarım

        kategoriArrayList = new ArrayList<>();

        Kategori k1 = new Kategori(1,"Komedi");
        Kategori k2 = new Kategori(2,"BilimKurgu");

        kategoriArrayList.add(k2);
        kategoriArrayList.add(k1);

        kategoriAdapter = new KategoriAdapter(this,kategoriArrayList);

        kategoriRV.setAdapter(kategoriAdapter);

    }
}
