package com.anura.controller;

public class Quest {
    public String title;
    public String description;
    public String status;

    public Quest(String title){
        this.title = title;
        this.status = "Incomplete";
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}
