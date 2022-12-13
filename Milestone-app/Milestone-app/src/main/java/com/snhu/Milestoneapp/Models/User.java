package com.snhu.Milestoneapp.Models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * xml user class
 * 
 * Purpose: get and set users from/to XML file.
 * Allow for program to check if username and password
 * match this user.
 */
@XmlRootElement(name ="user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    //tag holds username of user ex: <username>john</username>
    private String username;

    //tag holds password of user ex: <password>1234</password>
    private String password;

    private String role = "User";

    //check if username and password match this users
    public Boolean match (String p, String u){

        //if match return true
        if (this.password.equals(p) && this.username.equals(u)) {
            return true;
        }

        //if no match return false
        return false;
    }

    //Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [password=" + password + ", username=" + username + "]";
    } 
}

