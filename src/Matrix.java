import java.util.function.Function;

public class Matrix {
    private int rows;
    private int cols;
    private double[][] data;

    /**
     * Creates a new rows-by-cols matrix initialized to 0.
     */
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows][cols];
    }

    /**
     * Copy constructor: creates a deep copy of the given matrix.
     */
    public Matrix(Matrix other) {
        this(other.rows, other.cols);
        for (int i = 0; i < rows; i++) {
            System.arraycopy(other.data[i], 0, this.data[i], 0, cols);
        }
    }

    /**
     * Sets the element at row r and column c to value v.
     */
    public void set(int r, int c, double v) {
        data[r][c] = v;
    }

    /**
     * Returns the element at row r and column c.
     */
    public double get(int r, int c) {
        return data[r][c];
    }

    /**
     * Returns the number of rows.
     */
    public int get_rows() {
        return rows;
    }

    /**
     * Returns the number of columns.
     */
    public int get_cols() {
        return cols;
    }

    /**
     * Prints the matrix to standard output.
     */
    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Adds this matrix to another matrix.
     * @param B The matrix to add.
     * @return A new Matrix representing the sum.
     */
    public Matrix matrixAdd(Matrix B) {
        if (rows != B.rows || cols != B.cols) {
            throw new IllegalArgumentException("Matrix dimensions must agree for addition.");
        }
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = this.data[i][j] + B.data[i][j];
            }
        }
        return result;
    }

    /**
     * Subtracts another matrix from this matrix.
     * @param B The matrix to subtract.
     * @return A new Matrix representing the difference.
     */
    public Matrix matrixSub(Matrix B) {
        if (rows != B.rows || cols != B.cols) {
            throw new IllegalArgumentException("Matrix dimensions must agree for subtraction.");
        }
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = this.data[i][j] - B.data[i][j];
            }
        }
        return result;
    }

    /**
     * Multiplies this matrix with another matrix.
     * @param B The matrix to multiply with.
     * @return A new Matrix representing the product.
     */
    public Matrix matrixMult(Matrix B) {
        if (this.cols != B.rows) {
            throw new IllegalArgumentException("Matrix inner dimensions must agree for multiplication.");
        }
        Matrix result = new Matrix(this.rows, B.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < B.cols; j++) {
                double sum = 0;
                for (int k = 0; k < this.cols; k++) {
                    sum += this.data[i][k] * B.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }
        return result;
    }

    /**
     * Returns the transpose of this matrix.
     * @return A new Matrix representing the transpose.
     */
    public Matrix matrixTranspose() {
        Matrix result = new Matrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[j][i] = this.data[i][j];
            }
        }
        return result;
    }

    /**
     * Returns the element-wise (Hadamard) product of this matrix and another matrix.
     * @param B The matrix to perform the Schur product with.
     * @return A new Matrix representing the Schur product.
     */
    public Matrix schurProduct(Matrix B) {
        if (rows != B.rows || cols != B.cols) {
            throw new IllegalArgumentException("Matrix dimensions must agree for Schur product.");
        }
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = this.data[i][j] * B.data[i][j];
            }
        }
        return result;
    }

    /**
     * Applies a function to each element of the matrix.
     * @param fn A function that takes a Double and returns a Double.
     * @return A new Matrix with the function applied to each element.
     */
    public Matrix applyFunc(Function<Double, Double> fn) {
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = fn.apply(this.data[i][j]);
            }
        }
        return result;
    }
}


