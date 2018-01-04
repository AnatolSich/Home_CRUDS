package model;

import java.util.Date;

public class Book {
    private int id;
    private String name;
    private String author;
    private Date release;
    private int quantity;

    public Book(int id, String name, String author, Date release, int quantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.release = release;
        this.quantity = quantity;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", release=" + release +
                ", quantity=" + quantity +
                '}';
    }
}
