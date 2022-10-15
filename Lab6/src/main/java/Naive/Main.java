package Naive;

class Main {
    private static final int MATRIX_SIZE = 1000;

    public static void main(String[] args) {
        Matrix a = new Matrix(MATRIX_SIZE);
        Matrix b = new Matrix(MATRIX_SIZE);
        a.initialise();
        b.initialise();
        long start = System.currentTimeMillis();
        Matrix c = a.multiplyMatrix(b, MATRIX_SIZE);
        long stop = System.currentTimeMillis();
        System.out.println("Time: " + (stop - start) + "ms");
    }
}
