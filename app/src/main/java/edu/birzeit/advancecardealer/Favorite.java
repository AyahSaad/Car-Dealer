package edu.birzeit.advancecardealer;

public class Favorite {
    int id;
    int userId;
    int carId;

    public Favorite() {
    }

    public Favorite(int id, int userId, int carId) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", userId=" + userId +
                ", carId=" + carId +
                '}';
    }
}
