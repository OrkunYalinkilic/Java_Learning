package com.info.sayitahminuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class TahminActivity extends AppCompatActivity {

    private TextView textViewKalanHak, textViewYardim;
    private EditText editTextGirdi;
    private Button buttonTahmin;
    private int rastgeleSayi;
    private int sayac = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahmin);

        textViewKalanHak = findViewById(R.id.textViewKalanHak);
        textViewYardim = findViewById(R.id.textViewYardim);
        editTextGirdi = findViewById(R.id.editTextGirdi);
        buttonTahmin = findViewById(R.id.buttonTahmin);

        Random rnd = new Random();
        rastgeleSayi = rnd.nextInt(100);
        Log.e("Rastgele sayi: ", String.valueOf(rastgeleSayi));

        buttonTahmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sayac -= 1;

                int tahmin = Integer.parseInt(editTextGirdi.getText().toString());

                if (tahmin == rastgeleSayi) {

                    Intent i = new Intent(TahminActivity.this, SonucActivity.class);
                    i.putExtra("sonuc", true);
                    startActivity(i);
                    finish();//bu sayfaya geri dönmeye izin verme demektir.
                    return; // geri kalan işlemlere gitmemeyi sağlar.
                }
                else if (tahmin > rastgeleSayi) {

                    textViewYardim.setText("AZALT");
                    textViewKalanHak.setText("Kalan Hak: " + sayac);

                }
                else if (tahmin < rastgeleSayi) {

                    textViewYardim.setText("ARTTIR");
                    textViewKalanHak.setText("Kalan Hak: " + sayac);
                }

                if (sayac == 0) {
                    Intent i = new Intent(TahminActivity.this, SonucActivity.class);
                    i.putExtra("sonuc", false);
                    startActivity(i);
                    finish();

                }

                editTextGirdi.setText("");

                //startActivity(new Intent(TahminActivity.this,SonucActivity.class));
                //  finish();
            }
        });


    }
}
