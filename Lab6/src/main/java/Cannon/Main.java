package Cannon;

import mpi.MPI;
import mpi.Status;

import static java.lang.Math.sqrt;

public class Main {
    static int[][] A, B, C;
    static int[] a, b, c, tempA, tempB;
    static int locN, locN2, process, sqrtNum;
    static int myRank, myRow, myCol;
    static Status status;
    static final int MATRIX_SIZE = 1000;
    public static void main(String[] args) {
        long start = 0;
        MPI.Init(args);
        if(myRank == 0) {
            start = System.currentTimeMillis();
        }
        process = MPI.COMM_WORLD.Size();
        myRank = MPI.COMM_WORLD.Rank();

        sqrtNum = (int) sqrt(process);
        if (sqrtNum * sqrtNum != process) {
            if (myRank == 0) {
                System.out.println("Number of processors is not a quadratic number!\n");
            }
            MPI.Finalize();
            return;
        }
        int i;

        locN  = MATRIX_SIZE / sqrtNum;
        locN2 = locN * locN;
        myCol =  myRank % sqrtNum ;
        myRow = (myRank-myCol) / sqrtNum ;

        a = new int[locN2];
        b = new int[locN2];
        c = new int[locN2];

        for(i = 0; i < locN2 ; ++i) {
            c[i] = 0;
        }

        tempA = new int[locN2];
        tempB = new int[locN2];

        if (myRank == 0) {
            A = new int[MATRIX_SIZE][MATRIX_SIZE];
            B = new int[MATRIX_SIZE][MATRIX_SIZE];
            C = new int[MATRIX_SIZE][MATRIX_SIZE];
            getRandomArraysAB();
            scatterAB();
        } else {
            status = MPI.COMM_WORLD.Recv(a,0, locN2, MPI.INT, 0 , 1);
            status = MPI.COMM_WORLD.Recv(b, 0,locN2, MPI.INT, 0 , 2);
        }

        initAlignment();
        blockShift();

        if(myRank == 0) {
            gatherResult();
        } else {
            MPI.COMM_WORLD.Send(c,0,locN2,MPI.INT,0,1);
        }

        MPI.COMM_WORLD.Barrier();
        if(myRank == 0) {
            long stop = System.currentTimeMillis();
            System.out.println("Time: " + (stop - start) + "ms");
        }

        MPI.Finalize();
    }

    static int getIndex(int row, int col, int sqrtNum) {
        return ((row+sqrtNum)%sqrtNum)*sqrtNum + (col+sqrtNum)%sqrtNum;
    }

    static void getRandomArraysAB() {
        for(int i = 0; i< MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                A[i][j] = (int) (Math.random() * 10);
                B[i][j] = (int) (Math.random() * 10);
                C[i][j] = 0;
            }
        }
    }


    static void scatterAB() {
        int i, j, k, l;
        int piMin, piMax, pjMin, pjMax;
        for(k=0; k<process; k++) {
            pjMin = (k % sqrtNum) * locN;
            pjMax = (k % sqrtNum + 1) * locN-1;
            piMin = (k - (k % sqrtNum)) /sqrtNum * locN;
            piMax = ((k - (k % sqrtNum)) / sqrtNum +1) * locN - 1;
            l = 0;

            for(i = piMin; i <= piMax; ++i) {
                for(j=pjMin; j<=pjMax; ++j) {
                    tempA[l] = A[i][j];
                    tempB[l] = B[i][j];
                    ++l;
                }
            }

            if(k == 0) {
                System.arraycopy(tempA, 0, a, 0, locN2);
                System.arraycopy(tempB, 0, b, 0, locN2);
            } else {
                MPI.COMM_WORLD.Send(tempA, 0, locN2, MPI.INT, k, 1);
                MPI.COMM_WORLD.Send(tempB,0, locN2, MPI.INT, k, 2);
            }
        }
    }

    static void initAlignment() {
        status = MPI.COMM_WORLD.Sendrecv(a, 0,locN2, MPI.INT, getIndex(myRow,myCol-myRow,sqrtNum), 1,
                tempA, 0, locN2, MPI.INT, getIndex(myRow,myCol+myRow,sqrtNum), 1);
        System.arraycopy(tempA, 0, a, 0, locN2);

        status = MPI.COMM_WORLD.Sendrecv(b, 0, locN2, MPI.INT, getIndex(myRow-myCol,myCol,sqrtNum), 1,
                tempB, 0, locN2, MPI.INT, getIndex(myRow+myCol,myCol,sqrtNum), 1);
        System.arraycopy(tempB,0,b,0,locN2);

    }

    static void blockShift() {
        int i, j, k, l;
        for(l = 0; l < sqrtNum; ++l) {

            for(i = 0; i < locN; ++i)
                for(j = 0; j < locN; ++j)
                    for(k = 0; k < locN; ++k)
                        c[i * locN + j] += a[i * locN + k] * b[k * locN + j];
            MPI.COMM_WORLD.Sendrecv_replace(a, 0, locN2, MPI.INT, getIndex(myRow, myCol-1, sqrtNum),
                    1,getIndex(myRow, myCol+1, sqrtNum),1);
            MPI.COMM_WORLD.Sendrecv_replace(b, 0, locN2, MPI.INT, getIndex(myRow-1, myCol, sqrtNum),
                    1,getIndex(myRow+1, myCol, sqrtNum),1);

        }
    }

    static void gatherResult() {
        int i2, j2;
        int piMin, piMax, pjMin, pjMax;

        for (int i = 0; i < locN; i++) {
            System.arraycopy(c, i * locN, C[i], 0, locN);
        }

        for (int k = 1; k < process; k++) {
            status = MPI.COMM_WORLD.Recv(c, 0,locN2, MPI.INT, k, 1);
            pjMin = (k % sqrtNum) * locN;
            pjMax = (k % sqrtNum + 1) * locN - 1;
            piMin =  (k - (k % sqrtNum))/sqrtNum     * locN;
            piMax = ((k - (k % sqrtNum))/sqrtNum +1) * locN -1;

            i2 = 0;

            for(int i = piMin; i <= piMax; ++i) {
                j2 = 0;
                for(int j = pjMin; j <= pjMax; ++j) {
                    C[i][j] = c[i2 * locN + j2];
                    ++j2;
                }
                ++i2;
            }
        }
    }

}

