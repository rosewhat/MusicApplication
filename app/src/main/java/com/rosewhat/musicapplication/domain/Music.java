package com.rosewhat.musicapplication.domain;

public class Music {
    private String title;
    private String description;
    private String year;
    private String language;
    private int music;


    public Music(String title, String description, String year, String language, int music) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.language = language;
        this.music = music;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getYear() {
        return year;
    }

    public String getLanguage() {
        return language;
    }

    public int getMusic() {
        return music;
    }
}
