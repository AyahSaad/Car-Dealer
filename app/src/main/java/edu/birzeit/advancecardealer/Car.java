package edu.birzeit.advancecardealer;


import android.os.Bundle;

import java.io.Serializable;

public class Car  {
    int id;
    String factoryName;
    String type;
    double price;
    String model;
    String name;
    double offer;
    String year;
    String fuelType;
    double rating;
    String accident;
    String color;
    String  hasAspare;
    int DoorsCount;
    String company;
    String image;

    public Car(){


    }

    public Car(int id, String factoryName, String type, double price, String model, String name, double offer, String year, String fuelType, double rating, String accident, String color, String hasAspare, int doorsCount, String  image,String company) {
        this.id = id;
        this.factoryName = factoryName;
        this.type = type;
        this.price = price;
        this.model = model;
        this.name = name;
        this.offer = offer;
        this.year = year;
        this.fuelType = fuelType;
        this.rating = rating;
        this.accident = accident;
        this.color = color;
        this.hasAspare = hasAspare;
        this.DoorsCount = doorsCount;
        this.image = image;
        this.company = company;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOffer() {
        return offer;
    }

    public void setOffer(double offer) {
        this.offer = offer;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getAccident() {
        return accident;
    }

    public void setAccident(String accident) {
        this.accident = accident;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", factoryName='" + factoryName + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                ", offer=" + offer +
                ", year='" + year + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", rating=" + rating +
                ", accident='" + accident + '\'' +
                '}';
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHasAspare() {
        return hasAspare;
    }

    public void setHasAspare(String hasAspare) {
        this.hasAspare = hasAspare;
    }

    public int getDoorsCount() {
        return DoorsCount;
    }

    public void setDoorsCount(int doorsCount) {
        DoorsCount = doorsCount;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
