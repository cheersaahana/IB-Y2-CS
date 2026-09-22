public class Neuron {
    private double[] inputs;
    private double[] weights;

    public Neuron(double[] inputs, double[] weights) {
        this.inputs = inputs;
        this.weights = weights;
    }

    public double activation(double input) {
        if (input >= 1.0) {
            return 1.0;
        } else if (input <= -1.0) {
            return -1.0;
        } else {
            return input;
        }
    }

    public double output() {
        double output = 0;
        for (int i = 0; i < inputs.length; i++) {
            output += inputs[i] * weights[i];
        }
        return activation(output);
    }
}