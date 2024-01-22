package edu.birzeit.advancecardealer;

public class Notification {


    String notificationText;

    public Notification(){

    }

    public Notification(String notificationText){
           this.notificationText = notificationText;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    @Override
    public String toString() {
        return "Notification{" +
                ", notification='" + notificationText + '\'' +
                '}';
    }
}