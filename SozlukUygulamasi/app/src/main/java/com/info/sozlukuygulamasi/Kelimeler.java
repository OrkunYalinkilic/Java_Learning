package com.info.sozlukuygulamasi;

public class Kelimeler {
    private int kelimeID;
    private String kelimeTUR;
    private String kelimeENG;

    public Kelimeler() {
    }

    public Kelimeler(int kelimeID, String kelimeTUR, String kelimeENG) {
        this.kelimeID = kelimeID;
        this.kelimeTUR = kelimeTUR;
        this.kelimeENG = kelimeENG;
    }

    public int getKelimeID() {
        return kelimeID;
    }

    public void setKelimeID(int kelimeID) {
        this.kelimeID = kelimeID;
    }

    public String getKelimeTUR() {
        return kelimeTUR;
    }

    public void setKelimeTUR(String kelimeTUR) {
        this.kelimeTUR = kelimeTUR;
    }

    public String getKelimeENG() {
        return kelimeENG;
    }

    public void setKelimeENG(String kelimeENG) {
        this.kelimeENG = kelimeENG;
    }
}
