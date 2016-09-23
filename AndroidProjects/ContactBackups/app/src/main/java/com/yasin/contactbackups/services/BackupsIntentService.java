package com.yasin.contactbackups.services;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.File;
import java.io.IOException;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class BackupsIntentService extends IntentService {

    public BackupsIntentService() {
        super("BackupsIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String info = intent.getStringExtra("info");
        if ("backupsContacts".equals(info)) {

            ContentResolver resolver = getContentResolver();
            Cursor cursor = resolver.query(
                    ContactsContract.Contacts.CONTENT_URI,
                    new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME},
                    null,
                    null,
                    null
            );
            if (cursor != null) {
                while (cursor.moveToNext()){
                    int index = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                    if (index!=-1) {
                        long id = cursor.getLong(index);
                    }
                    index=cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                    if (index!=-1) {
                        String name = cursor.getString(index);
                    }
                }
                cursor.close();
            }

        }

    }

}
