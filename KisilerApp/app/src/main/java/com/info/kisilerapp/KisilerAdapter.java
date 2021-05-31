package com.info.kisilerapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class KisilerAdapter extends RecyclerView.Adapter<KisilerAdapter.CardTasarimTutucu> {

    private Context mContext;
    private List<Kisiler> kisilerListe;
    private VeriTabani vt;


    public KisilerAdapter(Context mContext, List<Kisiler> kisilerListe, VeriTabani vt) {
        this.mContext = mContext;
        this.kisilerListe = kisilerListe;
        this.vt = vt;
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {

        private TextView textViewKisiBilgi;
        private ImageView imageViewNokta;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);

            textViewKisiBilgi = itemView.findViewById(R.id.textViewKisiBilgi);
            imageViewNokta = itemView.findViewById(R.id.imageViewNokta);
        }
    }

    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kisi_card_tasarim, parent, false);
        return new CardTasarimTutucu(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final CardTasarimTutucu holder, int position) {

        final Kisiler gelenKisi = kisilerListe.get(position);

        holder.textViewKisiBilgi.setText(gelenKisi.getKisi_ad() + " - " + gelenKisi.getKisi_tel());
        holder.imageViewNokta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pop-up gös-ter

                PopupMenu popupMenu = new PopupMenu(mContext, holder.imageViewNokta);
                // imageViewNoktaya basıldıgında çalışan bir popUpMenu olusturduk.
                popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu_sil_guncelle, popupMenu.getMenu());
                // popupMenu'nun tasarımını ekledik.

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.action_guncelle:
                                // Snackbar.make(holder.imageViewNokta, "Güncelle Tıklandı", Snackbar.LENGTH_SHORT).show();

                            {
                                final View view = LayoutInflater.from(mContext).inflate(R.layout.alert_tasarim_acilan, null);
                                final EditText editTextAd = view.findViewById(R.id.editTextAd);
                                final EditText editTextTel = view.findViewById(R.id.editTextTel);

                                editTextAd.setText(gelenKisi.getKisi_ad());
                                editTextTel.setText(gelenKisi.getKisi_tel());

                                AlertDialog.Builder AlertYukleyiciGosterici2 = new AlertDialog.Builder(mContext);
                                AlertYukleyiciGosterici2.setTitle("Güncelle");
                                AlertYukleyiciGosterici2.setMessage("Mesaj2");
                                AlertYukleyiciGosterici2.setView(view);

                                AlertYukleyiciGosterici2.setPositiveButton("Güncelle", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        String guncelAd = editTextAd.getText().toString().trim();
                                        String guncelTel = editTextTel.getText().toString().trim();


                                        new KisilerDao().kisiGuncelle(vt, gelenKisi.getKisi_id(), guncelAd, guncelTel);


                                        kisilerListe = new KisilerDao().tumKisiler(vt);

                                        notifyDataSetChanged();

                                        // Snackbar.make(holder.imageViewNokta, "Güncel: " + guncelAd + " - " + guncelTel, Snackbar.LENGTH_SHORT).show();
                                    }
                                });

                                AlertYukleyiciGosterici2.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                                AlertYukleyiciGosterici2.create().show();
                            }

                                return true;
                            case R.id.action_sil:
                                Snackbar.make(holder.imageViewNokta, "Silmek istediğinize emin misiniz?", Snackbar.LENGTH_SHORT)
                                        .setAction("Evet", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                new KisilerDao().kisiSil(vt,gelenKisi.getKisi_id());

                                                kisilerListe = new KisilerDao().tumKisiler(vt);
                                                notifyDataSetChanged();
                                            }
                                        })

                                        .show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                // Menu itemlarından seçim yapıldıgında ne yapılacağını ayarladık.

                popupMenu.show(); // pop up hazır. Her card-view için gösterdik.
            }
        });
    }

    @Override
    public int getItemCount() {
        return kisilerListe.size();
    }


}
