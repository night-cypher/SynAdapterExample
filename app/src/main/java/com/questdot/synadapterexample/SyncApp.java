/*
 * Copyright (c) 2014 Twitter Inc.
 */
package com.questdot.synadapterexample;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;


public class SyncApp extends Application {
    private static final String TAG = "APP";
    private static final long SYNC_FREQUENCY = 60 * 60;  // 1 hour (in seconds)

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "create");

        Account account = GenericAccountService.GetAccount();

        AccountManager accountManager = (AccountManager) getApplicationContext().getSystemService(Context.ACCOUNT_SERVICE);
        if (accountManager.addAccountExplicitly(account, null, null)) {
            // Inform the system that this account supports sync
            ContentResolver.setIsSyncable(account, SyncProvider.AUTHORITY, 1);
            // Inform the system that this account is eligible for auto sync when the network is up
            ContentResolver.setSyncAutomatically(account, SyncProvider.AUTHORITY, true);
            // Recommend a schedule for automatic synchronization. The system may modify this based
            // on other scheduled syncs and network utilization.
            ContentResolver.addPeriodicSync(
                    account, SyncProvider.AUTHORITY, new Bundle(), SYNC_FREQUENCY);

        }

     /*   String acctType = getString(R.string.account_type);
        if (checkSelfPermission(Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            Account[] accounts = AccountManager.get(this).getAccountsByType(acctType);
            if (0 >= accounts.length) { addAccount(ACCOUNT_TYPE, acctType); }
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }*/
    }

   /* private void addAccount(String name, String acctType) {
        Account account = new Account(name, acctType);
        AccountManager.get(this).addAccountExplicitly(account, null, null);
        ContentResolver.setIsSyncable(account, SyncProvider.AUTHORITY, 1);
        ContentResolver.setSyncAutomatically(account, SyncProvider.AUTHORITY, true);
        ContentResolver.addPeriodicSync(account, SyncProvider.AUTHORITY, new Bundle(), 60);

    }*/



}
