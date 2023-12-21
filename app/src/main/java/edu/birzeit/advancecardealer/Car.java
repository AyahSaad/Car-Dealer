package edu.birzeit.advancecardealer;

public class Car {
    int id;
    String factoryName;
    String type;
    long price;
    String model;
    String name;
    long offer;
    String year;
    String fuelType;
    long rating;
    String accident;

    public Car() {
    }

    public Car(int id, String factoryName, String type, long price, String model, String name, long offer, String year, String fuelType, long rating, String accident) {
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
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

    public long getOffer() {
        return offer;
    }

    public void setOffer(long offer) {
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

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public String getAccident() {
        return accident;
    }

    public void setAccident(String accident) {
        this.accident = accident;
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
}
