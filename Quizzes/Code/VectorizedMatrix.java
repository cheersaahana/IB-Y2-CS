
import java.util.Arrays;

public class VectorizedMatrix {
    private int[][] matrix;
    private int[] vectorized;
    private int n;
    private int m;
    
    public VectorizedMatrix (int[][] matrix) {
        this.matrix = matrix;
        this.n = matrix.length;
        this.m = matrix[0].length;
        vectorized = new int[n * m];
    }

    public int[] vectorize() {
        int k = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                vectorized[k] = matrix[r][c];
                k++;
            }
        }

        return vectorized;
    }

    public String show(int[] vectorized) {
        return Arrays.toString(vectorized);
    }

    public static void main(String[] args) {
        int[][] numbers = { {1, 2, 3, 3}, {4, 5, 6, 6}, {7, 8, 9, 9} };
        VectorizedMatrix test = new VectorizedMatrix(numbers);
        int[] vectorized_matrix = test.vectorize();
        System.out.println(test.show(vectorized_matrix));
    }
}