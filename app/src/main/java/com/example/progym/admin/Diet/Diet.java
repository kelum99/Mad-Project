package com.example.progym.admin.Diet;

public class Diet {
    private String title;
    private String subTitle;
    private String description;
    private String breakfast;
    private String lunch;
    private String dinner;
    private String snack;

    public Diet() { }


    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getDescription() {
        return description;
    }

    public String getBreakfast(){return breakfast;}

    public String getLunch(){return lunch;}

    public String getDinner(){return dinner;}

    public String getSnack(){return snack;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBreakfast(String breakfast) {this.breakfast = breakfast;}

    public  void setLunch(String lunch) {this.lunch = lunch;}

    public void setDinner(String dinner) {this.dinner = dinner;}

    public void setSnack(String snack) {this.snack = snack;}

}
