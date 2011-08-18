public class AgeEncoding {
    private int m_age;
    private boolean[] m_candles;

    public double getRadix(int age, String candlesLine) {
        m_age = age;
        m_candles = new boolean[candlesLine.length()];
        for (int i = 0; i < candlesLine.length(); i++) {
            m_candles[candlesLine.length() - 1 - i] = (candlesLine.charAt(i) == '1');
        }

        if (age == 1 && m_candles[0]) {
            boolean one = true;
            for (int i = 1; i < m_candles.length; i++) {
                if (m_candles[i]) {
                    one = false;
                    break;
                }
            }
            if (one) {
                return -2;
            }
        }

        double v = newton(age);
        if (!Double.isNaN(v)) {
            return v;
        }

        return -1;
    }

    private double f(double x) {
        double r = -m_age;
        for (int i = 0; i < m_candles.length; i++) {
            if (m_candles[i]) {
                r += Math.pow(x, i);
            }
        }
        return r;
    }

    private double f1(double x) {
        double r = 0;
        for (int i = 1; i < m_candles.length; i++) {
            if (m_candles[i]) {
                r += i*Math.pow(x, i-1);
            }
        }
        return r;
    }

    private double newton(double x) {
        for (int i = 0; i < 1000; i++) {
            double err = Math.abs(f(x));
            //System.out.println("step " + i + " x=" + x + " err="+err);
            if (err < 1e-9) {
                return x;
            }
            x -= f(x)/f1(x);
        }
        return Double.NaN;
    }

    public static void main(String[] args) {
        AgeEncoding t = new AgeEncoding();
        double res = t.getRadix(16, "1");
        System.out.println(res);
    }
}
