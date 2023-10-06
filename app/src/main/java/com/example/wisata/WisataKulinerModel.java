package com.example.wisata;

public class WisataKulinerModel {
    String key, nama, lokasi, deskripsi;

    public WisataKulinerModel() {
        // dibutuhkan oleh Firebase
    }

    public WisataKulinerModel(String key, String nama, String lokasi, String deskripsi) {
        this.key = key;
        this.nama = nama;
        this.lokasi = lokasi;
        this.deskripsi = deskripsi;
    }

    public void setKey(String key){
        this.key = key;
    }

    public String getkey() {
        return key;
    }

    public String getNama() {
        return nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
}
