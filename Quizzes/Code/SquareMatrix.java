
import java.util.Arrays;

public class SquareMatrix {
    private int[] array;
    private int [][] square;
    private int s;

    public SquareMatrix(int[] array) {
        this.array = array;
        s = (int) Math.sqrt(array.length);
        if (s*s != array.length) {
            s++;
        }
        square = new int[s][s];
    }

    public int[][] toMatrix() {
        for (int i = 0; i < array.length ; i++) {
            square[i/s][i%s] = array[i];
        }
        return square;
    }

    public String show(int[][] matrix) {
        String string = "";
        for (int r = 0; r < matrix.length; r++) {
            string += Arrays.toString(matrix[r]) + "\n";
        }
        return string;
    }

    public static void main(String[] args) {
        int[] numbers = { 1, 2, 3, 3, 4, 5, 6, 6, 7, 8 };
        SquareMatrix test = new SquareMatrix(numbers);
        int[][] into_matrix = test.toMatrix();
        System.out.println(test.show(into_matrix));
    }
}
