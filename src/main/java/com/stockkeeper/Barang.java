package com.stockkeeper;

import com.stockkeeper.contracts.FormBarang;
import java.util.ArrayList;

public class Barang implements FormBarang {
    private String namaBarang;
    private int jumlahBarang;
    private double hargaBarang;
    public static ArrayList<Barang> daftarBarang = new ArrayList<>();

    public Barang() {}

    public Barang(String nama, int jumlah, double harga) {
        this.namaBarang = nama;
        this.jumlahBarang = jumlah;
        this.hargaBarang = harga;
    }

    @Override
    public final String getNamaBarang() {
        return namaBarang;
    }

    @Override
    public final int getJumlahBarang() {
        return jumlahBarang;
    }

    @Override
    public final double getHargaBarang() {
        return hargaBarang;
    }

    @Override
    public final ArrayList<Barang> getDaftarBarang() {
        return daftarBarang;
    }

    @Override
    public final void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    @Override
    public final void setJumlahBarang(int jumlahBarang) {
        this.jumlahBarang = jumlahBarang;
    }

    @Override
    public final void setHargaBarang(double hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    @Override
    public final void setDaftarBarang(Object obj) {
        daftarBarang.add((Barang) obj);
    }
}
