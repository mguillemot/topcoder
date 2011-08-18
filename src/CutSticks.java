import java.util.Arrays;

public class CutSticks {
    private class CutStick  implements Comparable<CutStick> {
        public int originalLength;
        public int cuts;
        public double length() {
            return ((double)originalLength)/(cuts+1);
        }
        public int compareTo(CutStick o) {
            double d = o.length() - length();
            if (d > 0) {
                return 1;
            } else if (d < 0) {
                return -1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "[len="+length()+" cuts="+cuts+"]";
        }
    }

    public double maxKth(int[] sticks, int C, int K) {
        int N = sticks.length;
        Arrays.sort(sticks);
        int[] rev = new int[N];
        for (int i = 0; i < N; i++) {
            rev[i] = sticks[N-i-1];
        }
        sticks = rev;
        if (K <= N) {
            return sticks[K-1];
        }
        int cuts = C;
        System.out.println("cuts="+cuts);
        double sum = 0;
        for (int stick : sticks) {
            sum += stick;
        }
        double[] ncuts = new double[N];
        int repcuts = cuts;
        for (int i = 0; i < N; i++) {
            ncuts[i] = K*sticks[i]/sum;
            System.out.println("ncuts["+i+"]="+ncuts[i]);
            repcuts -= Math.floor(ncuts[i]);
        }
        System.out.println("repcuts="+repcuts);
        int[] nncuts = new int[N];
        for (int i = 0; i < N; i++) {
            if (repcuts > 0) {
                nncuts[i] = (int)Math.ceil(ncuts[i]);
                repcuts--;
            } else {
                nncuts[i] = (int)Math.floor(ncuts[i]);
            }
            System.out.println("nncuts["+i+"]="+nncuts[i]);
        }
        CutStick[] cutsticks = new CutStick[N];
        for (int i = 0; i < N; i++) {
            cutsticks[i] = new CutStick();
            cutsticks[i].originalLength = sticks[i];
            cutsticks[i].cuts = nncuts[i];
        }
        Arrays.sort(cutsticks);
        System.out.println(Arrays.asList(cutsticks));

        for (int i = 0; i < N; i++) {
            K -= cutsticks[i].cuts+1;
            System.out.println("i="+i+" : K="+K);
            if (K <= 0) {
                return cutsticks[i].length();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        CutSticks t = new CutSticks();
        double res = t.maxKth(new int[]{
                1000000000,
                1000000000,
                1
        }, 2,5);
        System.out.println(res);
    }
}
