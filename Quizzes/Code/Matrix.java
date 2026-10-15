
import java.util.Arrays;

public class Matrix {
	private int[][] matrix;
	private int[] array;

	public Matrix (int[][] matrix) {
		this.matrix = matrix;
	}

	public Matrix (int[] array) {
		this.array = array;
	}

	public Matrix(int size) {
		matrix = new int[size][size];
	}

	public int[] vectorize() {
		int n = matrix.length;
		int m = matrix[0].length;
		int k = 0;
		int[] vectorized = new int[n * m];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				vectorized[k] = matrix[r][c];
				k++;
			}
		}
		return vectorized;
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

	public int[][] identity() {
		int size = matrix.length;
		int[][] identity = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					identity[i][j] = 1;
				} else {
					identity[i][j] = 0;
				}
			}
		}
		return identity;
	}

	public int[][] toMatrix() {
		int s = (int) Math.sqrt(array.length);
		if (s*s != array.length) {
			s++;
		}
		int[][] square = new int[s][s];
		for (int i = 0; i < array.length ; i++) {
			square[i/s][i%s] = array[i];
		}
		return square;
	}

	public int[][] diagonal(int size) {
		int[][] diagonal = new int[size][size];
		int k = 1;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					diagonal[i][j] = k;
					k++;
				} else {
					diagonal[i][j] = 0;
				}
			}
		}
		return diagonal;
	}

	public String show(int[] x) {
		return Arrays.toString(x);
	}

	public String show(int[][] x) {
		String string = "";
		for (int r = 0; r < x.length; r++) {
			string += Arrays.toString(x[r]) + "\n";
		}
		return string;
	}

	public static void main(String[] args) {
		/*//Quiz 10 - Scaled Matrix:
		int[][] matrix = {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 9}
		};
		int scale = 3;
		Matrix test = new Matrix(matrix);
		int[][] scaled_matrix = test.scale(scale);
		System.out.println(test.show(scaled_matrix));
		

		//Quiz 11 - Identity Matrix
		int size = 3;
		Matrix test2 = new Matrix(size);
		int[][] i_matrix = test2.identity();
		System.out.println(test2.show(i_matrix));
		System.out.println();
		
		
		//Quiz 12 - Vectorized Matrix:
		int[][] numbers = { {1, 2, 3, 3}, {4, 5, 6, 6}, {7, 8, 9, 9} };
		Matrix test3 = new Matrix(numbers);
		int[] vectorized_matrix = test3.vectorize();
		System.out.println(test3.show(vectorized_matrix));
		System.out.println();
		

		//Quiz 13 - Square Matrix:
		int[] numbers2 = {1, 2, 3, 3, 4, 5, 6, 6, 7, 8 };
		Matrix test4 = new Matrix(numbers2);
		int[][] into_matrix = test4.toMatrix();
		System.out.println(test4.show(into_matrix));
		*/

		//Quiz 14 -  Diagonal Matrix:
		int size = 4;
		Matrix test5 = new Matrix(size);
		int[][] into_matrix = test5.diagonal(size);
		System.out.println(test5.show(into_matrix));
	}
}