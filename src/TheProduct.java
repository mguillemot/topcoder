public class TheProduct {
    public long maxProduct(int[] numbers, int k, int maxDist) {
        int n = numbers.length;
        long[][] min = new long[k][n];
        long[][] max = new long[k][n];
        for (int kk = 1; kk <= k; kk++) {
            for (int i = 0; i < n; i++) {
                min[kk-1][i] = Long.MAX_VALUE;
                max[kk-1][i] = Long.MIN_VALUE;
            }
        }
        for (int i = 0; i < n; i++) {
            min[0][i] = numbers[i];
            max[0][i] = numbers[i];
        }
        for (int kk = 2; kk <= k; kk++) {
            for (int i = 0; i < n; i++) {
                long stepMax = Long.MIN_VALUE;
                long stepMin = Long.MAX_VALUE;
                for (int d = 0; d < maxDist; d++) {
                    int idx = i - d - 1;
                    if (idx >= 0 && max[kk - 2][idx] != Long.MIN_VALUE) {
                        long maxVal = (numbers[i] >= 0) ? numbers[i] * max[kk - 2][idx] : numbers[i] * min[kk - 2][idx];
                        if (maxVal > stepMax) {
                            stepMax = maxVal;
                        }
                        long minVal = (numbers[i] >= 0) ? numbers[i] * min[kk - 2][idx] : numbers[i] * max[kk - 2][idx];
                        if (minVal < stepMin) {
                            stepMin = minVal;
                        }
                    }
                }
                min[kk - 1][i] = stepMin;
                max[kk - 1][i] = stepMax;
            }
        }
        long m = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (max[k-1][i] > m) {
                m = max[k-1][i];
            }
        }
        return m;
    }

    public static void main(String[] args) {
        TheProduct t = new TheProduct();
        long res = t.maxProduct(new int[]{-3, -5, -8, -9, -1, -2}, 3, 3);
        System.out.println("res=" + res);
    }
}
