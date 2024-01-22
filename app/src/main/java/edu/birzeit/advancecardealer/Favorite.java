package edu.birzeit.advancecardealer;

public class Favorite {
    int id;
    String email;
    int carId;

    public Favorite() {
    }

    public Favorite( String email, int carId) {
        this.email = email;
        this.carId = carId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUseremail() {
        return email;
    }

    public void setUseremail(String email) {
        this.email = email;
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
                ", userId=" + email +
                ", carId=" + carId +
                '}';
    }
}
