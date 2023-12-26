package edu.birzeit.advancecardealer;

public class Reserve {
    int id;
    int carId;
   String email;
    String date;
    String time;

    public Reserve() {
    }


    public Reserve(int id,String email, int carId, String date, String time) {
        this.id = id;
        this.carId = carId;
        this.email = email;
        this.date = date;
        this.time = time;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                ", CarId=" + carId +
                ", email='" + email + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
