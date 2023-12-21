package edu.birzeit.advancecardealer;

public class Reserve {
    int id;
    int userId;
    int carId;
    String date;
    String time;

    public Reserve() {
    }


    public Reserve(int id, int userId, int carId, String date, String time) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.date = date;
        this.time = time;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "Reserve{" +
                "id=" + id +
                ", userId=" + userId +
                ", carId=" + carId +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
