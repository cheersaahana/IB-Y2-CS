class ADCError {
    static double calculateADCError(double sample) {
        double resolution = 5.0/Math.pow(2, 6);
        int digitalValue = (int) (sample/resolution);
        double represented = digitalValue * resolution;
        
        return Math.abs(sample - represented);
    }
    public static void main(String[] args) {
        double analogSample = 2.35;
        double error = calculateADCError(analogSample);
        System.out.println("ADC Error: " + error + " V");
    }
}