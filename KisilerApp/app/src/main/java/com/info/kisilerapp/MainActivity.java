package com.info.kisilerapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private RecyclerView rv;
    private FloatingActionButton fab;
    private ArrayList<Kisiler> kisilerArrayList; // adapter örneklerken kullanacağız.
    private KisilerAdapter adapter;
    private VeriTabani vt;
    private KisilerDao kd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.rv);
        fab = findViewById(R.id.fab);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        toolbar.setTitle("Kişiler");
        setSupportActionBar(toolbar);

       /* kisilerArrayList = new ArrayList<>();

        Kisiler k1 = new Kisiler(1, "Ahmet", "2135215321231");
        Kisiler k2 = new Kisiler(2, "Ali", "9879879214369");

        kisilerArrayList.add(k1);
        kisilerArrayList.add(k2);*/

        vt = new VeriTabani(this);

        kd = new KisilerDao();

        kisilerArrayList = kd.tumKisiler(vt);

        adapter = new KisilerAdapter(this, kisilerArrayList,vt);

        rv.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertGoster();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu_arama, menu);
        // LayoutInf yapmadık. Çünkü ekleme fonk içerisindeyiz. O kendisi arkada onu yapıyor.

        MenuItem menuItem = menu.findItem(R.id.action_ara);
        // menu içerisindeki action_ara item'ını aldım.
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        // Arama item' ının search ekranını aldım.
        searchView.setOnQueryTextListener(this);
        // Ardından bu search ekranının (searchView) bu text listener'ı dinlemesi gerektiğini söyledim
        // Artık arama butonuna (item) bastıgımda arama özelliği aktid olacak.
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {



        Log.e("onQueryTextSubmit", query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("onQueryTextChange", newText);

        kisilerArrayList = kd.tumKisilerAra(vt,newText);

        adapter = new KisilerAdapter(MainActivity.this,kisilerArrayList,vt);
        rv.setAdapter(adapter);

        return false;
    }

    public void alertGoster() {

        //LayoutInflater layoutInflater = LayoutInflater.from(this);
        //View view = layoutInflater.inflate(R.layout.alert_tasarim_acilan,null);

        final View view = LayoutInflater.from(this).inflate(R.layout.alert_tasarim_acilan,null); // temel alert harici

        final EditText editTextAd = view.findViewById(R.id.editTextAd);
        final EditText editTextTel = view.findViewById(R.id.editTextTel);


        AlertDialog.Builder AlertYukleyiciGosterici = new AlertDialog.Builder(this);
        AlertYukleyiciGosterici.setTitle("Kişi Ekle");
        AlertYukleyiciGosterici.setMessage("Mesaj");
        AlertYukleyiciGosterici.setView(view); // temel alert harici
        AlertYukleyiciGosterici.setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String gelenKisiAd = editTextAd.getText().toString();
                String gelenKisiTel = editTextTel.getText().toString();

                kd.kisiEkle(vt,gelenKisiAd,gelenKisiTel);

                // Ekleme yaptıktan sonra listeye eklenmesi için listeyi ve ardından adapteri güncelliyoruz.

                kisilerArrayList = kd.tumKisiler(vt);
                adapter = new KisilerAdapter(MainActivity.this, kisilerArrayList,vt);
                rv.setAdapter(adapter);

                Snackbar.make(fab,gelenKisiAd + " - " + gelenKisiTel,Snackbar.LENGTH_SHORT).show();
            }
        });

        AlertYukleyiciGosterici.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertYukleyiciGosterici.create().show();

    }
}
