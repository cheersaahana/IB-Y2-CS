
import java.util.Random;

class CPO {
    public static void main(String[] args) {
        Random x = new Random();
        double bias = 0.5;
        double w11 = (x.nextDouble() * 4) - 2; //done like this so that we choose numbers between -2 and 2
        double w22 = (x.nextDouble() * 4) - 2;
        double w12 = 1.0;
        double w21 = 1.5;
        double[] weightsN2 = {w22, w12};
        double[] weightsN1 = {w11, w21};

        //idk how to do it...

    }
}