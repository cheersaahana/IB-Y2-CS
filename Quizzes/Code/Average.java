import java.util.Arrays;

public class Average {
    int[][] nums;
    int rows;
    int cols;
    int[] avgRows;
    int[] avgCols;

    public Average (int[][] nums) {
        this.nums = nums;
        rows = nums.length;
        cols = nums[0].length;

        avgRows = new int[rows];
        for (int i = 0; i < rows; i++) {
            int sum = 0;
            for (int z = 0; z < cols; z++) {
                sum+= nums[i][z];
            }
            avgRows[i] = sum / rows;
        }

        avgCols = new int[cols];
        for (int i = 0; i < rows; i++) {
            int sum = 0;
            for (int z = 0; z < cols; z++) {
                sum += nums[z][i];
            }
            avgCols[i] = sum / cols;
        }
    }

    public String avgCols() {
        return Arrays.toString(avgCols);
    }

    public String avgRows() {
        return Arrays.toString(avgRows);
    }

    public static void main(String[] args) {
        int[][] numbers = {{1,2,3}, {4,5,6}, {7,8,9}};
        Average nums = new Average(numbers);
        System.out.println("Original Array:" + Arrays.deepToString(numbers));
        System.out.println("Row Averages: " + nums.avgRows());
        System.out.println("Column Averages: " + nums.avgCols());
    }
}