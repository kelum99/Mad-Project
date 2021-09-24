package com.example.progym.admin.exercises;

public class Exercise {

    private String title;
    private String subTitle;
    private String description;
    private String imageURL;

    public Exercise() { }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return this.getTitle()+" - "+getSubTitle();
    }
}
