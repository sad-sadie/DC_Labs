package Fox;

import mpi.MPI;

public class Main {
    private final static int MATRIX_SIZE = 1000;

    private static void unpackMatrix(int[] buff, Matrix a, int size) {
        int k = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                a.set(i, j, buff[k]);
                k++;
            }
        }
    }

    private static void multiply(Grid grid, Matrix a, Matrix b, Matrix c) {
        int blockSize = Main.MATRIX_SIZE / grid.dimensions;
        Matrix tempA = new Matrix(blockSize);
        int[] buff;

        for (int stage = 0; stage < grid.dimensions; stage++) {
            int root = (grid.row + stage) % grid.dimensions;
            if (root == grid.col) {
                buff = a.packMatrix(blockSize);
                grid.rowComm.Bcast(buff, 0, blockSize * blockSize, MPI.INT, root);
                unpackMatrix(buff, a, blockSize);
                c.incrementMatrix(a.multiplyMatrix(b, blockSize));
            } else {
                buff = tempA.packMatrix(blockSize);
                grid.rowComm.Bcast(buff, 0, blockSize * blockSize, MPI.INT, root);
                unpackMatrix(buff, tempA, blockSize);
                c.incrementMatrix(tempA.multiplyMatrix(b, blockSize));
            }
            buff = b.packMatrix(blockSize);
            unpackMatrix(buff, b, blockSize);
        }
    }


    public static void main(String[] args) {
        int blockSize;
        MPI.Init(args);
        long start = 0;
        Grid grid = new Grid();
        if (grid.worldRank == 0) {
            System.out.println("===================================================");
            System.out.println("Grid setup finished, total number of processors: " + grid.processCount);
        }
        grid.gridComm.Barrier();
        Matrix a = new Matrix(MATRIX_SIZE);
        Matrix b = new Matrix(MATRIX_SIZE);
        if (grid.worldRank == 0) {
            a.initialise();
            b.initialise();
            start = System.currentTimeMillis();
        }
        grid.gridComm.Barrier();
        MPI.COMM_WORLD.Bcast(a.getPackedValues(), 0, MATRIX_SIZE * MATRIX_SIZE, MPI.INT, 0);
        MPI.COMM_WORLD.Bcast(b.getPackedValues(), 0, MATRIX_SIZE * MATRIX_SIZE, MPI.INT, 0);
        grid.gridComm.Barrier();
        blockSize = MATRIX_SIZE / grid.dimensions;
        int baseRow = grid.row * blockSize;
        int baseCol = grid.col * blockSize;

        Matrix localA = new Matrix(blockSize);
        Matrix localB = new Matrix(blockSize);
        Matrix localC = new Matrix(blockSize);

        for (int i = baseRow; i < baseRow + blockSize; i++) {
            for (int j = baseCol; j < baseCol + blockSize; j++) {
                localA.set(i - baseRow, j - baseCol, a.get(i, j));
                localB.set(i - baseRow, j - baseCol, b.get(i, j));
            }
        }

        multiply(grid, localA, localB, localC);

        int[] resultBuff = new int[MATRIX_SIZE * MATRIX_SIZE];
        int[] localBuff = localC.packMatrix(blockSize);

        grid.gridComm.Gather(localBuff, 0, blockSize * blockSize, MPI.INT, resultBuff, 0, blockSize * blockSize, MPI.INT, 0);
        grid.gridComm.Barrier();
        if (grid.worldRank == 0) {
            Matrix c = new Matrix(MATRIX_SIZE);
            int k = 0;
            for (int bi = 0; bi < grid.dimensions; bi++) {
                for (int bj = 0; bj < grid.dimensions; bj++) {
                    for (int i = bi * blockSize; i < bi * blockSize + blockSize; i++) {
                        for (int j = bj * blockSize; j < bj * blockSize + blockSize; j++) {
                            c.set(i, j, resultBuff[k]);
                            k++;
                        }
                    }
                }
            }

            long stop = System.currentTimeMillis();
            System.out.println((stop - start) + "ms");
        }
        MPI.Finalize();
    }
}
