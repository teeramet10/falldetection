package app.developteam.detectfall.myapplication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by barbie on 5/10/2017.
 */

public class SMSData {

    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("msisdn")
    private String number;
    @SerializedName("message")
    private String message;
    @SerializedName("sender")
    private String  sender;
    @SerializedName("ScheduledDelivery")
    private String datetime;
    @SerializedName("force")
    private String force;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }
}
