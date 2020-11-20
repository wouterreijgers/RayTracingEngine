package unittests;

import mathematics.Matrix;
import mathematics.Vector;
import org.junit.jupiter.api.Test;
import util.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixTest {

	@Test
	void testMatrixSummation() {
		// Initialize two matrices to add
		double[][] aMatrix = {
				{1, 0, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 1, 0},
				{0, 0, 0, 1}};
		double[][] bMatrix = {
				{3, 0, 3, 0},
				{0, 3, 2, 0},
				{0, 3, 1, 0},
				{0, 2, 0, 1}};
		double[][] expectedResult = {{4, 0, 3, 0}, {0, 4, 2, 0}, {0, 3, 2, 0}, {0, 2, 0, 2}};

		Matrix a = new Matrix(aMatrix);
		Matrix b = new Matrix(bMatrix);
		Matrix result = a.addition(b);
		if(compareMatrices(result, new Matrix(expectedResult)))
			assertEquals(1, 1);
		else
			assertEquals(1, 0);

		double[][] cMatrix = {
				{15, 14, 13, 12},
				{11, 10, 9, 8},
				{7, 6, 5, 4},
				{3, 2, 1, 0}};
		double[][] dMatrix = {
				{0, 1, 2, 3},
				{4, 5, 6, 7},
				{8, 9, 10, 11},
				{12, 13, 14, 15}};
		double[][] expectedResult2 = {
				{15, 15, 15, 15},
				{15, 15, 15, 15},
				{15, 15, 15, 15},
				{15, 15, 15, 15}};
		Matrix c = new Matrix(cMatrix);
		Matrix d = new Matrix(dMatrix);
		Matrix result2 = c.addition(d);
		if(compareMatrices(result2, new Matrix(expectedResult2)))
			assertEquals(1, 1);
		else
			assertEquals(1, 0);
	}
	
	@Test
	void testMatrixSubstraction() {
		// Initialize two matrices to add
		double[][] aMatrix = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
		double[][] bMatrix = {{3, 0, 3, 0}, {0, 3, 2, 0}, {0, 3, 1, 0}, {0, 2, 0, 1}};
		double[][] expectedResult = {{2, 0, 3, 0}, {0, 2, 2, 0}, {0, 3, 0, 0}, {0, 2, 0, 0}};

		Matrix a = new Matrix(aMatrix);
		Matrix b = new Matrix(bMatrix);
		Matrix result = b.substract(a);
		if(compareMatrices(result, new Matrix(expectedResult)))
			assertEquals(1, 1);
		else {
			new Logger(this.getClass().getName(), "testMatrixSubstraction", "Matrices do not match");
			result.printMatrix();
			new Matrix(expectedResult).printMatrix();
			assertEquals(1, 0);
		}
	}

	@Test
	void testVectorMultiplication() {
		// Initialize two matrices to add
		double[] aVector = {7, 4, 12, 2};
		double[][] bMatrix = {
				{5, 6, 7, 7},
				{6, 3, 1, 4},
				{3, 6, 5, 8},
				{4, 7, 6, 9}
		};
		double[] expectedResult = {157, 74, 121, 146};

		Vector a = new Vector(aVector);
		Matrix b = new Matrix(bMatrix);
		Vector result = b.multiply(a);
		assertEquals(true, compareVectors(result, new Vector(expectedResult)));
		if (!compareVectors(result, new Vector(expectedResult)))
			new Logger(this.getClass().getName(), "testMatrixSubstraction", "Matrices do not match");
	}
	
	@Test
	void testMatrixMultiplication() {
		// Initialize two matrices to add
		double[][] aMatrix = {
				{7, 4, 12, 2},
				{4, 8, 6, 3},
				{6, 9, 7, 3},
				{7, 5, 6, 3}};
		double[][] bMatrix = {
				{5, 6, 7, 7},
				{6, 3, 1, 4},
				{3, 6, 5, 8},
				{4, 7, 6, 9}
		};
		double[][] expectedResult = {
				{103, 140, 125, 179},
				{98, 105, 84, 135},
				{117, 126, 104, 161},
				{95, 114, 102, 144}};

		Matrix a = new Matrix(aMatrix);
		Matrix b = new Matrix(bMatrix);
		Matrix result = b.multiply(a);
		assertEquals(true, compareMatrices(result, new Matrix(expectedResult)));
		if(!compareMatrices(result, new Matrix(expectedResult)))
			new Logger(this.getClass().getName(), "testMatrixSubstraction", "Matrices do not match");

		// Another test
		double[][] cMatrix = {
				{6, 2, 0, 2},
				{3, 7, 8, 3},
				{4, 6, 8, 1},
				{9, 4, 7, 3}};
		double[][] dMatrix = {
				{12, 4, 26, 5},
				{7, 3, 5, 4},
				{3, 4, 45, 5},
				{7, 5, 3, 3}};
		double[][] expectedResult2 = {
				{100, 40, 172, 44},
				{130, 80, 482, 92},
				{121, 71, 497, 87},
				{178, 91, 578, 105}};
		Matrix c = new Matrix(cMatrix);
		Matrix d = new Matrix(dMatrix);
		Matrix result2 = d.multiply(c);
		assertEquals(true, compareMatrices(result2, new Matrix(expectedResult2)));
		if(!compareMatrices(result, new Matrix(expectedResult)))
			new Logger(this.getClass().getName(), "testMatrixSubstraction", "Matrices do not match");
	}

	@Test
	void testInverseMatric() {
		// Initialize two matrices to add
		double[][] aMatrix = {
				{12, 4, 26, 5},
				{7, 3, 5, 4},
				{3, 4, 45, 5},
				{7, 5, 3, 3}};


		Matrix a = new Matrix(aMatrix);
		Matrix inverse = a.getInverse();
		Matrix result = a.multiply(inverse);
		result.printMatrix();

	}
	
	
	public Boolean compareMatrices(Matrix a, Matrix b) {
		for(int i=0;i<4;i++){ 
			for(int j=0;j<4;j++){
				if(a.getMatrix()[i][j] != b.getMatrix()[i][j])
					return false;
			}
		}
		return true;
	}

	public Boolean compareVectors(Vector a, Vector b) {
		for(int i=0;i<4;i++){
			if(a.getVector().get(i) != b.getVector().get(i))
				return false;
			}
		return true;
	}

}
