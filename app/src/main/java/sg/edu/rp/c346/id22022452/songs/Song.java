package sg.edu.rp.c346.id22022452.songs;

import androidx.annotation.NonNull;

public class Song {
    private int _id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(int id, String title, String singers, int year, int stars) {
        this._id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int getId() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) { this.title = title; }

    public String getSingers() {
        return singers;
    }

    public void setSingers(String singers) { this.singers = singers; }

    public int getYear() {
        return year;
    }

    public void setYear(int year) { this.year = year; }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) { this.stars = stars; }

    @NonNull
    @Override
    public String toString() {
        return _id + "\n" + title + "\n" + singers + "\n" + year + "\n" + stars;
    }
}