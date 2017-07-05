package app.developteam.detectfall.myapplication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by barbie on 4/8/2017.
 */

public class DBData {
    @SerializedName("macaddress")
    private String macaddress;
    @SerializedName("activity")
    private String activity;
    @SerializedName("round")
    private String round;
    @SerializedName("ax")
    private String ax;
    @SerializedName("ay")
    private String  ay;
    @SerializedName("az")
    private String az;
    @SerializedName("accleration")
    private String acceleration;
    @SerializedName("time")
    private String time;

    public String getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getAx() {
        return ax;
    }

    public void setAx(String ax) {
        this.ax = ax;
    }

    public String getAy() {
        return ay;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }

    public String getAz() {
        return az;
    }

    public void setAz(String az) {
        this.az = az;
    }

    public String getAccleration() {
        return acceleration;
    }

    public void setAcceleration(String accleration) {
        this.acceleration = accleration;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
