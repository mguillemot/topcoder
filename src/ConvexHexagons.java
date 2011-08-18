import java.util.Arrays;

public class ConvexHexagons {
    public static final int MOD = 1000000007;

    private int[] m_cache = new int[500000];

    public int find(int N) {
        int sum = 0;
        for (int i = 3; i <= N; i++) {
            int factor = (N + 1 - i) * (N + 1 - i + 1) / 2;
            sum = (sum + factor * t(i)) % MOD;
            //System.out.println(factor+"*T("+i+")");
        }
        return sum % MOD;
    }

    public int t(int n) {
        if (n < 2) {
            return 0;
        } else if (n == 3) {
            return 1;
        }
        if (m_cache[n] == 0) {
            System.out.println("computing t(" + n + ")");
            int sum = 0;
            for (int i = 1; i <= n - 2; i++) {
                System.out.println("i=" + i);
                int sum2 = 0;
                /*for (int j = i + 1; j <= n - i; j++) {
                    sum2 = (sum2 + j - 1) % MOD;
                }*/
                if (i <= (n - 1) / 2) {
                    sum2 = (sum2 + (n - i - 1) * (n - i) / 2 - i * (i - 1) / 2) % MOD;
                }
                /*for (int j = n - i + 1; j <= n - 1; j++) {
                    sum2 = (sum2 + n - i - 1) % MOD;
                }*/
                sum2 = (sum2 + (n - i - 1) * (i - 1)) % MOD;
                System.out.println("sum=" + sum2);
            }
            for (int i = 1; i <= n - 2; i++) {
                System.out.println("*i=" + i);
                int sum2 = 0;
                for (int j = i + 1; j <= n - 1; j++) {
                    sum2 = (sum2 + Math.min(n - i, j) - 1) % MOD;
                }
                System.out.println("sum2=" + sum2);
            }
            m_cache[n] = sum;
            //System.out.println("t("+n+")="+sum);
        }
        return m_cache[n];
    }

    public static void main(String[] args) {
        ConvexHexagons t = new ConvexHexagons();
        System.out.println(t.find(7));
    }
}

