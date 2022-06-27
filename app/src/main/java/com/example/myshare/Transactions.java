package com.example.myshare;

import java.io.Serializable;

public class Transactions {

    private static final long serialVersionUID = 1L;

    private String title;
    private String description;

    public Transactions(String title, String description) {
        this.title = title;
        this.description = description;
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
}
