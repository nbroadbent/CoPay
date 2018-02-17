package com.linkedpizza.copay;

<<<<<<< HEAD
/**
 * Created by iDarkDuck on 2/17/18.
 */

class Database {

=======
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
>>>>>>> f3570c6cb7682eaa0202d14fd7c19756cdd457ba
}
