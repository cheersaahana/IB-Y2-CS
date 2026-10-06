import java.util.Arrays;

public class IdentityMatrix {
    private int size;
    private int[][] identity;
    private String identityString;

    public IdentityMatrix(int size) {
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

    public String toString() {
        String identityString = "";
        for (int r = 0; r < identity.length; r++) {
            identityString += Arrays.toString(identity[r]) + "\n";
        }
        return identityString;
    }

    public static void main(String[] args) {
        int size = 3;
        IdentityMatrix test = new IdentityMatrix(size);
        System.out.println(test);
    }
}