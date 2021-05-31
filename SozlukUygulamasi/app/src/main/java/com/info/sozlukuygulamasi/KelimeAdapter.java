package com.info.sozlukuygulamasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class KelimeAdapter extends RecyclerView.Adapter<KelimeAdapter.CardTasarimTutucu> {

    private Context mContext;
    private List<Kelimeler> kelimelerListe;

    public KelimeAdapter(Context mContext, List<Kelimeler> kelimelerListe) {
        this.mContext = mContext;
        this.kelimelerListe = kelimelerListe;
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {

        private TextView textViewIngilizce, textViewTurkce;
        private CardView kelime_card;

        public CardTasarimTutucu(View itemView) {
            super(itemView);

            kelime_card = itemView.findViewById(R.id.kelime_card);
            textViewTurkce = itemView.findViewById(R.id.textViewTurkce);
            textViewIngilizce = itemView.findViewById(R.id.textViewIngilizce);
        }
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //tasarımsal bağlama

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kelime_card_tasarim, parent, false);
        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) { // doldurma işlemleri

        Kelimeler gelenKelime = kelimelerListe.get(position);

        holder.textViewIngilizce.setText(gelenKelime.getKelimeENG());
        holder.textViewTurkce.setText(gelenKelime.getKelimeTUR());

        holder.kelime_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return kelimelerListe.size();
    }

}
