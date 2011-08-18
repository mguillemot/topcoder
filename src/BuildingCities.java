import java.util.Arrays;

public class BuildingCities {
    private int[] m_cityX;
    private int[] m_cityY;
    private int m_maxDirect;

    private int cost(int i, int j) {
        double v = dist(i, j) / m_maxDirect;
        if (v == Math.floor(v)) {
            return (int) (v - 1);
        }
        return (int) Math.floor(v);
    }

    private double dist(int i, int j) {
        double dx = m_cityX[j] - m_cityX[i];
        double dy = m_cityY[j] - m_cityY[i];
        return Math.sqrt(dx * dx + dy * dy);
    }

    public int findMinimumCities(int maxDirect, int maxTravel, int[] cityX, int[] cityY) {
        m_maxDirect = maxDirect;
        m_cityX = cityX;
        m_cityY = cityY;
        int N = cityX.length;
        double[][] dist1 = new double[N][2829];
        for (int i = 1; i < N; i++) {
            Arrays.fill(dist1[i], Double.MAX_VALUE);
        }
        boolean change;
        do {
            change = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i != j) {
                        int cost = cost(i, j);
                        double dist = dist(i, j);
                        for (int k = 0; k < 2829 - cost; k++) {
                            double curDist = dist1[j][k + cost];
                            double newDist = dist1[i][k] + dist;
                            if (newDist < curDist) {
                                dist1[j][k + cost] = newDist;
                                change = true;
                            }
                        }
                    }
                }
            }
        } while (change);
        for (int ii = 0; ii < N; ii++) {
            System.out.println("****************node " + ii);
            for (int i = 0; i < 10; i++) {
                System.out.println(i + " => " + dist1[ii][i]);
            }
        }
        for (int k = 0; k < 2829; k++) {
            if (dist1[N - 1][k] <= maxTravel) {
                return k;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BuildingCities t = new BuildingCities();
        int res = t.findMinimumCities(2, 15, new int[]{0, 5, 2, 3, 1, 8, 6, 4, 7, 9, 10}, new int[]{0, 5, 2, 3, 1, 8, 6, 4, 7, 9, 10});
        System.out.println(res); // 0?
    }

}
