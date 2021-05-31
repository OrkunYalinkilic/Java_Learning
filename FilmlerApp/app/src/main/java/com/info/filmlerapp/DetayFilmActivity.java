package com.info.filmlerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DetayFilmActivity extends AppCompatActivity  {
    private ImageView imageViewFilmResim;
    private TextView filmAd, filmYil, filmYonetmen;
    private Film gelenFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay_film);

        imageViewFilmResim = findViewById(R.id.imageViewFilm);
        filmAd = findViewById(R.id.textViewFilmAd);
        filmYil = findViewById(R.id.textViewFilmYil);
        filmYonetmen = findViewById(R.id.textViewYonetmen);

      // Film gelenFilmm = (Film) getIntent().getSerializableExtra("gidenFilm");



    }
}
