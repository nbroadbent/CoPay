package com.linkedpizza.copay;

import android.provider.ContactsContract;

/**
 * Created by MLH-Admin on 2/17/2018.
 */

class Database {
    private static volatile Database instance = null;

    // Singleton.
    private Database(){}

    static Database getInstance(){
        if (instance == null){
            synchronized (instance){
                if (instance == null){
                    instance = new Database();
                }
            }
        }
        return instance;
    }
}
