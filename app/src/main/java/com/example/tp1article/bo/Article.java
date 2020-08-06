package com.example.tp1article.bo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Classe représentant un article
 */
@Entity
public class Article {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private Float price;
    private Float rating;
    private String description;
    private Boolean isBought;
    private String link;

    public Article(int id, String name, Float price, String description, Float rating, Boolean isBought, String link) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.isBought = isBought;
        this.link = link;
    }
    public Article(String name, Float price, String description, Float rating, Boolean isBought, String link) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.isBought = isBought;
        this.link = link;
    }

    public Article() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Boolean getIsBought() {
        return isBought;
    }

    public void setIsBought(Boolean isBought) {
        this.isBought = isBought;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", isBought=" + isBought +
                ", link='" + link + '\'' +
                '}';
    }
}
