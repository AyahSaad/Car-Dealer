package edu.birzeit.advancecardealer;

public class Reserve {
    int carId;
   String email;
    String date;
    String time;

    public String getReturnedDate() {
        return ReturnedDate;
    }

    public void setReturnedDate(String returnedDate) {
        ReturnedDate = returnedDate;
    }

    String ReturnedDate;

    public Reserve() {
    }


    public Reserve(String email, int carId, String date, String time,String ReturnedDate) {

        this.carId = carId;
        this.email = email;
        this.date = date;
        this.time = time;
        this.ReturnedDate = ReturnedDate;
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

                ", CarId=" + carId +
                ", email='" + email + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", Return Date " + ReturnedDate+'\''+
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
