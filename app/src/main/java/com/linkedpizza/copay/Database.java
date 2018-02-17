package com.linkedpizza.copay;


/**
 * Created by iDarkDuck on 2/17/18.
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
