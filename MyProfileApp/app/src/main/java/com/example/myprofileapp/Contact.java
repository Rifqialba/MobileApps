package com.example.myprofileapp;

public class Contact {
    private String nama;
    private String telepon;
    private String avatarText;
    private int color;

    public Contact(String nama, String telepon, String avatarText, int color) {
        this.nama = nama;
        this.telepon = telepon;
        this.avatarText = avatarText;
        this.color = color;
    }

    public String getNama() {
        return nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public String getAvatarText() {
        return avatarText;
    }

    public int getColor() {
        return color;
    }
}