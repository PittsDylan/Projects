package com.snhu.Milestoneapp.Models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Models a list of users
 * 
 * Purpose: create a list of users from XML file, 
 * when sign in occursthis model allows the program 
 * to search all users for that user.
 */
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class Users {

    //tag holds list of users ex: <user><username>john</username><password>1234</password></user>
    @XmlElement(name = "user")
    private List<User> user = null;

    //Getters & Setters
    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
