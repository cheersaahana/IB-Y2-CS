import java.util.Arrays;

public class Matrix {
    private int size;
    private int[][] identity;
    private String identityString;

    public Matrix(int size) {
        this.size = size;
        identity = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int s = 0; s < size; s++) {
                identity[i][s] = 0;
                if (i == s) {
                    identity[i][s] = 1;
                }
            }
        }
    }

    public String show() {
        identityString = Arrays.deepToString(identity);
        return identityString;
    }

    public static void main(String[] args) {
        int size = 3;
        Matrix test = new Matrix(size);
        int[][] identity = test.identity;
    }
}