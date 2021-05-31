package com.info.sozlukuygulamasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView rv;
    private Toolbar toolbar;
    private KelimeAdapter kelimeAdapter;
    private List<Kelimeler> kelimelerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        toolbar = findViewById(R.id.toolbar);

        //toolbar.setTitle("Sözlük");
        //  setSupportActionBar(toolbar);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Kelimeler k1 = new Kelimeler(1, "book", "kitap");
        Kelimeler k2 = new Kelimeler(2, "dog", "köpek");
        Kelimeler k3 = new Kelimeler(3, "computer", "bilgisayar");

        kelimelerList = new ArrayList<>();

        kelimelerList.add(k1);
        kelimelerList.add(k2);
        kelimelerList.add(k3);

        kelimeAdapter = new KelimeAdapter(this, kelimelerList);

        rv.setAdapter(kelimeAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_ara);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);

        // bundan önceki 3 satır alttaki arama ile ilgili 2 metodun çalışabilmesi için yazıldı.

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) { // arama butonuna tıkladıgında veri verir.
        Log.e("OnQueryTextSubmit", query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) { // aradığınızda sürekli veri verir.
        Log.e("onQueryTextChange", newText);
        return true;
    }
}
