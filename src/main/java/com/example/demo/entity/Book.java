package com.example.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Book implements Serializable {
    private String id;
    private String store;
    private String price;
    private String name;
    private String author;
    private String type;
    private String ISBN;
    private String introduction;
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && store == book.store && Objects.equals(price, book.price) && Objects.equals(name, book.name) && Objects.equals(author, book.author) && Objects.equals(type, book.type) && Objects.equals(ISBN, book.ISBN) && Objects.equals(introduction, book.introduction) && Objects.equals(image, book.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, store, price, name, author, type, ISBN, introduction, image);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", store=" + store +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", introduction='" + introduction + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
