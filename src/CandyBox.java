public class CandyBox {
    private double[] m_values;
    private int N;

    public double[] expectedScore(int C, int[] score, int S) {
        m_values = new double[score.length];
        N = score.length;
        for (int i = 0; i < score.length; i++) {
            m_values[i] = score[i];
        }
        double ncm1 = N * C - 1;

        for (int s = 0; s < S; s++) {
            double[] v = new double[N];
            double coeff = (C - 1) / ncm1;
            for (int i = 0; i < N; i++) {
                v[i] += coeff * m_values[i];
            }
            System.out.println("same v="+format(v));
            for (int src = 0; src < N; src++) {
                for (int dst = src+1; dst < N; dst++) {
                    System.out.println("src="+src+" dst="+dst);
                    for (int i = 0; i < N; i++) {
                        double coeffd = 2*C / ncm1/N;
                        if (i == src) {
                            v[i] += coeffd * ((C - 1) * m_values[src] / C + m_values[dst] / C);
                        } else if (i == dst) {
                            v[i] += coeffd * ((C - 1) * m_values[dst] / C + m_values[src] / C);
                        } else {
                            v[i] += coeffd * m_values[i];
                        }
                    }
                    System.out.println("v="+format(v));
                }
            }
            m_values = v;
        }

        return m_values;
    }

    private static String format(double[] v) {
        String r = "[";
        for (double v1 : v) {
            r += v1 + ",";
        }
        r += "]";
        return r;
    }

    public static void main(String[] args) {
        CandyBox t = new CandyBox();
//        double[] res = t.expectedScore(2, new int[]{1, 10}, 1); // 4,7
        double[] res = t.expectedScore(98, new int[]{13, 82, 74, 78, 12, 71, 81, 80, 30}, 154); // 4,7
//        double[] res = t.expectedScore(1, new int[]{1, 4, 10}, 1); // 5,5,5
        System.out.println("res="+format(res));
    }
}
