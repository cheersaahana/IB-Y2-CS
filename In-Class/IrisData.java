
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

class IrisData {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("extended-iris.data"));
        //Preprocessing
        int num = 0;
        int badFormat = 0;
        int badType = 0;
        int badDomain = 0;
        int missLabeled = 0;
        int numSet = 0;
        int numVer = 0;
        int numVir = 0;
        int repeated = 0;
        Set<String> seenLines = new HashSet<>();

        //Processing
        List<Double> petalLength = new ArrayList<>();
        List<Double> petalWidth = new ArrayList<>();
        List<Double> sepalLength = new ArrayList<>();
        List<Double> sepalWidth = new ArrayList<>();

        while (file.hasNextLine()) {
            num++;
            String line = file.nextLine().strip();
            String[] split = line.split(",", -1);

            //Duplication Check
            if (!seenLines.add(line)) {
                repeated++;
            }

            //Format Check
            if (split.length != 5) {
                badFormat++;
                continue;
            }

            //Domain, Type, and Label Check
            try {
                double pl = Double.parseDouble(split[0].strip());
                double pw = Double.parseDouble(split[1].strip());
                double sl = Double.parseDouble(split[2].strip());
                double sw = Double.parseDouble(split[3].strip());

                if (pl <= 0 || pw <= 0 || sl <= 0 || sw <= 0) {
                    badDomain++;
                    continue;
                }

                String label = split[4].strip();
                if (label.equalsIgnoreCase("Iris-setosa")) {
                    numSet++;
                } else if (label.equalsIgnoreCase("Iris-versicolor")) {
                    numVer++;
                } else if (label.equalsIgnoreCase("Iris-virginica")) {
                    numVir++;
                } else {
                    missLabeled++;
                    continue;
                }

                petalLength.add(pl);
                petalWidth.add(pw);
                sepalLength.add(sl);
                sepalWidth.add(sw);
            } catch (NumberFormatException oops) {
                badType++;
                continue;
            }
        }


        //Preprocessing Results
        System.out.println("Number of lines: " + num);
        System.out.println("Setosa: " + numSet);
        System.out.println("Versicolor: " + numVer);
        System.out.println("Virginica: " + numVir);
        System.out.println("badFormat: " + badFormat);
        System.out.println("badType: " + badType);
        System.out.println("badDomain: " + badDomain);
        System.out.println("missLabeled: " + missLabeled);
        System.out.println("repeated: " + repeated);
        System.out.println();

        //Processing Results
        calculateStats(petalLength, "Petal Length");
        calculateStats(petalWidth, "Petal Width");
        calculateStats(sepalLength, "Sepal Length");
        calculateStats(sepalWidth, "Sepal Width");
    }

    public static double medianOf(List<Double> list, int start, int end) {
        int l = end - start;
        int mid = (start + end) / 2;

        if (l <= 0) {
            return Double.NaN;
        }

        if (l % 2 == 0) {
            return (list.get(mid - 1) + list.get(mid)) / 2.0;
        }

        return list.get(mid);
    }

    public static void calculateStats(List<Double> values, String name) {
        if (values.isEmpty()) {
            System.out.println("No data found for: " + name);
            return;
        }

        List<Double> sorted = values;
        Collections.sort(sorted);

        int n = sorted.size();

        double sum = 0;
        for (int i = 0; i < n; i++) {
            double value = sorted.get(i);
            sum += value;
        }

        double mean = sum/n;

        double variance = 0;
        for (int i = 0; i < n; i++) {
            double value = sorted.get(i);
            variance += Math.pow(value - mean, 2);
        }
        variance /= n;

        double median = medianOf(sorted, 0, n);

        int mid = n / 2;
        double q1 = medianOf(sorted, 0, mid);
        double q3 = medianOf(sorted, (n+1)/2, n);
        double iqr = q3 - q1;

        double min = sorted.get(0);
        double max = sorted.get(n-1);

        List<Double> outliers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (sorted.get(i) < (q1 - 1.5*iqr) || sorted.get(i) > (q3 + 1.5*iqr)) {
                outliers.add(sorted.get(i));
            } 
        }

        System.out.println("Calculations for " + name);
        System.out.println("Mean: " + mean);
        System.out.println("Variance (σ²): " + variance);
        System.out.println("Median: " + median);
        System.out.println("Q1: " + q1);
        System.out.println("Q3: " + q3);
        System.out.println("IQR: " + iqr);
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.println("Outliers: " + outliers);
        System.out.println();
    }
}