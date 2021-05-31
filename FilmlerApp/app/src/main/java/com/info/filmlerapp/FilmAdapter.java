package com.info.filmlerapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.CardTasarimTutucu> {

    private Context mContext;
    private List<Film> filmListe;

    public FilmAdapter(Context mContext, List<Film> filmListe) {
        this.mContext = mContext;
        this.filmListe = filmListe;
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {

        private CardView film_card;
        public ImageView imageViewFilm;
        private TextView textViewFilmAd;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);

            film_card = itemView.findViewById(R.id.film_card);
            imageViewFilm = itemView.findViewById(R.id.imageViewFilm);
            textViewFilmAd = itemView.findViewById(R.id.textViewFilmAd);

        }
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_card_tasarimi,parent,false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {

        final Film gelenFilm = filmListe.get(position);

        holder.textViewFilmAd.setText(gelenFilm.getFilm_ad());
        holder.imageViewFilm.setImageResource
                (mContext.getResources().getIdentifier
                        (gelenFilm.getFilm_resim(),"drawable",mContext.getPackageName()));

        holder.film_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ıntent = new Intent(mContext,DetayFilmActivity.class);

                ıntent.putExtra("gidenFilm",gelenFilm);

                mContext.startActivity(ıntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return filmListe.size();
    }

}
