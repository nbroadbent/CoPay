package com.linkedpizza.copay;

/**
 * Created by MLH-Admin on 2/17/2018.
 */

class Server {

    private static volatile Server instance = null;

    // Singleton.
    private Server(){}

    static Server getInstance(){
        if (instance == null){
            synchronized (Server.class){
                if (instance == null){
                    instance = new Server();
                }
            }
        }
        return instance;
    }
}
