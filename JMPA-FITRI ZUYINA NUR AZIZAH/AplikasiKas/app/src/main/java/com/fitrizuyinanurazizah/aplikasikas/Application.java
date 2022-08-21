package com.fitrizuyinanurazizah.aplikasikas;

import android.accounts.Account;

import androidx.recyclerview.widget.ItemTouchHelper;

public class Application {

    public String deskripsi;
    public String amount;

    public Application(String deskripsi, String amount) {
        this.deskripsi = deskripsi;
        this.amount = amount;
    }

    public void onCreate() {
        super.onCreate();
        account = new Account("Budi");;
        ItemTouchHelper.SimpleCallback;

    }
}
