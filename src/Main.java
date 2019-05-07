public class Main {

    private static double [][] a = {
            {2.0,9.0,7.0,11.0,4.0,5.0,6.0},
            {18.0,84.0,69.0,110.0,41.0,56.0,56.0},
            {8.0,66.0,97.0,164.0,76.0,136.0,49.0},
            {2.0,21.0,58.0,94.0,63.0,74.0,33.0},
            {14.0,63.0,49.0,131.0,86.0,88.0,73.0},
            {4.0,42.0,98.0,240.0,194.0,243.0,117.0},
            {14.0,84.0,127.0,203.0,120.0,165.0,96.0}
    };

    /*P should be:
        {1,2,6,5,3,4,0}

L should be:
{
{1.0,0.0,0.0,0.0,0.0,0.0,0.0},
{0.4444444444444444,1.0,0.0,0.0,0.0,0.0,0.0},
{0.7777777777777778,0.6511627906976745,1.0,0.0,0.0,0.0,0.0},
{0.2222222222222222,0.813953488372093,0.9513888888888888,1.0,0.0,0.0,0.0},
{0.1111111111111111,0.4069767441860465,0.7743055555555556,0.0249424405218726,1.0,0.0,0.0},
{0.7777777777777778,-0.08139534883720913,0.02430555555555528,0.6603990790483498,0.2708533077660565,1.0,0.0},
{0.1111111111111111,-0.011627906976744143,0.0034722222222221513,-3.8372985418267004E-4,0.0038350910834128384,-7.334066739993784E-4,1.0}
};

R should be:
{
{18.0,84.0,69.0,110.0,41.0,56.0,56.0},
{0.0,28.66666666666667,66.33333333333334,115.11111111111111,57.77777777777778,111.11111111111111,24.111111111111114},
{0.0,0.0,30.139534883720927,42.488372093023244,50.48837209302326,49.09302325581395,36.74418604651162},
{0.0,0.0,0.0,81.4375,89.82638888888889,93.4097222222222,49.97222222222223},
{0.0,0.0,0.0,0.0,-6.403683806600151,-17.784727551803535,-12.732540291634685},
{0.0,0.0,0.0,0.0,0.0,-4.575503355704725,0.9609300095876963},
{0.0,0.0,0.0,0.0,0.0,0.0,-7.334066740039538E-4}
};


     */
    //private static double [][] a =  {{2,-1,0},{-1,2,-1},{0,-1,2}};
    private static int[] pi = new int[a.length];
    private static double[][] l = new double [a.length][a.length];
    private static double[][] r = new double [a.length][a.length];

    public static void main(String[] args) {
	    lrFactorization(pi, a, l , r);
	    System.out.print("Perm: ");
	    printVector(pi);

        System.out.print("A: ");printMatrix(a);
        System.out.print("L: ");printMatrix(l);
        System.out.print("R: ");printMatrix(r);
    }



    public static void lrFactorization(int[] pi, double[][] a, double[][] l, double[][] r) {
        //Pivottisierung dann normal LR
        int j = 0;
        for (int i = 0; i < pi.length; i++) {
            pi[i] = i;
            l[i][i] = 1;
        }

        double max = Math.abs(a[0][0]);
        int zeile1 = 0;
        int zeile2 = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (j = i + 1; j < a.length; j++) {

                if (Math.abs(a[j][i]) > max) {
                    max = Math.abs(a[j][i]);
                    zeile2 = j;

                }
            }
            tauscheZeile(zeile1, zeile2);
            zeile1++;
            System.out.println("nach Tausch");
            printMatrix(a);



            System.out.println("i: " + i + " und j:" + j);
            l[j][i] = a[j][i] / a[i][i];

            for (int m = i + 1; m < a[0].length; m++) {
                a[j][m] = a[j][m] - (l[j][i] * a[i][m]);
            }

        }

        for (int i = 0; i < a[0].length; i++) {
            for (int k = 0; k < i + 1; k++) {
                r[k][i] = a[k][i];
            }
        }

        //lrFactorization(a,l,r);

    }

    public static void lrFactorization(double[][] a, double[][] l, double[][] r) {
        for (int i = 0; i < a.length; i++) {
            l[i][i] = 1;
        }

        for (int i = 0; i < a[0].length - 1; i++) {

            for (int j = i + 1; j < a[0].length; j++) {

                l[j][i] = a[j][i] / a[i][i];

                for (int m = i + 1; m < a[0].length; m++) {
                    a[j][m] = a[j][m] - (l[j][i] * a[i][m]);
                }
            }

        }

        for (int i = 0; i < a[0].length; i++) {
            for (int j = 0; j < i + 1; j++) {
                r[j][i] = a[j][i];
            }
        }
    }



    public static void tauscheZeile(int zeile1, int zeile2) {
        double[][] temp = new double[a.length][a.length];
        int tempP = 0;
        for(int sp = 0; sp<a.length; sp++) {
            temp[zeile1][sp] = a[zeile1][sp];
            a[zeile1][sp] = a[zeile2][sp];
            a[zeile2][sp] = temp[zeile1][sp];

            tempP = pi[zeile1];
            pi[zeile1] = pi[zeile2];
            pi[zeile2] = tempP;
        }

    }

    public static void choleskyFactorization(double[][] a, double[][] l){
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a.length; j++) {
                l[i][j] = 0;
            }
        }

        for(int k = 0; k < a.length; k++) {
            l[k][k] = Math.sqrt(a[k][k]);

            for(int i = k+1; i < a.length; i++){
                l[i][k] = a[i][k]/l[k][k];


                for(int j = k+1; j <= i; j++) {
                    a[i][j] = a[i][j] - l[i][k] * l[j][k];
                }
            }
        }
    }


    public static void printVector(int [] pVector) {
        for(int j = 0; j < pVector.length; j++) {
            System.out.println(pVector[j] + " ");
        }
    }

    public static void printMatrix(double [][] pMatrix) {
        for(int i = 0; i < pMatrix.length; i++) {
            for(int j = 0; j < pMatrix.length; j++) {
                System.out.print(pMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
