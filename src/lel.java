/*public class lel {
    public static void lrFactorization(int[] pi, double[][] a, double[][] l, double[][] r) {
        //Pivottisierung dann normal LR
        for(int i = 0; i < pi.length; i++ ) {
            pi[i] = i;
        }
        double max = Math.abs(a[0][0]);
        int zeile1 = 0;
        int zeile2 = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 1; j < a.length; j++) {

                if (Math.abs(a[j][i]) > max) {
                    max = Math.abs(a[j][i]);
                    zeile2 = j;

                }
            }

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

            zeile1++;

        }

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

}

 */
