package com.fredrik.cigarapp.model;

import jakarta.persistence.*;

@Entity
@Table(name="cigar")
public class Cigar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;
    @Column
    private String brand;
    @Column
    private String origin;
    @Column
    private String duration;
    @Column
    private String body;
    @Column
    private String length;
    @Column
    private String handRolled;
    @Column
    private String ringGage;
    @Column
    private String status;
    @Column
    private String price;
    @Column
    private String boxPrice;
    @Column
    private String boxAmount;
    @Column
    private String rating;
    @Column
    private String favoriteOf;
    @Column
    private String imagePath;
    @Column
    private String brandImagePath;
    @Column
    private String originImagePath;
    @Column
    private String profilePicture;

    public Cigar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getHandRolled() {
        return handRolled;
    }

    public void setHandRolled(String handRolled) {
        this.handRolled = handRolled;
    }

    public String getRingGage() {
        return ringGage;
    }

    public void setRingGage(String ringGage) {
        this.ringGage = ringGage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBoxPrice() {
        return boxPrice;
    }

    public void setBoxPrice(String boxPrice) {
        this.boxPrice = boxPrice;
    }

    public String getBoxAmount() {
        return boxAmount;
    }

    public void setBoxAmount(String boxAmount) {
        this.boxAmount = boxAmount;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFavoriteOf() {
        return favoriteOf;
    }

    public void setFavoriteOf(String favoriteOf) {
        this.favoriteOf = favoriteOf;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getBrandImagePath() {
        return brandImagePath;
    }

    public void setBrandImagePath(String brandImagePath) {
        this.brandImagePath = brandImagePath;
    }

    public String getOriginImagePath() {
        return originImagePath;
    }

    public void setOriginImagePath(String originImagePath) {
        this.originImagePath = originImagePath;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}