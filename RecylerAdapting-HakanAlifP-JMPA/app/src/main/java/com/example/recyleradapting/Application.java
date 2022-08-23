package com.example.recyleradapting;

import android.accounts.Account;

import androidx.recyclerview.widget.ItemTouchHelper;

public class Application{
    public String account;

    public Application(String account) {
        this.account = account;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        account = new Account("Me");
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback() {
        };
    }
}