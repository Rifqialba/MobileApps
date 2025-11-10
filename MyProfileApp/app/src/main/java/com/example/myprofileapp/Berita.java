package com.example.myprofileapp;

public class Berita {
    private String id;
    private String judul;
    private String ringkasan;
    private String kategori;
    private String penulis;
    private String tanggal;
    private String waktuBaca;
    private int gambar;

    public Berita(String id, String judul, String ringkasan, String kategori,
                  String penulis, String tanggal, String waktuBaca, int gambar) {
        this.id = id;
        this.judul = judul;
        this.ringkasan = ringkasan;
        this.kategori = kategori;
        this.penulis = penulis;
        this.tanggal = tanggal;
        this.waktuBaca = waktuBaca;
        this.gambar = gambar;
    }

    // Getters
    public String getId() { return id; }
    public String getJudul() { return judul; }
    public String getRingkasan() { return ringkasan; }
    public String getKategori() { return kategori; }
    public String getPenulis() { return penulis; }
    public String getTanggal() { return tanggal; }
    public String getWaktuBaca() { return waktuBaca; }
    public int getGambar() { return gambar; }
}