package model;

import java.util.Date;

public class Book {
    private Integer id;
    private String name;
    private String author;
    private Date release;
    private Integer quantity;
    private Integer userId;

    public Book(Integer id, String name, String author, Date release, Integer quantity, Integer userId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.release = release;
        this.quantity = quantity;
        this.userId = userId;
    }

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
