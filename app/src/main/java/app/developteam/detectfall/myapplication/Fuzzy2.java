package app.developteam.detectfall.myapplication;

import java.util.ArrayList;

/**
 * Created by barbie on 4/8/2017.
 */

public class Fuzzy2 {

    public static String FALL = "FALL";
    public static String NONE = "NONE";


    private double[] xlow = new double[]{0, 0, 5, 8};
//    private double[] xmedium = new double[]{4.5, 7.5, 13, 16};
    private double[] xmedium = new double[]{4.5, 7.5, 13, 15};
    private double[] xhigh = new double[]{13, 16, 18, 20};
    private double[] xhigher = new double[]{18, 20, 40, 40};

    private double[] ylow = new double[]{0, 0, 10, 15};
    private double[] ymedium = new double[]{9, 13.5, 20.5, 25};
    private double[] yhigh = new double[]{20, 25, 40, 40};

    private double[] zlow = new double[]{0, 0, 6, 9};
    private double[] zmedium = new double[]{7, 9.5, 12, 15};
    private double[] zhigh = new double[]{13, 15.3, 17, 19};
    private double[] zhigher = new double[]{16.5, 19, 40, 40};

    private double[] output_fall = new double[]{0, 0, 40, 60};
    private double[] output_nofall = new double[]{40, 60, 100, 100};

    //MS/G TO G = 9.80665
    private double DOM_X_LOW = 0;
    private double DOM_X_MEDIUM = 0;
    private double DOM_X_HIGH = 0;
    private double DOM_X_HIGHER = 0;


    private double DOM_Y_LOW = 0;
    private double DOM_Y_MEDIUM = 0;
    private double DOM_Y_HIGH = 0;

    private double DOM_Z_LOW = 0;
    private double DOM_Z_MEDIUM = 0;
    private double DOM_Z_HIGH = 0;
    private double DOM_Z_HIGHER = 0;


    private ArrayList<Double> accelerations;

    public Fuzzy2() {
        accelerations = new ArrayList<>();
    }

    public void calculateXMemberShipFx(double x) {

        if (x >= xlow[0] && x <= xlow[3]) {
            if (x >= xlow[1] && x <= xlow[2]) {
                DOM_X_LOW = 1;
            } else if (x > xlow[2] && x <= xlow[3]) {
                DOM_X_LOW = (xlow[3] - x) / (xlow[3] - xlow[2]);
            } else {
                DOM_X_LOW = 0;
            }

        }

        if (x >= xmedium[0] && x <= xmedium[3]) {
            if (x >= xmedium[0] && x < xmedium[1]) {
                DOM_X_MEDIUM = (x - xmedium[0]) / (xmedium[1] - xmedium[0]);
            } else if (x >= xmedium[1] && x <= xmedium[2]) {
                DOM_X_MEDIUM = 1;
            } else if (x > xmedium[2] && x <= xmedium[3]) {
                DOM_X_MEDIUM = (xmedium[3] - x) / (xmedium[3] - xmedium[2]);
            } else {
                DOM_X_MEDIUM = 0;
            }

        }
        if (x >= xhigh[0] && x <= xhigh[3]) {
            if (x >= xhigh[0] && x < xhigh[1]) {
                DOM_X_HIGH = (x - xhigh[0]) / (xhigh[1] - xhigh[0]);
            } else if (x >= xhigh[1] && x <= xhigh[2]) {
                DOM_X_HIGH = 1;
            } else if (x > xhigh[2] && x <= xhigh[3]) {
                DOM_X_HIGH = (xhigh[3] - x) / (xhigh[3] - xhigh[2]);
            } else {
                DOM_X_HIGH = 0;
            }

        }
        if (x >= xhigher[0] && x <= xhigher[3]) {
            if (x >= xhigher[0] && x < xhigher[1]) {
                DOM_X_HIGHER = (x - xhigher[0]) / (xhigher[1] - xhigher[0]);
            } else if (x >= xhigher[1] && x <= xhigher[2]) {
                DOM_X_HIGHER = 1;
            } else {
                DOM_X_HIGHER = 0;
            }

        }
    }

    public void calculateYMemberShipFx(double y) {

        if (y <= ylow[3]) {
            if (y >= ylow[1] && y <= ylow[2]) {
                DOM_Y_LOW = 1;
            } else if (y > ylow[2] && y <= ylow[3]) {
                DOM_Y_LOW = (ylow[3] - y) / (ylow[3] - ylow[2]);
            } else {
                DOM_Y_LOW = 0;
            }


        }
        if (y >= ymedium[0] && y <= ymedium[3]) {
            if (y >= ymedium[0] && y < ymedium[1]) {
                DOM_Y_MEDIUM = (y - ymedium[0]) / (ymedium[1] - ymedium[0]);
            } else if (y >= ymedium[1] && y <= ymedium[2]) {
                DOM_Y_MEDIUM = 1;
            } else if (y > ymedium[2] && y <= ymedium[3]) {
                DOM_Y_MEDIUM = (ymedium[3] - y) / (ymedium[3] - ymedium[2]);
            } else {
                DOM_Y_MEDIUM = 0;
            }

        }

        if (y >= yhigh[0] && y <= yhigh[3]) {
            if (y >= yhigh[0] && y < yhigh[1]) {
                DOM_Y_HIGH = (y - yhigh[0]) / (yhigh[1] - yhigh[0]);
            } else if (y >= yhigh[1] && y <= yhigh[2]) {
                DOM_Y_HIGH = 1;
            } else {
                DOM_Y_HIGH = 0;
            }

        }

    }

    public void calculateZMemberShipFx(double z) {

        if (z <= zlow[3]) {
            if (z >= zlow[1] && z <= zlow[2]) {
                DOM_Z_LOW = 1;
            } else if (z > zlow[2] && z <= zlow[3]) {
                DOM_Z_LOW = (zlow[3] - z) / (zlow[3] - zlow[2]);
            } else {
                DOM_Z_LOW = 0;
            }


        }
        if (z >= zmedium[0] && z <= zmedium[3]) {
            if (z >= zmedium[0] && z < zmedium[1]) {
                DOM_Z_MEDIUM = (z - zmedium[0]) / (zmedium[1] - zmedium[0]);
            } else if (z >= zmedium[1] && z <= zmedium[2]) {
                DOM_Z_MEDIUM = 1;
            } else if (z > zmedium[2] && z <= zmedium[3]) {
                DOM_Z_MEDIUM = (zmedium[3] - z) / (zmedium[3] - zmedium[2]);
            } else {
                DOM_Z_MEDIUM = 0;
            }

        }
        if (z >= zhigh[0] && z <= zhigh[3]) {
            if (z >= zhigh[0] && z < zhigh[1]) {
                DOM_Z_HIGH = (z - zhigh[0]) / (zhigh[1] - zhigh[0]);
            } else if (z >= zhigh[1] && z <= zhigh[2]) {
                DOM_Z_HIGH = 1;
            } else if (z > zhigh[2] && z <= zhigh[3]) {
                DOM_Z_HIGH = (zhigh[3] - z) / (zhigh[3] - zhigh[2]);
            } else {
                DOM_Z_HIGH = 0;
            }

        }

        if (z >= zhigher[0] && z <= zhigher[3]) {
            if (z >= zhigher[0] && z < zhigher[1]) {
                DOM_Z_HIGHER = (z - zhigher[0]) / (zhigher[1] - zhigher[0]);
            } else if (z >= zhigher[1] && z <= zhigher[2]) {
                DOM_Z_HIGHER = 1;
            } else {
                DOM_Z_HIGHER = 0;
            }

        }

    }


    private void detectXMaxMemberShip() {
        if (DOM_X_LOW > DOM_X_MEDIUM && DOM_X_LOW > DOM_X_HIGH && DOM_X_LOW > DOM_X_HIGHER) {
            DOM_X_MEDIUM = 0;
            DOM_X_HIGH = 0;
            DOM_X_HIGHER = 0;
        } else if (DOM_X_MEDIUM > DOM_X_LOW && DOM_X_MEDIUM > DOM_X_HIGH && DOM_X_MEDIUM > DOM_X_HIGHER) {
            DOM_X_LOW = 0;
            DOM_X_HIGH = 0;
            DOM_X_HIGHER = 0;
        } else if (DOM_X_HIGH > DOM_X_LOW && DOM_X_HIGH > DOM_X_MEDIUM && DOM_X_HIGH > DOM_X_HIGHER) {
            DOM_X_LOW = 0;
            DOM_X_MEDIUM = 0;
            DOM_X_HIGHER = 0;
        } else {
            DOM_X_LOW = 0;
            DOM_X_MEDIUM = 0;
            DOM_X_HIGH = 0;
        }
    }

    private void detectYMaxMemberShip() {

        if (DOM_Y_LOW > DOM_Y_MEDIUM && DOM_Y_LOW > DOM_Y_HIGH) {
            DOM_Y_MEDIUM = 0;
            DOM_Y_HIGH = 0;
        } else if (DOM_Y_MEDIUM > DOM_Y_LOW && DOM_Y_MEDIUM > DOM_Y_HIGH) {
            DOM_Y_LOW = 0;
            DOM_Y_HIGH = 0;
        } else {
            DOM_X_LOW = 0;
            DOM_X_MEDIUM = 0;
        }
    }

    private void detectZMaxMemberShip() {

        if (DOM_Z_LOW > DOM_Z_MEDIUM && DOM_Z_LOW > DOM_Z_HIGH && DOM_Z_LOW > DOM_Z_HIGHER) {
            DOM_Z_MEDIUM = 0;
            DOM_Z_HIGH = 0;
            DOM_Z_HIGHER = 0;
        } else if (DOM_Z_MEDIUM > DOM_Z_LOW && DOM_Z_MEDIUM > DOM_Z_HIGH && DOM_Z_MEDIUM > DOM_Z_HIGHER) {
            DOM_Z_LOW = 0;
            DOM_Z_HIGH = 0;
            DOM_Z_HIGHER = 0;
        } else if (DOM_Z_HIGH > DOM_Z_LOW && DOM_Z_HIGH > DOM_Z_MEDIUM && DOM_Z_HIGH > DOM_Z_HIGHER) {
            DOM_Z_LOW = 0;
            DOM_Z_MEDIUM = 0;
            DOM_Z_HIGHER = 0;
        } else {
            DOM_Z_LOW = 0;
            DOM_Z_MEDIUM = 0;
            DOM_Z_HIGH = 0;
        }
    }

    public String rulesInference() {
        detectXMaxMemberShip();
        detectYMaxMemberShip();
        detectZMaxMemberShip();

        if ((DOM_X_LOW > 0 && DOM_Y_LOW > 0 && DOM_Z_HIGHER > 0)
                || (DOM_X_LOW > 0 && DOM_Y_MEDIUM > 0 && DOM_Z_HIGHER > 0)

                || (DOM_X_MEDIUM > 0 && DOM_Y_LOW > 0 && DOM_Z_HIGHER > 0)
                || (DOM_X_MEDIUM > 0 && DOM_Y_MEDIUM > 0 && DOM_Z_HIGHER > 0)
                || (DOM_X_MEDIUM > 0 && DOM_Y_HIGH > 0 && DOM_Z_HIGH > 0)
                || (DOM_X_MEDIUM > 0 && DOM_Y_HIGH > 0 && DOM_Z_HIGHER > 0)

                || (DOM_X_HIGH > 0 && DOM_Y_LOW > 0 && DOM_Z_LOW > 0)
                || (DOM_X_HIGH > 0 && DOM_Y_LOW > 0 && DOM_Z_MEDIUM > 0)
                || (DOM_X_HIGH > 0 && DOM_Y_LOW > 0 && DOM_Z_HIGH > 0)
                || (DOM_X_HIGH > 0 && DOM_Y_LOW > 0 && DOM_Z_HIGHER > 0)
                || (DOM_X_HIGH > 0 && DOM_Y_MEDIUM > 0 && DOM_Z_LOW > 0)
                || (DOM_X_HIGH > 0 && DOM_Y_MEDIUM > 0 && DOM_Z_MEDIUM > 0)
                || (DOM_X_HIGH > 0 && DOM_Y_MEDIUM > 0 && DOM_Z_HIGH > 0)
                || (DOM_X_HIGH > 0 && DOM_Y_MEDIUM > 0 && DOM_Z_HIGHER > 0)
                || (DOM_X_HIGH > 0 && DOM_Y_HIGH > 0 && DOM_Z_MEDIUM > 0)
                || (DOM_X_HIGH > 0 && DOM_Y_HIGH > 0 && DOM_Z_HIGHER > 0)

                || (DOM_X_HIGHER > 0 && DOM_Y_LOW > 0 && DOM_Z_LOW > 0)
                || (DOM_X_HIGHER > 0 && DOM_Y_LOW > 0 && DOM_Z_MEDIUM > 0)
                || (DOM_X_HIGHER > 0 && DOM_Y_LOW > 0 && DOM_Z_HIGH > 0)
                || (DOM_X_HIGHER > 0 && DOM_Y_LOW > 0 && DOM_Z_HIGHER > 0)
		        || (DOM_X_HIGHER > 0 && DOM_Y_MEDIUM > 0 && DOM_Z_LOW > 0)
                || (DOM_X_HIGHER > 0 && DOM_Y_MEDIUM > 0 && DOM_Z_MEDIUM > 0)
                || (DOM_X_HIGHER > 0 && DOM_Y_MEDIUM > 0 && DOM_Z_HIGH > 0)
                || (DOM_X_HIGHER > 0 && DOM_Y_MEDIUM > 0 && DOM_Z_HIGHER > 0)
                || (DOM_X_HIGHER > 0 && DOM_Y_HIGH > 0 && DOM_Z_LOW > 0)
                || (DOM_X_HIGHER > 0 && DOM_Y_HIGH > 0 && DOM_Z_HIGH > 0)
                || (DOM_X_HIGHER > 0 && DOM_Y_HIGH > 0 && DOM_Z_HIGHER > 0))
                {
            return Fuzzy2.FALL;
        } else {
            return Fuzzy2.NONE;
        }

    }


    public void clearData() {
        DOM_X_LOW = 0;
        DOM_X_MEDIUM = 0;
        DOM_X_HIGH = 0;
        DOM_X_HIGHER = 0;

        DOM_Y_LOW = 0;
        DOM_Y_MEDIUM = 0;
        DOM_Y_HIGH = 0;

        DOM_Z_LOW = 0;
        DOM_Z_MEDIUM = 0;
        DOM_Z_HIGH = 0;
        DOM_Z_HIGHER = 0;


    }

    public double getDOM_X_LOW() {
        return DOM_X_LOW;
    }

    public double getDOM_X_MEDIUM() {
        return DOM_X_MEDIUM;
    }

    public double getDOM_X_HIGH() {
        return DOM_X_HIGH;
    }

    public double getDOM_X_HIGHER() {
        return DOM_X_HIGHER;
    }

    public double getDOM_Y_LOW() {
        return DOM_Y_LOW;
    }

    public double getDOM_Y_MEDIUM() {
        return DOM_Y_MEDIUM;
    }

    public double getDOM_Y_HIGH() {
        return DOM_Y_HIGH;
    }

    public double getDOM_Z_LOW() {
        return DOM_Z_LOW;
    }

    public double getDOM_Z_MEDIUM() {
        return DOM_Z_MEDIUM;
    }

    public double getDOM_Z_HIGH() {
        return DOM_Z_HIGH;
    }

    public double getDOM_Z_HIGHER() {
        return DOM_Z_HIGHER;
    }

}
