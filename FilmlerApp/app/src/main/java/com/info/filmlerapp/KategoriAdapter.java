package com.info.filmlerapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.CardTasarimTutucu> {

    private Context mContext;
    private List<Kategori> kategoriListe;

    public KategoriAdapter(Context mContext, List<Kategori> kategoriListe) {
        this.mContext = mContext;
        this.kategoriListe = kategoriListe;
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {

        private CardView kategori_card;
        private TextView textViewKategoriAd;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);

            kategori_card = itemView.findViewById(R.id.kategori_card);
            textViewKategoriAd = itemView.findViewById(R.id.textViewKategoriAd);
        }
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kategori_card_tasarimi, parent, false);

        return new CardTasarimTutucu(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {

        Kategori gelenKategori = kategoriListe.get(position);

        holder.textViewKategoriAd.setText(gelenKategori.getKategori_ad());

        holder.kategori_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(mContext, FilmlerActivity.class);
                mContext.startActivity(ıntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kategoriListe.size();
    }
}
