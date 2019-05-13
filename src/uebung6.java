public class uebung6 {
    private static double[] x1 = { 0.35, 4.84, 9.51, 7.54, 2.05, 4.27, 6.49, 2.36, 8.55, 6.44 };
    private static double[] x2 = { 5.81, 4.58, 7.68, 7.52, 0.88, 3.87, 3.30, 1.54, 6.45, 3.98 };
    private static double[] yy = {  98.9, 1134.5, 20916.8,  7489.1,   37.6,
            663.7, 4666.0,    56.6, 13605.3, 4339.3 };

    public static void main(String[] args) {
        aufgabe61();
    }

    private static void aufgabe61() {
        //matritzen erstellen
        double mA [][] = new double[x1.length][2];
        double mAT [][] = new double[2][x1.length];
        double mATA [][] = new double[2][2];
        double mATy [] = new double[2];
        double x0[] = new double[2];
        //double
        //matritzen füllen
        for(int z = 0; z < mA.length; z++) {

            for(int sp = 0; sp < mA[0].length; sp++) {
                mA[z][sp]= func(x1[z],x2[z],(sp+1));
            }
        }
        System.out.println("Matrix A: ");
        Konstanten.printMatrix(mA);
        mAT = Konstanten.transposeMatrix(mA);
        //umrechnen
        mATA = Konstanten.matrixmultiplication(mAT,mA);
        mATy = Konstanten.matrixVectorMultiplication(mAT, yy);
        System.out.println("rechte Seite: ");
        Konstanten.printMatrix(mATA);
        System.out.println("linke Seite");
        Konstanten.printVector(mATy);
        //ausrechnen
        Konstanten.solveLinearEquationSystem(mATA, x0, mATy);
        System.out.println("Ergebnisse: ");
        Konstanten.printVector(x0);
    }

    public static double func(double x1,double x2, int zOrSp) {
        if(zOrSp == 1) {
            return Math.pow(x2-Math.pow(x1,2),2);
        } else if(zOrSp == 2) {
            return Math.pow((1-x1),2);
        }
        return -1;
    }
}
