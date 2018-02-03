package model;

import java.util.Date;

public class Book {
   private int id;
    private String title;
    private Date releaseDate;
    private int userId;

    public Book(int id, String title, Date releaseDate, int userId) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.userId = userId;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
