package com.stockkeeper.contracts;

import java.util.ArrayList;

public interface FormBarang {
    String getNamaBarang();
    int getJumlahBarang();
    double getHargaBarang();
    ArrayList<?> getDaftarBarang();

    void setNamaBarang(String namaBarang);
    void setJumlahBarang(int jumlahBarang);
    void setHargaBarang(double hargaBarang);
    void setDaftarBarang(Object obj);
}