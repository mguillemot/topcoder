import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SubFibonacci {

    private int[] S;

    private boolean isInFiboSequence(int a, int b, int n) {
        int c = a + b;
        while (c < n) {
            a = b;
            b = c;
            c = a + b;
        }
        return (c == n);
    }

    public int maxElements(int[] S) {
        this.S = S;

        if (S.length <= 4) {
            System.out.println("Tjrs possible pour un set de " + S.length);
            return S.length;
        }

        int maxLenlen = 0;

        for (int i = 0; i < S.length; i++) {
            int a = S[i];
            for (int j = 0; j < S.length; j++) {
                if (i != j) {
                    int b = S[j];
                    boolean[] taken = new boolean[S.length];
                    taken[i] = true;
                    taken[j] = true;
                    int max = Math.max(a, b);
                    int len = 2;
                    for (int k = 0; k < S.length; k++) {
                        int c = S[k];
                        if (k != i && k != j && isInFiboSequence(a, b, c)) {
                            taken[k] = true;
                            if (c > max) {
                                max = c;
                            }
                            len++;
                        }
                    }

                    if (len >= S.length - 2) {
                        System.out.println("a=" + a + " b=" + b + " len=" + len + " => tout le set est OK");
                        return S.length;
                    }

                    // <2nd fibo>
                    for (int ii = 0; ii < S.length; ii++) {
                        int aa = S[ii];
                        if (!taken[ii] && aa >= max) {
                            for (int jj = 0; jj < S.length; jj++) {
                                int lenlen = len + 1;
                                int bb = S[jj];
                                if (ii != jj && !taken[jj] && bb >= max) {
                                    lenlen++;
                                    for (int kk = 0; kk < S.length; kk++) {
                                        int cc = S[kk];
                                        if (kk != ii && kk != jj && !taken[kk] && cc > max && isInFiboSequence(aa, bb, cc)) {
                                            lenlen++;
                                        }
                                    }
                                }
                                if (lenlen > maxLenlen) {
                                    maxLenlen = lenlen;
                                }
                            }
                        }
                    }
                    // </2nd fibo>

                }
            }
        }

        return maxLenlen;
    }

    public static void main(String[] args) {
        SubFibonacci s = new SubFibonacci();
        int[] S;

//        S = new int[]{8, 1, 20, 3, 10};
//        System.out.println(s.maxElements(S));
//
//        S = new int[]{19, 47, 50, 58, 77, 99};
//        System.out.println(s.maxElements(S));
//
//        S = new int[]{512};
//        System.out.println(s.maxElements(S));
//
//        S = new int[]{3, 5, 7, 10, 13, 15, 20, 90};
//        System.out.println(s.maxElements(S));
//
//        S = new int[]{1, 2, 3, 5, 8, 13, 21, 34, 55, 89};
//        System.out.println(s.maxElements(S));

        int I = 39;
        S = new int[I];
        S[0] = 3;
        S[1] = 1;
        for (int i = 2; i < 30; i++)
        {
            S[i] = S[i-2]+S[i-1];
            System.out.print(S[i] + ",");
        }
        S[30] = 1467663;
        S[31] = 1467664;
        for (int i = 32; i < I; i++)
        {
            S[i] = S[i-2]+S[i-1];
            System.out.print(S[i] + ",");
            if (S[i] > 100000000)
            {
                System.out.println("break @" + i);
                break;
            }
        }
        Arrays.
        int max = s.maxElements(S);
        System.out.println("===");
        System.out.println(max);




    }

}
