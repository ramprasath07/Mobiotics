package com.ramprasath.mobiotics.Model;

public class VideoPlayer {


    private String description;
    private String id;
    private String thumb;
    private String title;
    private String url;

    public VideoPlayer(String description, String id, String thumb, String title, String url) {
        this.description = description;
        this.id = id;
        this.thumb = thumb;
        this.title = title;
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getThumb() {
        return thumb;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}






