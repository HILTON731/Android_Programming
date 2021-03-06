package com.kangwon.macaron.models;

import com.google.firebase.database.IgnoreExtraProperties;

// [START Schedular_user_class]
@IgnoreExtraProperties
public class User {

    public String username;
    public String email;
    public String phone;
    public boolean isowner;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email){
        this.username = username;
        this.email = email;
    }
    public User(String username, String email, String phone, boolean isowner){
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.isowner = isowner;
    }
}
// [END Schedular_user_class]