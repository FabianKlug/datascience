public class uebung5 {

   /* private static double[] x1 = { 0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0 };
    private static double[] y1 = { 5.1, 7.8, 7.1, 7.4, 9.4, 8.4, 8.9, 6.2, 4.7, 3.3, 0.6 };

    private static double[] size = {
            1.55, 1.57, 1.62, 1.68, 1.75, 1.76, 1.81, 1.83,
            1.87, 1.89, 1.90, 1.92, 1.95, 1.96, 1.99, 2.02
    };

    private static double[] weight = {
            51,   50,   55,   52,   60,   68,   78,   91,
            84,   81,   90,  105,   95,   99,  100,  101
    };

    // Daten von Aufgabenblatt 5, Aufgabe 3
    private static double[] x3 = { 1, 2, 4, 6,  8, 10,  12,  14 };
    private static double[] y3 = { 1, 1, 3, 8, 21, 55, 144, 377 };



    public static void main(String[] args) {

    }



    public static void aufgabe3() {
        double[][] myA = new double[x3.length][2];
        double[][]myAT = new double[2][x3.length];

        double[][] myMul= new double[x3.length][x3.length];
        double[] myR;
        double[] xres = new double[2];

        printMatrix(myA);
        myA = fillMatrix3(myA);
        System.out.println("A normal");
        printMatrix(myA);
        System.out.println("A transponiert");
        myAT = transposeMatrix(myA);
        printMatrix(myAT);
        myMul = matrixmultiplication(myAT, myA);
        System.out.println("Ergebnis multiplikation");
        printMatrix(myMul);

        // rechte Seite
        System.out.println("rechte Seite: ");
        myR = matrixVectorMultiplication(myAT, y1);
        printVector(myR);

        solveLinearEquationSystem(myMul, xres, myR );
        printVector(xres);
    }


    public static void aufgabe1() {
        double [][] myA = new double[x1.length][3];
        double [][] myAT = new double[3][x1.length];
        double [][] myMul = new double[x1.length][x1.length];
        double [] myR;
        double[] xres = new double [3];

        printMatrix(myA);
        myA = fillMatrix(myA);
        System.out.println("A normal");
        printMatrix(myA);
        System.out.println("A transponiert");
        myAT = transposeMatrix(myA);
        printMatrix(myAT);
        myMul = matrixmultiplication(myAT, myA);
        System.out.println("Ergebnis multiplikation");
        printMatrix(myMul);

        // rechte Seite
        System.out.println("rechte Seite: ");
        myR = matrixVectorMultiplication(myAT, y1);
        printVector(myR);

        solveLinearEquationSystem(myMul, xres, myR );
        printVector(xres);
    }



    private static double[][] fillMatrix3(double[][] a){
        double[][] result = new double[a.length][a[0].length];
        for (int i=0 ; i<a[0].length ; i++) {
            for (int j=0 ; j<a.length ; j++) {
                //System.out.println("Emde");
                //result[j][i] = f3(x3[j], (i+1));
                if(i == 0) {
                    result[j][i] = Math.log(y3[j]);
                } else {
                    result[j][i] = x3[j];
                }
            }
            //System.out.println("Emde");
        }
        System.out.println("Emde");
        return result;
    }

    private static double f3(double v, int i) {
        if(i == 1) {
            return Math.log(v);
        } else if ( i== 2) {
            return v;
        }
        return -1;
    }



    private static double f(double x, int z) {
        if(z == 1) {
            return f1(x);
        } else if (z==2) {
            return f2(x);
        } else if(z==3) {
            return f3(x);
        }
        return -1;
    }

    private static  double f3(double x) {
        return 1;
    }

    private static double f2(double x) {
        return x;
    }

    private static double f1(double x) {
        return Math.pow(x,2);
    }

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

    private static void initPermutation(int[] pi) {
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

    private static void copyMatrix(double[][] a, double[][] r) {
        for (int i=0 ; i<a.length ; i++) {
            for (int j=0 ; j<a[i].length ; j++) {
                r[i][j] = a[i][j];
            }
        }
    }

    private static int searchPivot(double[][] a, int j) {
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

    private static void transpose(int[] pi, int j, int i) {
        int tmp;

        System.out.printf("Zeilenvertauschung: %d <--> %d\n", i, j);
        tmp = pi[i];
        pi[i] = pi[j];
        pi[j] = tmp;
    }

    private static void swapRows(double[][] matrix, int j, int i, int from, int to) {
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

    private static void permute(int[] pi, double[] b, double[] c) {
        int n = b.length;

        for (int j=0 ; j<n ; j++) {
            c[j] = b[pi[j]];
        }
    }

    */

}
