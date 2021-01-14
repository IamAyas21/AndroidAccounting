package com.stratone.accounting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RebModel {
    @SerializedName("ModalSaham")
    @Expose
    private String modalSaham;
    @SerializedName("TambahSaham")
    @Expose
    private String tambahSaham;
    @SerializedName("LastProfit")
    @Expose
    private String lastProfit;
    @SerializedName("JumlahAwal")
    @Expose
    private String jumlahAwal;
    @SerializedName("Balance")
    @Expose
    private String balance;
    @SerializedName("Deviden")
    @Expose
    private String deviden;
    @SerializedName("JumlahAkhir")
    @Expose
    private String jumlahAkhir;

    public String getModalSaham() {
        return modalSaham;
    }

    public void setModalSaham(String modalSaham) {
        this.modalSaham = modalSaham;
    }

    public String getTambahSaham() {
        return tambahSaham;
    }

    public void setTambahSaham(String tambahSaham) {
        this.tambahSaham = tambahSaham;
    }

    public String getLastProfit() {
        return lastProfit;
    }

    public void setLastProfit(String lastProfit) {
        this.lastProfit = lastProfit;
    }

    public String getJumlahAwal() {
        return jumlahAwal;
    }

    public void setJumlahAwal(String jumlahAwal) {
        this.jumlahAwal = jumlahAwal;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getDeviden() {
        return deviden;
    }

    public void setDeviden(String deviden) {
        this.deviden = deviden;
    }

    public String getJumlahAkhir() {
        return jumlahAkhir;
    }

    public void setJumlahAkhir(String jumlahAkhir) {
        this.jumlahAkhir = jumlahAkhir;
    }

}
