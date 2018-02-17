package com.linkedpizza.copay;

/**
 * Created by Nick on 2/17/2018.
 */

class UserAccount {

    private String name;
    private String email;

    UserAccount(String name, String email){
        this.name = name;
        this.email = email;
    }

    protected void setName(String name){
        this.name = name;
    }
    protected String getName(){
        return name;
    }

    protected void setEmail(String email){
        this.email = email;
    }
    protected String getEmail(){
        return email;
    }
}
