
/**
 * This class represents a Matrix object.
 * Matrix = 2D array of integers (0 - 255)
 * the numbers represent color in grayscale where 0 is white and 255 is black
 * @version 2020a
 * @author Kfir Sibirsky
 */
public class Matrix {

	private int[][] mat;
	/**
	 * Constructs a Matrix from a two-dimensional array; the dimensions as well as the
	 * values of this Matrix will be the same as the dimensions of the two-dimensional array
	 * @param array the array to copy
	 */
	public Matrix(int[][] array) {
		mat = new int[array.length][array[0].length];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				mat[i][j] = array[i][j];
			}
		}
	}
	/**
	 * Constructs a size1 by size2 Matrix of zeros.
	 * @param size1 represents the number of rows
	 * @param size2 represents the number of columns
	 */
	public Matrix(int size1, int size2) {
		mat = new int[size1][size2];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				mat[i][j] = 0;
			}
		}
	}
	/**
	 * gets a new Matrix represents a smoothed image 
	 * @return a new Matrix represents a smoothed image 
	 */
	public Matrix imageFilterAverage() {
		int[][] edited = new int[mat.length][mat[0].length];
		//calculates the average of the neighbors cell values and adjust accordingly
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				if(i==0) {
					if(j==0) {
						edited[i][j] = (mat[i][j]+mat[i][j+1]+mat[i+1][j]+mat[i+1][j+1])/4;//top left corner
					}
					else if(j==mat[0].length-1) {
						edited[i][j] = (mat[i][j]+mat[i][j-1]+mat[i+1][j]+mat[i+1][j-1])/4;//top right corner
					}
					else if(j>0&&j<mat[0].length-1) {
						edited[i][j] = (mat[i][j]+mat[i][j-1]+mat[i+1][j]+mat[i+1][j-1]+mat[i+1][j+1]+mat[i][j+1])/6;//top
					}
				}
				else if(i==mat.length-1) {
					if(j==0) {
						edited[i][j] = (mat[i][j]+mat[i][j+1]+mat[i-1][j]+mat[i-1][j+1])/4;//bottom left corner
					}
					else if(j==mat[0].length-1) {
						edited[i][j] = (mat[i][j]+mat[i][j-1]+mat[i-1][j]+mat[i-1][j-1])/4;//bottom right corner
					}
					else if(j>0&&j<mat[0].length-1) {
						edited[i][j] = (mat[i][j]+mat[i][j-1]+mat[i][j+1]+mat[i-1][j+1]+mat[i-1][j-1]+mat[i-1][j])/6;//bottom
					}
				}
				else if(j==0) {
					if(i>0&&j<mat.length-1) {
						edited[i][j] = (mat[i][j]+mat[i][j+1]+mat[i+1][j]+mat[i+1][j+1]+mat[i-1][j]+mat[i-1][j+1])/6;//left side
					}
				}
				else if(j==mat[0].length-1) {
					if(i>0&&i<mat.length-1) {
						edited[i][j] = (mat[i][j]+mat[i][j-1]+mat[i+1][j]+mat[i+1][j-1]+mat[i-1][j]+mat[i-1][j-1])/6;//right side
					}
				}
				else {
				edited[i][j] = (mat[i][j]+mat[i][j+1]+mat[i+1][j]+mat[i+1][j+1]+mat[i+1][j-1]+mat[i][j-1]+mat[i-1][j+1]+mat[i-1][j-1]+mat[i-1][j])/9;//top left corner
			}
			}
		}
		return new Matrix(edited);
	}
	/**
	 * gets a new Matrix represents a negative image
	 * each cell is assigned the opposite color in range of 0-255 
	 * @return a new Matrix represents a negative image 
	 */
	public Matrix makeNegative() {
		int[][] negative = new int[mat.length][mat[0].length];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				negative[i][j]= Math.abs(255-mat[i][j]);//calculates the opposite color in range of 2-255
			}
		}
		return new Matrix(negative);
	}
	/**
	 * gets a new Matrix represents a rotated 90 degrees clockwise image
	 * @return a new Matrix represents a rotated 90 degrees clockwise image
	 */
	public Matrix rotateClockwise() {
		int[][] rotated = new int[mat[0].length][mat.length]; 
		//every cell is copied to the compatible cell in the rotated Matrix and adjusted accordingly
		int x = mat.length-1, y = 0;
			for (int i = 0; i < rotated.length; i++) {
				for (int j = 0; j < rotated[0].length; j++) {
					rotated[i][j]= mat[x][y];
					x--;
				}
				x = mat.length-1;
				y++;
			}	
		return new Matrix(rotated);
	}
	/**
	 * gets a new Matrix represents a rotated 90 degrees counter clockwise image
	 * @return a new Matrix represents a rotated 90 degrees counter clockwise image
	 */
	public Matrix rotateCounterClockwise() {
		int[][] rotated = new int[mat[0].length][mat.length]; 
		//every cell is copied to the compatible cell in the rotated Matrix and adjusted accordingly
		int x = 0, y = mat[0].length-1;
			for (int i = 0; i < rotated.length; i++) {
				for (int j = 0; j < rotated[0].length; j++) {
					rotated[i][j]= mat[x][y];
					x++;
				}
				x=0;
				y--;
			}	
		return new Matrix(rotated);
	}

	/**
	 * returns a String that represents this Matrix ( = image)
	 * @Override toString in class java.lang.Object
	 * @return String that represents this Matrix ( = image) in the following format:
	 * X	X	X
	 * X	X	X
	 * X	X	X
	 * for example:
	 * 3	8	72
	 * 4	6	60
	 * 253	2	1
	 */
	public String toString() {
		String str = "";
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				str+=mat[i][j]+"\t";
			}
			str=str.substring(0,str.length()-1);//remove the tab at the end of str
			str+="\n";
		}
		str=str.substring(0,str.length()-1);//remove the next line at the end of str
		return str;
	}
}
