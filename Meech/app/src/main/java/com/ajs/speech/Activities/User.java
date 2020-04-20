package com.ajs.speech.Activities;

public class User {
    private String id;

    private String name;

    private String nickName;

    private String bio;


    public User(String name, String nickName, String bio) {
        this.name = name;
        this.bio = bio;
        this.nickName = nickName;
    }

    public User(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }
}
