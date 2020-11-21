package mathematics;

import util.Logger;

public class Matrix {
	double[][] matrix;
	
	public Matrix() {
		double[][] emptyMatrix = {
				{1, 0, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 1, 0},
				{0, 0, 0, 1}};
		this.matrix = emptyMatrix;
	}

	/***
	 * Get a visual representation of the matrix
	 */
	public void printMatrix() {
		for(int i=0;i<4;i++){
			System.out.print("|");
			for(int j=0;j<4;j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("|\n");
		}
	}

	/***
	 * Getters and setters
	 *
	 */
	
	public Matrix(double[][] matrix) {
		this.matrix = matrix;
	}

	public double[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}

	public Matrix change(int x, int y, double value){
		if(x<matrix.length && y<matrix[0].length)
			matrix[x][y] = value;
		return this;
	}

	
	/***
	 * Matrices can only be added together if they have the same dimensions
	 * @param b
	 * @return
	 */
	public Matrix addition(Matrix b) {
		new Logger(this.getClass().getName(), "addition", "");
		if(matrix.length != b.getMatrix().length || matrix[0].length != b.getMatrix()[0].length) {
			new Logger(this.getClass().getName(), "addition", "Dimensions do not match.");
		}

		double[][] result = new double[4][4];
		for(int i=0;i<4;i++){ 
			for(int j=0;j<4;j++){
				result[i][j] = matrix[i][j] + b.getMatrix()[i][j];
			}
		}
		return new Matrix(result);
	}
	
	/***
	 * Matrices can only be substracted if they have the same dimensions
	 * @param b
	 * @return new matrix
	 */
	public Matrix substract(Matrix b) {
		new Logger(this.getClass().getName(), "substraction", "");
		if(matrix.length != b.getMatrix().length || matrix[0].length != b.getMatrix()[0].length) {
			new Logger(this.getClass().getName(), "substraction", "Dimensions do not match.");
		}
		double[][] result = new double[4][4];
		for(int i=0;i<4;i++){ 
			for(int j=0;j<4;j++){
				result[i][j] = matrix[i][j] - b.getMatrix()[i][j];
			}
		}
		return new Matrix(result);
	}

	/***
	 * Matrices can be multiplied. in this code its "this x b"
	 * @param b
	 * @return
	 */
	public Matrix multiply(Matrix b) {
		new Logger(this.getClass().getName(), "multiplication", "");
		if(matrix.length != b.getMatrix().length || matrix[0].length != b.getMatrix()[0].length) {
			new Logger(this.getClass().getName(), "substraction", "Dimensions do not match.");
		}
		double[][] result = new double[4][4];
		//multiplying and printing multiplication of 2 matrices
		for (int x = 0; x < 4; x++)
		{
			for (int y = 0; y < 4; y++)
			{
				for (int i = 0; i < 4; i++)
				{
					result[x][y] += matrix[i][y] * b.getMatrix()[x][i];
				}
			}
		}

		return new Matrix(result);
	}

	public Vector multiply(Vector vector)
	{
		double[] newVector = {0, 0, 0, 0};
		for (int i = 0; i < 4; i++)
		{
			double sum = 0;
			for (int j = 0; j < 4; j++)
			{
				sum += this.matrix[i][j] * vector.get(j);
			}

			newVector[i] = sum;
		}

		return new Vector(newVector);
	}

	public Matrix getInverse(){
		double determinant=0;
		double A2[][] = {
				{0, 0, 0, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}};
		double [][] temp;

		for(int i=0;i<4;i++) {
			switch (i) {
				case 0:
					for (int j = 0; j < 4; j++) {
						switch (j) {
							case 0:
								temp = new double[][]{
										{matrix[1][1], matrix[1][2], matrix[1][3]},
										{matrix[2][1], matrix[2][2], matrix[2][3]},
										{matrix[3][1], matrix[3][2], matrix[3][3]}};
								A2[i][j] = (temp[0][0] * temp[1][1] * temp[2][2]
										+ temp[0][1] * temp[1][2] * temp[2][0]
										+ temp[0][2] * temp[1][0] * temp[2][1]
										- temp[0][2] * temp[1][1] * temp[2][0]
										- temp[0][1] * temp[1][0] * temp[2][2]
										- temp[0][0] * temp[1][2] * temp[2][1]);
								//System.out.println(i + ", " + j + " -> " + A2[i][j]);
								break;
							case 1:

								temp = new double[][]{
										{matrix[1][0], matrix[1][2], matrix[1][3]},
										{matrix[2][0], matrix[2][2], matrix[2][3]},
										{matrix[3][0], matrix[3][2], matrix[3][3]}};
								A2[i][j] = (temp[0][0] * temp[1][1] * temp[2][2]
										+ temp[0][1] * temp[1][2] * temp[2][0]
										+ temp[0][2] * temp[1][0] * temp[2][1]
										- temp[0][2] * temp[1][1] * temp[2][0]
										- temp[0][1] * temp[1][0] * temp[2][2]
										- temp[0][0] * temp[1][2] * temp[2][1]);
								//System.out.println(i + ", " + j + " -> " + A2[i][j]);

								break;
							case 2:
								temp = new double[][]{
										{matrix[1][0], matrix[1][1], matrix[1][3]},
										{matrix[2][0], matrix[2][1], matrix[2][3]},
										{matrix[3][0], matrix[3][1], matrix[3][3]}};
								A2[i][j] = (temp[0][0] * temp[1][1] * temp[2][2]
										+ temp[0][1] * temp[1][2] * temp[2][0]
										+ temp[0][2] * temp[1][0] * temp[2][1]
										- temp[0][2] * temp[1][1] * temp[2][0]
										- temp[0][1] * temp[1][0] * temp[2][2]
										- temp[0][0] * temp[1][2] * temp[2][1]);
								//System.out.println(i + ", " + j + " -> " + A2[i][j]);

								break;
							case 3:
								temp = new double[][]{
										{matrix[1][0], matrix[1][1], matrix[1][2]},
										{matrix[2][0], matrix[2][1], matrix[2][2]},
										{matrix[3][0], matrix[3][1], matrix[3][2]}};
								A2[i][j] = (temp[0][0] * temp[1][1] * temp[2][2]
										+ temp[0][1] * temp[1][2] * temp[2][0]
										+ temp[0][2] * temp[1][0] * temp[2][1]
										- temp[0][2] * temp[1][1] * temp[2][0]
										- temp[0][1] * temp[1][0] * temp[2][2]
										- temp[0][0] * temp[1][2] * temp[2][1]);
								//System.out.println(i + ", " + j + " -> " + A2[i][j]);

								break;
							default:
								new Logger("", "invert", "out of range");
								break;
						}
					}
					break;
				case 1:
					for (int j = 0; j < 4; j++) {
						switch (j) {
							case 0:
								temp = new double[][]{
										{matrix[0][1], matrix[0][2], matrix[0][3]},
										{matrix[2][1], matrix[2][2], matrix[2][3]},
										{matrix[3][1], matrix[3][2], matrix[3][3]}};
								A2[i][j] = (temp[0][0] * temp[1][1] * temp[2][2]
										+ temp[0][1] * temp[1][2] * temp[2][0]
										+ temp[0][2] * temp[1][0] * temp[2][1]
										- temp[0][2] * temp[1][1] * temp[2][0]
										- temp[0][1] * temp[1][0] * temp[2][2]
										- temp[0][0] * temp[1][2] * temp[2][1]);
								//System.out.println(i + ", " + j + " -> " + A2[i][j]);

								break;
							case 1:
								temp = new double[][]{
										{matrix[0][0], matrix[0][2], matrix[0][3]},
										{matrix[2][0], matrix[2][2], matrix[2][3]},
										{matrix[3][0], matrix[3][2], matrix[3][3]}};
								A2[i][j] = (temp[0][0] * temp[1][1] * temp[2][2]
										+ temp[0][1] * temp[1][2] * temp[2][0]
										+ temp[0][2] * temp[1][0] * temp[2][1]
										- temp[0][2] * temp[1][1] * temp[2][0]
										- temp[0][1] * temp[1][0] * temp[2][2]
										- temp[0][0] * temp[1][2] * temp[2][1]);
								//System.out.println(i + ", " + j + " -> " + A2[i][j]);

								break;
							case 2:
								temp = new double[][]{
										{matrix[0][0], matrix[0][1], matrix[0][3]},
										{matrix[2][0], matrix[2][1], matrix[2][3]},
										{matrix[3][0], matrix[3][1], matrix[3][3]}};
								A2[i][j] = (temp[0][0] * temp[1][1] * temp[2][2]
										+ temp[0][1] * temp[1][2] * temp[2][0]
										+ temp[0][2] * temp[1][0] * temp[2][1]
										- temp[0][2] * temp[1][1] * temp[2][0]
										- temp[0][1] * temp[1][0] * temp[2][2]
										- temp[0][0] * temp[1][2] * temp[2][1]);
								//System.out.println(i + ", " + j + " -> " + A2[i][j]);

								break;
							case 3:
								temp = new double[][]{
										{matrix[0][0], matrix[0][1], matrix[0][2]},
										{matrix[2][0], matrix[2][1], matrix[2][2]},
										{matrix[3][0], matrix[3][1], matrix[3][2]}};
								A2[i][j] = (temp[0][0] * temp[1][1] * temp[2][2]
										+ temp[0][1] * temp[1][2] * temp[2][0]
										+ temp[0][2] * temp[1][0] * temp[2][1]
										- temp[0][2] * temp[1][1] * temp[2][0]
										- temp[0][1] * temp[1][0] * temp[2][2]
										- temp[0][0] * temp[1][2] * temp[2][1]);
								//System.out.println(i + ", " + j + " -> " + A2[i][j]);
								break;
							default:
								new Logger("", "invert", "out of range");
								break;
						}
					}
					break;
				case 2:
					for (int j = 0; j < 4; j++) {
						switch (j) {
							case 0:
								temp = new double[][]{
										{matrix[0][1], matrix[0][2], matrix[0][3]},
										{matrix[1][1], matrix[1][2], matrix[1][3]},
										{matrix[3][1], matrix[3][2], matrix[3][3]}};
								A2[i][j] = (temp[0][0] * temp[1][1] * temp[2][2]
										+ temp[0][1] * temp[1][2] * temp[2][0]
										+ temp[0][2] * temp[1][0] * temp[2][1]
										- temp[0][2] * temp[1][1] * temp[2][0]
										- temp[0][1] * temp[1][0] * temp[2][2]
										- temp[0][0] * temp[1][2] * temp[2][1]);
								//System.out.println(i + ", " + j + " -> " + A2[i][j]);

								break;
							case 1:
								temp = new double[][]{
										{matrix[0][0], matrix[0][2], matrix[0][3]},
										{matrix[1][0], matrix[1][2], matrix[1][3]},
										{matrix[3][0], matrix[3][2], matrix[3][3]}};
								A2[i][j] = (temp[0][0] * temp[1][1] * temp[2][2]
										+ temp[0][1] * temp[1][2] * temp[2][0]
										+ temp[0][2] * temp[1][0] * temp[2][1]
										- temp[0][2] * temp[1][1] * temp[2][0]
										- temp[0][1] * temp[1][0] * temp[2][2]
										- temp[0][0] * temp[1][2] * temp[2][1]);
								//System.out.println(i + ", " + j + " -> " + A2[i][j]);

								break;
							case 2:
								temp = new double[][]{
										{matrix[0][0], matrix[0][1], matrix[0][3]},
										{matrix[1][0], matrix[1][1], matrix[1][3]},
										{matrix[3][0], matrix[3][1], matrix[3][3]}};
								A2[i][j] = (temp[0][0] * temp[1][1] * temp[2][2]
										+ temp[0][1] * temp[1][2] * temp[2][0]
										+ temp[0][2] * temp[1][0] * temp[2][1]
										- temp[0][2] * temp[1][1] * temp[2][0]
										- temp[0][1] * temp[1][0] * temp[2][2]
										- temp[0][0] * temp[1][2] * temp[2][1]);
								//System.out.println(i + ", " + j + " -> " + A2[i][j]);

								break;
							case 3:
								temp = new double[][]{
										{matrix[0][0], matrix[0][1], matrix[0][2]},
										{matrix[1][0], matrix[1][1], matrix[1][2]},
										{matrix[3][0], matrix[3][1], matrix[3][2]}};
								A2[i][j] = (temp[0][0] * temp[1][1] * temp[2][2]
										+ temp[0][1] * temp[1][2] * temp[2][0]
										+ temp[0][2] * temp[1][0] * temp[2][1]
										- temp[0][2] * temp[1][1] * temp[2][0]
										- temp[0][1] * temp[1][0] * temp[2][2]
										- temp[0][0] * temp[1][2] * temp[2][1]);
								//System.out.println(i + ", " + j + " -> " + A2[i][j]);

								break;
							default:
								new Logger("", "invert", "out of range");
								break;
						}
					}
					break;
				case 3:
					for (int j = 0; j < 4; j++) {
						switch (j) {
							case 0:
								temp = new double[][]{
										{matrix[0][1], matrix[0][2], matrix[0][3]},
										{matrix[1][1], matrix[1][2], matrix[1][3]},
										{matrix[2][1], matrix[2][2], matrix[2][3]}};
								A2[i][j] = (temp[0][0] * temp[1][1] * temp[2][2]
										+ temp[0][1] * temp[1][2] * temp[2][0]
										+ temp[0][2] * temp[1][0] * temp[2][1]
										- temp[0][2] * temp[1][1] * temp[2][0]
										- temp[0][1] * temp[1][0] * temp[2][2]
										- temp[0][0] * temp[1][2] * temp[2][1]);
								//System.out.println(i + ", " + j + " -> " + A2[i][j]);

								break;
							case 1:
								temp = new double[][]{
										{matrix[0][0], matrix[0][2], matrix[0][3]},
										{matrix[1][0], matrix[1][2], matrix[1][3]},
										{matrix[2][0], matrix[2][2], matrix[2][3]}};
								A2[i][j] = (temp[0][0] * temp[1][1] * temp[2][2]
										+ temp[0][1] * temp[1][2] * temp[2][0]
										+ temp[0][2] * temp[1][0] * temp[2][1]
										- temp[0][2] * temp[1][1] * temp[2][0]
										- temp[0][1] * temp[1][0] * temp[2][2]
										- temp[0][0] * temp[1][2] * temp[2][1]);
								//System.out.println(i + ", " + j + " -> " + A2[i][j]);

								break;
							case 2:
								temp = new double[][]{
										{matrix[0][0], matrix[0][1], matrix[0][3]},
										{matrix[1][0], matrix[1][1], matrix[1][3]},
										{matrix[2][0], matrix[2][1], matrix[2][3]}};
								A2[i][j] = (temp[0][0] * temp[1][1] * temp[2][2]
										+ temp[0][1] * temp[1][2] * temp[2][0]
										+ temp[0][2] * temp[1][0] * temp[2][1]
										- temp[0][2] * temp[1][1] * temp[2][0]
										- temp[0][1] * temp[1][0] * temp[2][2]
										- temp[0][0] * temp[1][2] * temp[2][1]);
								//System.out.println(										i + ", " + j + " -> " + A2[i][j]);

								break;
							case 3:
								temp = new double[][]{
										{matrix[0][0], matrix[0][1], matrix[0][2]},
										{matrix[1][0], matrix[1][1], matrix[1][2]},
										{matrix[2][0], matrix[2][1], matrix[2][2]}};
								A2[i][j] = (temp[0][0] * temp[1][1] * temp[2][2]
										+ temp[0][1] * temp[1][2] * temp[2][0]
										+ temp[0][2] * temp[1][0] * temp[2][1]
										- temp[0][2] * temp[1][1] * temp[2][0]
										- temp[0][1] * temp[1][0] * temp[2][2]
										- temp[0][0] * temp[1][2] * temp[2][1]);
								//System.out.println(i + ", " + j + " -> " + A2[i][j]);

								break;
							default:
								new Logger("", "invert", "out of range");
								break;
						}
					}
					break;
			}
		}
		double[] A3={0,0,0,0};
		for(int i=0;i<4;i++){
			switch (i){
				case 0:
					temp = new double[][]{
							{matrix[1][1], matrix[1][2], matrix[1][3]},
							{matrix[2][1], matrix[2][2], matrix[2][3]},
							{matrix[3][1], matrix[3][2], matrix[3][3]}};
					A3[i] = matrix[i][0]
							* (temp[0][0] * temp[1][1] * temp[2][2]
							+ temp[0][1] * temp[1][2] * temp[2][0]
							+ temp[0][2] * temp[1][0] * temp[2][1]
							- temp[0][2] * temp[1][1] * temp[2][0]
							- temp[0][1] * temp[1][0] * temp[2][2]
							- temp[0][0] * temp[1][2] * temp[2][1]);
					break;
				case 1:
					temp = new double[][]{
							{matrix[0][1], matrix[0][2], matrix[0][3]},
							{matrix[2][1], matrix[2][2], matrix[2][3]},
							{matrix[3][1], matrix[3][2], matrix[3][3]}};
					A3[i] = matrix[i][0]
							* (temp[0][0] * temp[1][1] * temp[2][2]
							+ temp[0][1] * temp[1][2] * temp[2][0]
							+ temp[0][2] * temp[1][0] * temp[2][1]
							- temp[0][2] * temp[1][1] * temp[2][0]
							- temp[0][1] * temp[1][0] * temp[2][2]
							- temp[0][0] * temp[1][2] * temp[2][1]);
					break;
				case 2:
					temp = new double[][]{
							{matrix[0][1], matrix[0][2], matrix[0][3]},
							{matrix[1][1], matrix[1][2], matrix[1][3]},
							{matrix[3][1], matrix[3][2], matrix[3][3]}};
					A3[i] = matrix[i][0]
							* (temp[0][0] * temp[1][1] * temp[2][2]
							+ temp[0][1] * temp[1][2] * temp[2][0]
							+ temp[0][2] * temp[1][0] * temp[2][1]
							- temp[0][2] * temp[1][1] * temp[2][0]
							- temp[0][1] * temp[1][0] * temp[2][2]
							- temp[0][0] * temp[1][2] * temp[2][1]);
					break;
				case 3:
					temp = new double[][]{
							{matrix[0][1], matrix[0][2], matrix[0][3]},
							{matrix[1][1], matrix[1][2], matrix[1][3]},
							{matrix[2][1], matrix[2][2], matrix[2][3]}};
					A3[i] = matrix[i][0]
							* (temp[0][0] * temp[1][1] * temp[2][2]
							+ temp[0][1] * temp[1][2] * temp[2][0]
							+ temp[0][2] * temp[1][0] * temp[2][1]
							- temp[0][2] * temp[1][1] * temp[2][0]
							- temp[0][1] * temp[1][0] * temp[2][2]
							- temp[0][0] * temp[1][2] * temp[2][1]);
					break;
				default:
					new Logger("", "invert", "out of range");
					break;
			}
		}

		determinant = A3[0]-A3[1]+A3[2]-A3[3];
		//System.out.println(determinant);
		double tempVal = 0;
		double A4[][] = {
				{0, 0, 0, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}};
		for(int i=0;i<4;i++) {
			for (int j = 0; j < 4; j++) {
				A4[i][j]=Math.pow((-1), i+j)*A2[j][i];
			}
		}
		//System.out.println("A2");
		new Matrix(A2).printMatrix();
		//System.out.println("A4");
		new Matrix(A4).printMatrix();
		//System.out.println("A4");

		//new Matrix(A2).printMatrix();
		Matrix inverse = new Matrix(A4).divide(determinant);
		//inverse.printMatrix();
		return inverse;
	}

	private Matrix divide(double determinant) {
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				matrix[i][j] = matrix[i][j]/determinant;
			}
		}
		return this;
	}


}
