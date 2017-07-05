package app.developteam.detectfall.myapplication;

import java.util.ArrayList;

/**
 * Created by barbie on 2/28/2017.
 */

class Calculator {
    ArrayList<Double> data;

    public Calculator() {

    }

    public double calVariance(ArrayList<Double> data){
        double mean =calMean(data);
        double sum =0;

        for(int i=3;i<data.size();i++){
            sum+=Math.pow(data.get(i)-mean,2);
        }

        return sum/((data.size()-4));
    }

    private double calMean(ArrayList<Double> data){
        double sum=0;
        for(int i=0;i<data.size();i++){
            sum+=data.get(i);
        }

        return sum/data.size();
    }

    public int calRoll(double y, double z) {
        int roll = 0;
        roll = (int) (Math.atan2(-y, z) * 180 / 3.141592);
        return roll;
    }

    public int calYaw(double x, double z) {
        int yaw = 0;
        yaw = (int) (Math.atan(z / Math.sqrt(x * x + z * z)) * 180 / 3.141592);
        return yaw;
    }

    public int calPitch(double x, double y, double z) {
        int pitch = 0;
        pitch = (int) (Math.atan2(x, Math.sqrt(y * y + z * z)) * 360 / 3.141592);
        return pitch;
    }

    public double calAngle(double radian){
        double pi=3.141592;
        double angle =0;
        angle = (180/pi)*radian;
        return angle;
    }
}
