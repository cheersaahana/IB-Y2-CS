class ADCResolution {
    static int calculateResolution(double min, double max, double[] readings) {
        double smallestDifference = Double.MAX_VALUE;
        for (int i = 0; i < readings.length; i++) {
            for (int j = i + 1; j < readings.length; j++) {
                double difference = Math.abs(readings[i] - readings[j]);
                if (difference > 0 && difference < smallestDifference) {
                    smallestDifference = difference;
                }
            }
        }
        double voltageRange = max - min;
        int bits = (int) Math.ceil(Math.log(voltageRange / smallestDifference) / Math.log(2));
        return bits;
    }

    public static void main(String[] args) {
        double[] readings = {2.0, 1.5, 0.0, 2.02, 4.0, 5.0};
        int result = calculateResolution(0.0, 5.0, readings);
        System.out.println("Required ADC resolution: " + result + " bits");
    }
}