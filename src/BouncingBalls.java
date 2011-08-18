import java.util.Arrays;
import java.util.Collections;

public class BouncingBalls {
    private int N;
    private double[] x;
    private boolean[] dir; // true=>right
    private double T;

    public double expectedBounces(int[] xx, int TT) {
        Arrays.sort(xx);
        N = xx.length;
        x = new double[N];
        dir = new boolean[N];
        int possibilities = (int) Math.pow(2, N);
        //System.out.println(possibilities + " possibilities");
        long collisions = 0;
        for (int initialDirs = 0; initialDirs < possibilities; initialDirs++) {
            //System.out.print("initialDirs="+initialDirs+"   ");
            for (int i = 0; i < N; i++) {
                dir[i] = ((initialDirs >> i) & 1) != 0;
                x[i] = xx[i];
                //System.out.print(dir[i]?"R":"L");
            }
            System.out.println();
            T = TT;

            time: while (T > 0) {
                int colli = -1;
                double mindist = Double.MAX_VALUE;
                for (int i = 0; i < N-1; i++) {
                    if (dir[i] && !dir[i+1]) {
                        double dist = x[i+1]-x[i];
                        if (dist < mindist) {
                            mindist = dist;
                            colli = i;
                        }
                    }
                }
                double Tdiff = mindist/2;
                if (colli != -1 && Tdiff <= T) {
                    //System.out.println("  collisions after " + Tdiff);
                    for (int i = 0; i < N; i++) {
                        if (dir[i]) {
                            x[i] += Tdiff;
                        } else {
                            x[i] -= Tdiff;
                        }
                    }
                    for (int i = 0; i < N-1; i++) {
                        if (x[i] == x[i+1]) {
                            dir[i] = false;
                            dir[i+1] = true;
                            collisions++;
                            //System.out.println("       bim! i="+i);
                        }
                    }
                    T -= Tdiff;
                } else {
                    break time;
                }
            }
        }
        return (double)collisions/possibilities;
    }

    public static void main(String[] args) {
        BouncingBalls t = new BouncingBalls();
        double res = t.expectedBounces(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12},3);
        System.out.println(res);
    }
}
