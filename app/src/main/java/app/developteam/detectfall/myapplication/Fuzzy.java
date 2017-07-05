package app.developteam.detectfall.myapplication;

import java.util.ArrayList;

/**
 * Created by barbie on 2/11/2017.
 */

public class Fuzzy {

    public static String FALL = "FALL";
    public static String NONE = "NONE";


    private double[] x_highnegative = new double[]{-20, -20, -15, -14};
    private double[] x_negative = new double[]{-15, -14, -11, -8};
    private double[] x_zero = new double[]{-10, -8, 8, 10};
    private double[] x_positive = new double[]{8, 11, 14, 15};
    private double[] x_highpositive = new double[]{14, 15, 20, 20};

    private double[] y_highnegative = new double[]{-20, -20, -14, 10};
    private double[] y_negative = new double[]{-13, -11, 0, 2};
    private double[] y_positive = new double[]{0, 2, 13, 15};
    private double[] y_highpositive = new double[]{13, 15, 20, 20};

    private double[] z_highnegative = new double[]{-20, -20, -15, -14};
    private double[] z_negative = new double[]{-15, -14, -11, -8};
    private double[] z_zero = new double[]{-10, -8, 8, 10};
    private double[] z_positive = new double[]{8, 11, 14, 15};
    private double[] z_highpositive = new double[]{14, 15, 20, 20};

    private double[] output_fall = new double[]{};
    private double[] output_walkrun = new double[]{};

    //MS/G TO G = 9.80665
    private double DOM_X_HNegative = 0;
    private double DOM_X_Negative = 0;
    private double DOM_X_Zero = 0;
    private double DOM_X_Positive = 0;
    private double DOM_X_HPositive = 0;

    private double DOM_Y_HNegative = 0;
    private double DOM_Y_NEGATIVE = 0;
    private double DOM_Y_Positive = 0;
    private double DOM_Y_HPositive = 0;

    private double DOM_Z_HNegative = 0;
    private double DOM_Z_Negative = 0;
    private double DOM_Z_Zero = 0;
    private double DOM_Z_Positive = 0;
    private double DOM_Z_HPositive = 0;

    private double DOM_ACC = 0;

    private ArrayList<Double> accelerations;

    public Fuzzy() {
        accelerations = new ArrayList<>();
    }

    public void calculateXMemberShipFx(double x) {

        if (x >= x_highnegative[0] && x <= x_highnegative[3]) {
            if (x >= x_highnegative[1] && x <= x_highnegative[2]) {
                DOM_X_HNegative = 1;
            } else if (x > x_highnegative[2] && x <= x_highnegative[3]) {
                DOM_X_HNegative = (x_highnegative[3] - x) / (x_highnegative[3] - x_highnegative[2]);
            } else {
                DOM_X_HNegative = 0;
            }

        }

        if (x >= x_negative[0] && x <= x_negative[3]) {
            if (x >= x_negative[0] && x < x_negative[1]) {
                DOM_X_Negative = (x - x_negative[0]) / (x_negative[1] - x_negative[0]);
            } else if (x >= x_negative[1] && x <= x_negative[2]) {
                DOM_X_Negative = 1;
            } else if (x > x_negative[2] && x <= x_negative[3]) {
                DOM_X_Negative = (x_negative[3] - x) / (x_negative[3] - x_negative[2]);
            } else {
                DOM_X_Negative = 0;
            }

        }
        if (x >= x_zero[0] && x <= x_zero[3]) {
            if (x >= x_zero[0] && x < x_zero[1]) {
                DOM_X_Zero = (x - x_zero[0]) / (x_zero[1] - x_zero[0]);
            } else if (x >= x_zero[1] && x <= x_zero[2]) {
                DOM_X_Zero = 1;
            } else if (x > x_zero[2] && x <= x_zero[3]) {
                DOM_X_Zero = (x_zero[3] - x) / (x_zero[3] - x_zero[2]);
            } else {
                DOM_X_Zero = 0;
            }

        }
        if (x >= x_positive[0] && x <= x_positive[3]) {
            if (x >= x_positive[0] && x < x_positive[1]) {
                DOM_X_Positive = (x - x_positive[0]) / (x_positive[1] - x_positive[0]);
            } else if (x >= x_positive[1] && x <= x_positive[2]) {
                DOM_X_Positive = 1;
            } else if (x > x_positive[2] && x <= x_positive[3]) {
                DOM_X_Positive = (x_positive[3] - x) / (x_positive[3] - x_positive[2]);
            } else {
                DOM_X_Positive = 0;
            }

        }

        if (x >= x_highpositive[0] && x <= x_highpositive[3]) {
            if (x >= x_highpositive[0] && x < x_highpositive[1]) {
                DOM_X_HPositive = (x - x_highpositive[0]) / (x_highpositive[1] - x_highpositive[0]);
            } else if (x >= x_highpositive[1] && x <= x_highpositive[2]) {
                DOM_X_HPositive = 1;
            } else {
                DOM_X_HPositive = 0;
            }

        }
    }

    public void calculateYMemberShipFx(double y) {

        if (y <= y_highnegative[3]) {
            if (y >= y_highnegative[1] && y <= y_highnegative[2]) {
                DOM_Y_HNegative = 1;
            } else if (y > y_highnegative[2] && y <= y_highnegative[3]) {
                DOM_Y_HNegative = (y_highnegative[3] - y) / (y_highnegative[3] - y_highnegative[2]);
            } else {
                DOM_Y_HNegative = 0;
            }


        }
        if (y >= y_negative[0] && y <= y_negative[3]) {
            if (y >= y_negative[0] && y < y_negative[1]) {
                DOM_Y_NEGATIVE = (y - y_negative[0]) / (y_negative[1] - y_negative[0]);
            } else if (y >= y_negative[1] && y <= y_negative[2]) {
                DOM_Y_NEGATIVE = 1;
            } else if (y > y_negative[2] && y <= y_negative[3]) {
                DOM_Y_NEGATIVE = (y_negative[3] - y) / (y_negative[3] - y_negative[2]);
            } else {
                DOM_Y_NEGATIVE = 0;
            }

        }

        if (y >= y_positive[0] && y <= y_positive[3]) {
            if (y >= y_positive[0] && y < y_positive[1]) {
                DOM_Y_Positive = (y - y_positive[0]) / (y_positive[1] - y_positive[0]);
            } else if (y >= y_positive[1] && y <= y_positive[2]) {
                DOM_Y_Positive = 1;
            } else if (y > y_positive[2] && y <= y_positive[3]) {
                DOM_Y_Positive = (y_positive[3] - y) / (y_positive[3] - y_positive[2]);
            } else {
                DOM_Y_Positive = 0;
            }

        }

        if (y >= y_highpositive[0] && y <= y_highpositive[3]) {
            if (y >= y_highpositive[0] && y < y_highpositive[1]) {
                DOM_Y_HPositive = (y - y_highpositive[0]) / (y_highpositive[1] - y_highpositive[0]);
            } else if (y >= y_highpositive[1] && y <= y_highpositive[2]) {
                DOM_Y_HPositive = 1;
            } else {
                DOM_Y_HPositive = 0;
            }

        }

    }

    public void calculateZMemberShipFx(double z) {

        if (z <= z_highnegative[3]) {
            if (z >= z_highnegative[1] && z <= z_highnegative[2]) {
                DOM_Z_HNegative = 1;
            } else if (z > z_highnegative[2] && z <= z_highnegative[3]) {
                DOM_Z_HNegative = (z_highnegative[3] - z) / (z_highnegative[3] - z_highnegative[2]);
            } else {
                DOM_Z_HNegative = 0;
            }


        }
        if (z >= z_zero[0] && z <= z_zero[3]) {
            if (z >= z_zero[0] && z < z_zero[1]) {
                DOM_Z_Zero = (z - z_zero[0]) / (z_zero[1] - z_zero[0]);
            } else if (z >= z_zero[1] && z <= z_zero[2]) {
                DOM_Z_Zero = 1;
            } else if (z > z_zero[2] && z <= z_zero[3]) {
                DOM_Z_Zero = (z_zero[3] - z) / (z_zero[3] - z_zero[2]);
            } else {
                DOM_Z_Zero = 0;
            }

        }

        if (z >= z_negative[0] && z <= z_negative[3]) {
            if (z >= z_negative[0] && z < z_negative[1]) {
                DOM_Z_Negative = (z - z_negative[0]) / (z_negative[1] - z_negative[0]);
            } else if (z >= z_negative[1] && z <= z_negative[2]) {
                DOM_Z_Negative = 1;
            } else if (z > z_negative[2] && z <= z_negative[3]) {
                DOM_Z_Negative = (z_negative[3] - z) / (z_negative[3] - z_negative[2]);
            } else {
                DOM_Z_Negative = 0;
            }

        }
        if (z >= z_positive[0] && z <= z_positive[3]) {
            if (z >= z_positive[0] && z < z_positive[1]) {
                DOM_Z_Positive = (z - z_positive[0]) / (z_positive[1] - z_positive[0]);
            } else if (z >= z_positive[1] && z <= z_positive[2]) {
                DOM_Z_Positive = 1;
            } else if (z > z_positive[2] && z <= z_positive[3]) {
                DOM_Z_Positive = (z_positive[3] - z) / (z_positive[3] - z_positive[2]);
            } else {
                DOM_Z_Positive = 0;
            }

        }

        if (z >= z_highpositive[0] && z <= z_highpositive[3]) {
            if (z >= z_highpositive[0] && z < z_highpositive[1]) {
                DOM_Z_HPositive = (z - z_highpositive[0]) / (z_highpositive[1] - z_highpositive[0]);
            } else if (z >= z_highpositive[1] && z <= z_highpositive[2]) {
                DOM_Z_HPositive = 1;
            } else {
                DOM_Z_HPositive = 0;
            }

        }

    }
//
//    public void calculateAcclerationMemberShipFx(double acc) {
//        if (acc >= 16) {
//            DOM_ACC = 1;
//        }
//    }

    public String rulesInference() {
        detectXMaxMemberShip();
        detectYMaxMemberShip();
        detectZMaxMemberShip();

        if ((DOM_X_HNegative > 0 && DOM_Y_HNegative > 0 && DOM_Z_HNegative > 0)
                || (DOM_X_HNegative > 0 && DOM_Y_NEGATIVE > 0 && DOM_Z_HNegative > 0)
                || (DOM_X_HNegative > 0 && DOM_Y_NEGATIVE > 0 && DOM_Z_Zero > 0)
                || (DOM_X_HNegative > 0 && DOM_Y_NEGATIVE > 0 && DOM_Z_HPositive > 0)

                || (DOM_X_HNegative > 0 && DOM_Y_Positive > 0 && DOM_Z_HNegative > 0)
                || (DOM_X_HNegative > 0 && DOM_Y_Positive > 0 && DOM_Z_Zero > 0)
                || (DOM_X_HNegative > 0 && DOM_Y_Positive > 0 && DOM_Z_Positive > 0)
                || (DOM_X_HNegative > 0 && DOM_Y_Positive > 0 && DOM_Z_HPositive > 0)

                || (DOM_X_HNegative > 0 && DOM_Y_HPositive > 0 && DOM_Z_HNegative > 0)
                || (DOM_X_HNegative > 0 && DOM_Y_HPositive > 0 && DOM_Z_Zero > 0)
                || (DOM_X_HNegative > 0 && DOM_Y_HPositive > 0 && DOM_Z_HPositive > 0)
                //---------------------------------------X_HNEGATIVE-----------------------------
                || (DOM_X_Negative > 0 && DOM_Y_HPositive > 0 && DOM_Z_HPositive > 0)
                || (DOM_X_Negative > 0 && DOM_Y_HNegative > 0 && DOM_Z_HPositive > 0)
                //---------------------------------------X_NEGATIVE-----------------------------
                || (DOM_X_Zero > 0 && DOM_Y_HNegative > 0 && DOM_Z_HNegative > 0)
                || (DOM_X_Zero > 0 && DOM_Y_HNegative > 0 && DOM_Z_HPositive > 0)
                || (DOM_X_Zero > 0 && DOM_Y_NEGATIVE > 0 && DOM_Z_HNegative > 0)
                || (DOM_X_Zero > 0 && DOM_Y_NEGATIVE > 0 && DOM_Z_HPositive > 0)
                || (DOM_X_Zero > 0 && DOM_Y_Positive > 0 && DOM_Z_HNegative > 0)
                //---------------------------------------X_ZERO-----------------------------
                || (DOM_X_Positive > 0 && DOM_Y_HNegative > 0 && DOM_Z_HPositive > 0)
                || (DOM_X_Positive > 0 && DOM_Y_NEGATIVE > 0 && DOM_Z_Negative > 0)
                //---------------------------------------X_POSITIVE-----------------------------
                || (DOM_X_HPositive > 0 && DOM_Y_HNegative > 0 && DOM_Z_HNegative > 0)
                || (DOM_X_HPositive > 0 && DOM_Y_HNegative > 0 && DOM_Z_Negative > 0)
                || (DOM_X_HPositive > 0 && DOM_Y_HNegative > 0 && DOM_Z_Zero > 0)

                || (DOM_X_HPositive > 0 && DOM_Y_NEGATIVE > 0 && DOM_Z_HNegative > 0)
                || (DOM_X_HPositive > 0 && DOM_Y_NEGATIVE > 0 && DOM_Z_Negative > 0)
                || (DOM_X_HPositive > 0 && DOM_Y_NEGATIVE > 0 && DOM_Z_Zero > 0)
                || (DOM_X_HPositive > 0 && DOM_Y_NEGATIVE > 0 && DOM_Z_HPositive > 0)

                || (DOM_X_HPositive > 0 && DOM_Y_Positive > 0 && DOM_Z_Zero > 0)

                || (DOM_X_HPositive > 0 && DOM_Y_HPositive > 0 && DOM_Z_Zero > 0)
                || (DOM_X_HPositive > 0 && DOM_Y_HPositive > 0 && DOM_Z_HPositive > 0)) {
            return FALL;
        } else {
            return NONE;
        }


    }


    private void detectXMaxMemberShip() {
        if (DOM_X_HNegative > DOM_X_Negative && DOM_X_HNegative > DOM_X_Zero && DOM_X_HNegative > DOM_X_Positive && DOM_X_HNegative > DOM_X_HPositive) {
            DOM_X_Negative = 0;
            DOM_X_Zero = 0;
            DOM_X_Positive = 0;
            DOM_X_HPositive = 0;
        } else if (DOM_X_Negative > DOM_X_HNegative && DOM_X_Negative > DOM_X_Zero && DOM_X_Negative > DOM_X_Positive && DOM_X_Negative > DOM_X_HPositive) {
            DOM_X_HNegative = 0;
            DOM_X_Zero = 0;
            DOM_X_Positive = 0;
            DOM_X_HPositive = 0;
        } else if (DOM_X_Zero > DOM_X_HNegative && DOM_X_Zero > DOM_X_Negative && DOM_X_Zero > DOM_X_Positive && DOM_X_Zero > DOM_X_HPositive) {
            DOM_X_HNegative = 0;
            DOM_X_Negative = 0;
            DOM_X_Positive = 0;
            DOM_X_HPositive = 0;
        } else if (DOM_X_Positive > DOM_X_HNegative && DOM_X_Positive > DOM_X_Negative && DOM_X_Zero > DOM_X_Positive && DOM_X_HNegative > DOM_X_HPositive) {
            DOM_X_HNegative = 0;
            DOM_X_Negative = 0;
            DOM_X_Zero = 0;
            DOM_X_HPositive = 0;
        } else if (DOM_X_HPositive > DOM_X_HNegative && DOM_X_HPositive > DOM_X_Negative && DOM_X_HPositive > DOM_X_Zero && DOM_X_HPositive > DOM_X_HPositive) {
            DOM_X_HNegative = 0;
            DOM_X_Negative = 0;
            DOM_X_Zero = 0;
            DOM_X_Positive = 0;
        }
    }

    private void detectYMaxMemberShip() {
        if (DOM_Y_HNegative > DOM_Y_NEGATIVE && DOM_Y_HNegative > DOM_Y_Positive && DOM_Y_HNegative > DOM_Y_HPositive) {
            DOM_Y_NEGATIVE = 0;
            DOM_Y_Positive = 0;
            DOM_Y_HPositive = 0;
        } else if (DOM_Y_NEGATIVE > DOM_Y_HNegative && DOM_Y_NEGATIVE > DOM_Y_Positive && DOM_Y_NEGATIVE > DOM_Y_HPositive) {
            DOM_Y_HNegative = 0;
            DOM_Y_Positive = 0;
            DOM_Y_HPositive = 0;
        } else if (DOM_Y_Positive > DOM_Y_HNegative && DOM_Y_Positive > DOM_Y_NEGATIVE && DOM_Y_Positive > DOM_Y_HPositive) {
            DOM_Y_HNegative = 0;
            DOM_Y_NEGATIVE = 0;
            DOM_Y_HPositive = 0;
        } else {
            DOM_Y_HNegative = 0;
            DOM_Y_NEGATIVE = 0;
            DOM_Y_Positive = 0;
        }

    }

    private void detectZMaxMemberShip() {

        if (DOM_Z_HNegative > DOM_Z_Negative && DOM_Z_HNegative > DOM_Z_Zero && DOM_Z_HNegative > DOM_Z_Positive && DOM_Z_HNegative > DOM_Z_HPositive) {
            DOM_Z_Negative = 0;
            DOM_Z_Zero = 0;
            DOM_Z_Positive = 0;
            DOM_Z_HPositive = 0;
        } else if (DOM_Z_Negative > DOM_Z_HNegative && DOM_Z_Negative > DOM_Z_Zero && DOM_Z_Negative > DOM_Z_Positive && DOM_Z_Negative > DOM_Z_HPositive) {
            DOM_Z_HNegative = 0;
            DOM_Z_Zero = 0;
            DOM_Z_Positive = 0;
            DOM_Z_HPositive = 0;
        } else if (DOM_Z_Zero > DOM_Z_HNegative && DOM_Z_Zero > DOM_Z_Negative && DOM_Z_Zero > DOM_Z_Positive && DOM_Z_Zero > DOM_Z_HPositive) {
            DOM_Z_HNegative = 0;
            DOM_Z_Negative = 0;
            DOM_Z_Positive = 0;
            DOM_Z_HPositive = 0;
        } else if (DOM_Z_Positive > DOM_Z_HNegative && DOM_Z_Positive > DOM_Z_Negative && DOM_Z_Zero > DOM_Z_Positive && DOM_Z_HNegative > DOM_Z_HPositive) {
            DOM_Z_HNegative = 0;
            DOM_Z_Negative = 0;
            DOM_Z_Zero = 0;
            DOM_Z_HPositive = 0;
        } else if (DOM_Z_HPositive > DOM_Z_HNegative && DOM_Z_HPositive > DOM_Z_Negative && DOM_Z_HPositive > DOM_Z_Zero && DOM_Z_HPositive > DOM_Z_HPositive) {
            DOM_Z_HNegative = 0;
            DOM_Z_Negative = 0;
            DOM_Z_Zero = 0;
            DOM_Z_Positive = 0;
        }
    }

//    public String calculateWalkRun(double acceleration) {
//        accelerations.add(acceleration);
//
//        if (accelerations.size() == 5) {
//            double[] acc = new double[5];
//            for (int i = 0; i < 5; i++) {
//                acc[i] = accelerations.get(i);
//            }
//            Calculator calculator = new Calculator(acc);
//            double variance = calculator.calculateVariance();
//
//            accelerations.clear();
//
//            if (variance < 3.0) {
//                return WALK;
//            } else {
//                return RUN;
//            }
//        }
//
//        return "";
//    }


    public void clearData() {
        DOM_X_HNegative = 0;
        DOM_X_Negative = 0;
        DOM_X_Zero = 0;
        DOM_X_Positive = 0;
        DOM_X_HPositive = 0;

        DOM_Y_HNegative = 0;
        DOM_Y_NEGATIVE = 0;
        DOM_Y_Positive = 0;
        DOM_Y_HPositive = 0;

        DOM_Z_HNegative = 0;
        DOM_Z_Negative = 0;
        DOM_Z_Zero = 0;
        DOM_Z_Positive = 0;
        DOM_Z_HPositive = 0;

        DOM_ACC = 0;

    }

    public double getDOM_X_HNegative() {
        return DOM_X_HNegative;
    }

    public double getDOM_X_Negative() {
        return DOM_X_Negative;
    }

    public double getDOM_X_Zero() {
        return DOM_X_Zero;
    }

    public double getDOM_X_Positive() {
        return DOM_X_Positive;
    }

    public double getDOM_Y_HNegative() {
        return DOM_Y_HNegative;
    }

    public double getDOM_Y_NEGATIVE() {
        return DOM_Y_NEGATIVE;
    }

    public double getDOM_Y_Positive() {
        return DOM_Y_Positive;
    }

    public double getDOM_Y_HPositive() {
        return DOM_Y_HPositive;
    }

    public double getDOM_Z_Negative() {
        return DOM_Z_Negative;
    }

    public double getDOM_Z_Zero() {
        return DOM_Z_Zero;
    }

    public double getDOM_Z_Positive() {
        return DOM_Z_Positive;
    }

    public double getDOM_Z_HPositive() {
        return DOM_Z_HPositive;
    }

    public double getDOM_ACC() {
        return DOM_ACC;
    }
}

