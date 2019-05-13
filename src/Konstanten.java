public class Konstanten {

    public static void forwardSubstitution(double[][] l, double[] y, double[] b) {
        int n = l[0].length;

        y[0] = b[0]/l[0][0];
        for (int i=1 ; i<n ; i++) {
            y[i] = b[i];
            for (int j=0 ; j<i ; j++) {
                y[i] = y[i] - l[i][j]*y[j];
            }
            y[i] = y[i] / l[i][i];
        }
    }

    public static void backwardSubstitution(double[][] r, double[] x, double[] y) {
        int n = r.length;

        x[n-1] = y[n-1]/r[n-1][n-1];
        for (int i=n-2 ; i>=0 ; i--) {
            x[i] = y[i];
            for (int j=i+1; j<n ; j++) {
                x[i] = x[i] - r[i][j]*x[j];
            }
            x[i] = x[i] / r[i][i];
        }
    }

    public static void initPermutation(int[] pi) {
        int n = pi.length;

        for (int i=0 ; i<n ; i++) {
            pi[i] = i;
        }
    }

    public static void transposeMatrix(double[][] a, double[][] at) {
        int n = a.length;

        for (int i=0 ; i<n ; i++) {
            for (int j=0 ; j<n ; j++) {
                at[j][i] = a[i][j];
            }
        }
    }

    public static void copyMatrix(double[][] a, double[][] r) {
        for (int i=0 ; i<a.length ; i++) {
            for (int j=0 ; j<a[i].length ; j++) {
                r[i][j] = a[i][j];
            }
        }
    }

    public static int searchPivot(double[][] a, int j) {
        int    n = a.length;
        double absPivot = Math.abs(a[j][j]);
        int    ipivot = j;

        for (int i=j+1 ; i<n ; i++) {
            if (Math.abs(a[i][j]) > absPivot) {
                ipivot = i;
                absPivot = Math.abs(a[i][j]);
            }
        }
        return ipivot;
    }

    public static void transpose(int[] pi, int j, int i) {
        int tmp;

        System.out.printf("Zeilenvertauschung: %d <--> %d\n", i, j);
        tmp = pi[i];
        pi[i] = pi[j];
        pi[j] = tmp;
    }

    public static void swapRows(double[][] matrix, int j, int i, int from, int to) {
        double tmp;

        for (int k=from ; k<to ; k++) {
            tmp = matrix[j][k];
            matrix[j][k] = matrix[i][k];
            matrix[i][k] = tmp;
        }
    }

    public static void lrFactorization(double[][] a, double[][] l, double[][] r) {
        int n = a.length;

        copyMatrix(a,r);
        a = r;
        for (int j=0 ; j<n ; j++) {
            l[j][j] = 1;
            for (int i=j+1 ; i<n ; i++) {
                l[i][j] = a[i][j] / a[j][j];
                a[i][j] = 0;
                for (int k=j+1 ; k<n ; k++) {
                    a[i][k] = a[i][k] - l[i][j]*a[j][k];
                }
            }
        }
    }

    public static void lrFactorization(int[] pi, double[][] a, double[][] l, double[][] r) {
        int n = a.length;
        int ipivot;

        initPermutation(pi);

        copyMatrix(a,r);
        a = r;
        for (int j=0 ; j<n ; j++) {
            // pivotzeile suche
            ipivot  = searchPivot(a,j);
            if (ipivot != j) {
                // In A die Zeilen j und ipivot tauschen
                // ab Spalte j bis Spalte n (exklusive)
                swapRows(a,j,ipivot,j,n);
                // In l die Zeilen j und ipivot tauschen
                // ab Spalte 0 bis Spalte j (exklusive)
                swapRows(l,j,ipivot,0,j);
                // Tausch in pi merken
                transpose(pi,j,ipivot);
            }
            l[j][j] = 1;
            for (int i=j+1 ; i<n ; i++) {
                l[i][j] = a[i][j] / a[j][j];
                a[i][j] = 0;
                for (int k=j+1 ; k<n ; k++) {
                    a[i][k] = a[i][k] - l[i][j]*a[j][k];
                }
            }
        }
    }

    public static void choleskyFactorization(double[][] a, double[][] l) {
        int    n = a.length;
        double lsum;

        for (int i=0 ; i<n ; i++) {
            for (int j=0 ; j<i ; j++) {
                lsum = 0.0;
                for (int k=0 ; k<j ; k++) {
                    lsum = lsum + l[j][k]*l[i][k];
                }
                l[i][j] = (a[i][j] - lsum)/l[j][j];
            }
            lsum = 0.0;
            for (int k=0 ; k<i ; k++) {
                lsum = lsum + l[i][k]*l[i][k];
            }
            l[i][i] = Math.sqrt(a[i][i] - lsum);
        }
    }

    public static void permute(int[] pi, double[] b, double[] c) {
        int n = b.length;

        for (int j=0 ; j<n ; j++) {
            c[j] = b[pi[j]];
        }
    }


    public static double[] solveLinearEquationSystem(double[][] a, double[] x, double[] b) {
        double[][] l = new double[a.length][a.length];
        double[][] r = new double[a.length][a.length];
        double[] y = new double[a.length];

        lrFactorization(a, l, r);
        forwardSubstitution(l, y, b);
        backwardSubstitution(r, x, y);
        return x;
    }

    public static double[] matrixVectorMultiplication(double[][] matrixA, double[] vector) {
        double[]result  =new double[matrixA.length];
        for(int z= 0; z< matrixA.length; z++) {
            for(int sp = 0; sp < matrixA[0].length; sp++) {
                result[z] += matrixA[z][sp] * vector[sp];

            }
        }
        return result;
    }



    public static double[][] transposeMatrix(double[][] a) {
        double[][] result = new double[a[0].length][a.length];
        for (int i=0 ; i<result.length ; i++) {
            for (int j=0 ; j<result[0].length ; j++) {
                result[i][j] = a[j][i];
            }
        }
        return result;
    }
    public static double[][] matrixmultiplication(double[][] matrixA, double[][] matrixB) {
        if(matrixA.length == matrixB[0].length){
            double[][] resMatrix = new double[matrixA.length][matrixB[0].length];
            for(int i = 0; i < matrixA.length; i++) {
                for (int j = 0; j < matrixB[0].length; j++) {
                    for (int k = 0; k < matrixB.length; k++) {
                        resMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                    }
                }
            }

            return resMatrix;
        }
        System.out.println("Fehler");
        return null;
    }

    public static void printVector(double [] pVector) {
        for(int j = 0; j < pVector.length; j++) {
            System.out.println(pVector[j] + " ");
        }
    }

    public static void printMatrix(double [][] pMatrix) {
        for(int i = 0; i < pMatrix.length; i++) {
            for(int j = 0; j < pMatrix[0].length; j++) {
                System.out.print(pMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
