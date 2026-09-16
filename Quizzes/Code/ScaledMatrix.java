import java.util.Arrays;

public class ScaledMatrix {
    private int[][] matrix;
    private int scale;

    public ScaledMatrix (int[][] matrix) {
        this.matrix = matrix;
    }

    public int[][] scale (int scale) {
        int size = matrix[0].length;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                matrix[x][y] *= 3;
            }
        }
        return matrix;
    }

    public String show(int[][] matrix) {
        return Arrays.deepToString(matrix);
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int scale = 3;
        ScaledMatrix test = new ScaledMatrix(matrix);
        int[][] scaled_matrix = test.scale(scale);
        System.out.println(test.show(scaled_matrix));
    }
}