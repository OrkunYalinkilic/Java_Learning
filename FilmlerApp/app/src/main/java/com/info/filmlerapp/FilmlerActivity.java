package com.info.filmlerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

public class FilmlerActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView filmlerRV;
    private ArrayList<Film> arrayListFilmler;
    private FilmAdapter filmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmler);

        toolbar = findViewById(R.id.toolbar);
        filmlerRV = findViewById(R.id.FilmlerRV);

        toolbar.setTitle("Filmler");
        setSupportActionBar(toolbar);

        filmlerRV.setHasFixedSize(true);
        filmlerRV.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//?
        arrayListFilmler = new ArrayList<>();

        Film f1 = new Film(1, "Black Swan", 1992, "black", null, null);
        Film f2 = new Film(2, "Gora", 2010, "gora", null, null);
        Film f3 = new Film(3, "The Guilty", 2009, "guilty", null, null);

        arrayListFilmler.add(f1);
        arrayListFilmler.add(f2);
        arrayListFilmler.add(f3);

        filmAdapter = new FilmAdapter(this, arrayListFilmler);

        filmlerRV.setAdapter(filmAdapter);


    }
}
