package com.example.nikola_lenivetsapp.Models;

public class Note {

    private String noteKey, title, description, userPhoto, picture, userId, userName;

    public Note(String title, String description, String userPhoto, String picture, String userId, String userName) {
        this.title = title;
        this.description = description;
        this.userPhoto = userPhoto;
        this.picture = picture;
        this.userId = userId;
        this.userName = userName;
    }
    public Note(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNoteKey() {
        return noteKey;
    }

    public void setNoteKey(String noteKey) {
        this.noteKey = noteKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
